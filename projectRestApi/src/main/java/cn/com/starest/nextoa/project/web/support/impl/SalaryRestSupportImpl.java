package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.Salary;
import cn.com.starest.nextoa.project.domain.model.SalaryAggregation;
import cn.com.starest.nextoa.project.domain.model.SalaryImportHistory;
import cn.com.starest.nextoa.project.domain.request.SaveSalaryRequest;
import cn.com.starest.nextoa.project.exception.SalaryException;
import cn.com.starest.nextoa.project.service.SalaryService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.SalaryRestSupport;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class SalaryRestSupportImpl implements SalaryRestSupport {

	@Autowired
	private SalaryService salaryService;

	@Override
	public Page<SalaryAggregationSummary> listSalaryAggregationByYear(SalaryAggregationQueryParameter request, User byWho) {
		Page<SalaryAggregation> salaryAggregationPage = salaryService.listSalaryByAggregation(request,
																							  SalaryAggregation.AggregationType.BY_YEAR,
																							  byWho);
		return new PermissionAwarePageImpl<>(salaryAggregationPage.getContent()
																  .stream()
																  .map(SalaryAggregationSummary::from)
																  .collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 salaryAggregationPage.getTotalElements(),
											 salaryService.resolveGrantedActions(byWho));
	}

	@Override
	public Page<SalaryAggregationSummary> listSalaryAggregationByMonth(SalaryAggregationQueryParameter request, User byWho) {
		Page<SalaryAggregation> salaryAggregationPage = salaryService.listSalaryByAggregation(request,
																							  SalaryAggregation.AggregationType.BY_MONTH,
																							  byWho);
		return new PermissionAwarePageImpl<>(salaryAggregationPage.getContent()
																  .stream()
																  .map(SalaryAggregationSummary::from)
																  .collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 salaryAggregationPage.getTotalElements(),
											 salaryService.resolveGrantedActions(byWho));
	}

	//	@Override
	//	public Page<SalaryAggregationSummary> listSalaryByAggregation(SalaryAggregationQueryParameter request, User byWho) {
	//		Page<SalaryAggregation> salaryAggregationPage = salaryService.listSalaryByAggregation(request, byWho);
	//		return new PermissionAwarePageImpl<>(salaryAggregationPage.getContent()
	//																  .stream()
	//																  .map(SalaryAggregationSummary::from)
	//																  .collect(Collectors.toList()),
	//											 new PageRequest(request.getStart(), request.getLimit()),
	//											 salaryAggregationPage.getTotalElements(),
	//											 salaryService.resolveGrantedActions(byWho));
	//	}

	@Override
	public SalaryImportHistorySummary importSalary(MultipartRequest multipartRequest, User user) {
		List<SaveSalaryRequest> saveSalaryRequestList = new ArrayList<>();
		MultipartFile multipartFile = multipartRequest.getFile("file");
		if (multipartFile == null || multipartFile.isEmpty()) {
			throw new SalaryException("上传的文件不能为空");
		}
		try {
			InputStream fileInputStream = multipartFile.getInputStream();
			if (!fileInputStream.markSupported()) {
				fileInputStream = new PushbackInputStream(fileInputStream, 8);
			}
			Workbook wb = null;
			if (POIFSFileSystem.hasPOIFSHeader(fileInputStream)) {
				try {
					wb = new HSSFWorkbook(fileInputStream);
				}
				catch (Throwable e) {
					throw new RuntimeException("解析excel文件出错", e);
				}
			}
			else if (POIXMLDocument.hasOOXMLHeader(fileInputStream)) {
				try {
					wb = new XSSFWorkbook(OPCPackage.open(fileInputStream));
				}
				catch (Throwable e) {
					throw new RuntimeException("解析excel文件出错", e);
				}
			}

			if (wb == null) {
				throw new RuntimeException("系统目前不支持您提供的excel版本");
			}

			Sheet sheet = wb.getSheetAt(0);
			ImportResult result = new ImportResult();
			result.setResult(true);

			Integer succeedCount = 0;
			Integer failedCount = 0;
			List<String> errorMsgs = new ArrayList<>();

			for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
				int colIndex = 0;

				try {
					Row row = sheet.getRow(rowNum);
					SaveSalaryRequest request = new SaveSalaryRequest();
					colIndex++;
					request.setYear(Integer.valueOf(getStringCellValue(row.getCell(1))));
					colIndex++;
					request.setMonth(Integer.valueOf(getStringCellValue(row.getCell(2))));
					colIndex++;
					request.setCompanyName(getStringCellValue(row.getCell(3)));
					colIndex++;
					request.setProjectName(getStringCellValue(row.getCell(4)));
					colIndex++;
					request.setBizDepartmentName(getStringCellValue(row.getCell(5)));
					colIndex++;
					request.setEmployee(getStringCellValue(row.getCell(6)));
					colIndex++;
					request.setAddress(getStringCellValue(row.getCell(7)));
					colIndex++;
					request.setHireDate(getDateCellValue(row.getCell(8)));
					colIndex++;
					request.setServicedTime(getDecimalCellValue(row.getCell(9)));
					colIndex++;
					request.setBusinessTripInsideTime(getDecimalCellValue(row.getCell(10)));
					colIndex++;
					request.setBusinessTripOutsideTime(getDecimalCellValue(row.getCell(11)));
					colIndex++;
					request.setBaseSalary(getDecimalCellValue(row.getCell(12)));
					colIndex++;
					request.setPerformanceSalary(getDecimalCellValue(row.getCell(13)));
					colIndex++;
					request.setSumSalary(getDecimalCellValue(row.getCell(14)));
					colIndex++;
					request.setBusinessTripInsideSubsidy(getDecimalCellValue(row.getCell(15)));
					colIndex++;
					request.setBusinessTripOutsideSubsidy(getDecimalCellValue(row.getCell(16)));
					colIndex++;
					request.setOvertimeSubsidy(getDecimalCellValue(row.getCell(17)));
					colIndex++;
					request.setComputerSubsidy(getDecimalCellValue(row.getCell(18)));
					colIndex++;
					request.setTempSubsidy(getDecimalCellValue(row.getCell(19)));
					colIndex++;
					request.setSpecialSubsidy(getDecimalCellValue(row.getCell(20)));
					colIndex++;
					request.setFirstPartySubsidy(getDecimalCellValue(row.getCell(21)));
					colIndex++;
					request.setYearEndBonus(getDecimalCellValue(row.getCell(22)));
					colIndex++;
					request.setSumSubsidy(getDecimalCellValue(row.getCell(23)));
					colIndex++;
					request.setOtherDeductMoney(getDecimalCellValue(row.getCell(24)));
					colIndex++;
					request.setCalculatedSalary(getDecimalCellValue(row.getCell(25)));
					colIndex++;
					request.setPersonalIncomeTax(getDecimalCellValue(row.getCell(26)));
					colIndex++;
					request.setSocialInsurance(getDecimalCellValue(row.getCell(27)));
					colIndex++;
					request.setPublicReserveFund(getDecimalCellValue(row.getCell(28)));
					colIndex++;
					request.setSumDeductMoney(getDecimalCellValue(row.getCell(29)));
					colIndex++;
					request.setFinalSalary(getDecimalCellValue(row.getCell(30)));
					colIndex++;
					request.setCorpSocialInsurance(getDecimalCellValue(row.getCell(31)));
					colIndex++;
					request.setCorpPublicReserveFund(getDecimalCellValue(row.getCell(32)));
					colIndex++;
					request.setTotalPay(getDecimalCellValue(row.getCell(33)));
					colIndex++;
					request.setDescription(getStringCellValue(row.getCell(34)));
					colIndex++;
					saveSalaryRequestList.add(request);
				}
				catch (Exception e) {
					// build logs
					failedCount++;
					errorMsgs.add("第[" + rowNum + "]行，第[" + colIndex + "]列导入失败,原因：" + e.getMessage());

					result.setResult(false);
				}
			}
			if (!result.isResult()) {
				throw new SalaryException(errorMsgs.stream().collect(Collectors.joining("<br/>")));
			}

			return SalaryImportHistorySummary.from(salaryService.importSalaries(saveSalaryRequestList, user));
		}
		catch (IOException e) {
			throw new SalaryException(e.getMessage(), e);
		}
	}

	private Date getDateCellValue(Cell cell) throws ParseException {
		if (cell == null) {
			return null;
		}
		return cell.getDateCellValue();
		//		cell.setCellType(Cell.CELL_TYPE_STRING);
		//		if (StringUtils.isEmpty(cell.getStringCellValue()) || cell.getStringCellValue().startsWith("#")) {
		//			return null;
		//		}
		//		try {
		//			return DateUtils.parseDate(cell.getStringCellValue(), "yyyy/MM/dd");
		//		}
		//		catch (Throwable e) {
		//			throw new RuntimeException("解析日期格式出错");
		//		}
	}

	private String getStringCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String result = cell.getStringCellValue();
		if (result != null) {
			result = result.trim();
		}
		return result;
	}

	private BigDecimal getDecimalCellValue(Cell cell) {
		if (cell == null) {
			return BigDecimal.ZERO;
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String cellValue = cell.getStringCellValue();
		if (cellValue != null) {
			cellValue = cellValue.trim();
		}

		if (StringUtils.isEmpty(cellValue)) {
			return null;
		}
		try {
			return new BigDecimal(cellValue);
		}
		catch (Throwable e) {
			throw new RuntimeException("解析数字出错");
		}
	}

	@Override
	public Page<SalaryImportHistorySummary> listSalaryImportHistories(PageQueryParameter request, User byWho) {
		Page<SalaryImportHistory> salaryPage = salaryService.listSalaryImportHistories(request, byWho);
		return new PageImpl<>(salaryPage.getContent()
										.stream()
										.map(SalaryImportHistorySummary::from)
										.collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  salaryPage.getTotalElements());
	}

	@Override
	public SalarySummary createSalary(SaveSalaryRequest request, User byWho) {
		Salary salary = salaryService.createSalary(request, byWho);
		SalarySummary summary = SalarySummary.from(salary);
		summary.setGrantedActions(salaryService.resolveGrantedActions(salary, byWho));
		return summary;
	}

	@Override
	public SalarySummary updateSalary(String id, SaveSalaryRequest request, User byWho) {
		Salary salary = salaryService.updateSalary(id, request, byWho);
		SalarySummary summary = SalarySummary.from(salary);
		summary.setGrantedActions(salaryService.resolveGrantedActions(salary, byWho));
		return summary;
	}

	@Override
	public SalaryDetail findSalaryById(String id, User byWho) {
		Salary salary = salaryService.findSalaryById(id, byWho);
		SalaryDetail summary = SalaryDetail.from(salary);
		summary.setGrantedActions(salaryService.resolveGrantedActions(salary, byWho));
		return summary;
	}

	@Override
	public Page<SalarySummary> listSalaries(SalaryQueryParameter request, User byWho) {
		Page<Salary> salaryPage = salaryService.listSalaries(request, byWho);
		return new PermissionAwarePageImpl<>(salaryPage.getContent().stream().map(item -> {
			SalarySummary summary = SalarySummary.from(item);
			summary.setGrantedActions(salaryService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 salaryPage.getTotalElements(),
											 salaryService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteSalaryById(String id, User user) {
		salaryService.deleteSalaryById(id, user);
	}

}
