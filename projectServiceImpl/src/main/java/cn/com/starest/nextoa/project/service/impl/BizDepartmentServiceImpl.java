package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;
import cn.com.starest.nextoa.project.domain.request.SaveBizDepartmentRequest;
import cn.com.starest.nextoa.project.domain.rule.BizDepartmentReference;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.repository.BizDepartmentRepository;
import cn.com.starest.nextoa.project.service.BizDepartmentService;
import cn.com.starest.nextoa.service.AccountService;
import cn.com.starest.nextoa.service.SystemSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Service
public class BizDepartmentServiceImpl implements BizDepartmentService {

    @Autowired
    private SystemSettingService systemSettingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BizDepartmentRepository bizDepartmentRepository;

    @Autowired(required = false)
    private List<BizDepartmentReference> bizDepartmentReferenceList;

    @Override
    public BizDepartment createBizDepartment(SaveBizDepartmentRequest request, User byWho) {
        if (bizDepartmentRepository.findFirstByName(request.getName()) != null) {
            throw new ValidationException("重复的部门名称");
        }

        BizDepartment bizDepartment = new BizDepartment();

        BeanUtils.copyProperties(request, bizDepartment);
        if (request.getManagerIds() != null) {
            bizDepartment.setManagers(Arrays.asList(request.getManagerIds())
                    .stream()
                    .map(userId -> accountService.findById(userId))
                    .collect(Collectors.toList()));
        }
        BizDepartment.onCreate(bizDepartment, byWho);

        return bizDepartmentRepository.save(bizDepartment);
    }

    @Override
    public BizDepartment updateBizDepartment(String id, SaveBizDepartmentRequest request, User byWho) {
        BizDepartment bizDepartment = bizDepartmentRepository.findById(id);
        if (bizDepartment == null) {
            throw new EntityNotFoundException("没有找到id对应的数据");
        }

        BizDepartment matchedBizDepartment = bizDepartmentRepository.findFirstByName(request.getName());
        if (matchedBizDepartment != null && !matchedBizDepartment.getId().equals(id)) {
            throw new ValidationException("重复的部门名称");
        }

        BeanUtils.copyProperties(request, bizDepartment);
        if (request.getManagerIds() != null) {
            bizDepartment.setManagers(Arrays.asList(request.getManagerIds())
                    .stream()
                    .map(userId -> accountService.findById(userId))
                    .collect(Collectors.toList()));
        } else {
            bizDepartment.setManagers(new ArrayList<>());
        }
        BizDepartment.onModify(bizDepartment, byWho);

        return bizDepartmentRepository.save(bizDepartment);
    }

    @Override
    public BizDepartment deleteBizDepartmentById(String id, User byWho) {
        BizDepartment bizDepartment = bizDepartmentRepository.findById(id);
        if (bizDepartment == null) {
            throw new EntityNotFoundException("没有找到id对应的数据");
        }

        if (bizDepartmentReferenceList != null) {
            bizDepartmentReferenceList.forEach(ref -> {
                if (ref.hasReference(bizDepartment)) {
                    throw new ValidationException("该数据已经被其他数据引用,不能删除");
                }
            });
        }
        bizDepartmentRepository.delete(bizDepartment);
        return bizDepartment;
    }

    @Override
    public BizDepartment findBizDepartmentById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }

        return bizDepartmentRepository.findById(id);
    }

    @Override
    public Page<BizDepartment> listBizDepartments(PageQueryRequest request) {
        return bizDepartmentRepository.findAll(new PageRequest(request.getStart(),
                request.getLimit(),
                new Sort(new Sort.Order(Sort.Direction.ASC, "sort"))));
    }

    @Override
    public Page<BizDepartment> listBizDepartmentsByType(BizDepartmentType type, PageQueryRequest request) {
        return bizDepartmentRepository.findPageByType(type, new PageRequest(request.getStart(),
                request.getLimit(),
                new Sort(new Sort.Order(Sort.Direction.ASC, "sort"))));
    }

    @Override
    public Page<BizDepartment> listSupervisedBizDepartments(PageQueryRequest request, User byWho) {
        //if the user is global supervisors, the user can query all the projects
        boolean supervisor = User.ADMINISTRATOR.equals(byWho.getUsername());
        if (!supervisor) {
            supervisor = systemSettingService.isBizDepartmentSupervisor(byWho);
        }

        if (supervisor) {
            return listBizDepartments(request);
        }

        //else only the project's manager can query the project
        return bizDepartmentRepository.findPageByManagersIn(byWho,
                new PageRequest(request.getStart(),
                        request.getLimit(),
                        new Sort(new Sort.Order(Sort.Direction.ASC,
                                "sort"))));
    }

}
