package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.SystemSetting;
import in.clouthink.nextoa.bl.request.AbstractBaseSummary;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
public class SystemSettingSummary extends AbstractBaseSummary {

    public static void convert(SystemSetting fromObj, SystemSettingSummary result) {
        BeanUtils.copyProperties(fromObj, result);
        AbstractBaseSummary.convert(fromObj, result);

    }

    public static SystemSettingSummary from(SystemSetting fromObj) {
        if (fromObj == null) {
            return null;
        }
        SystemSettingSummary result = new SystemSettingSummary();
        convert(fromObj, result);
        return result;
    }

    private String name;

    private String fileObjectId;

    private String contactEmail;

    private String contactPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileObjectId() {
        return fileObjectId;
    }

    public void setFileObjectId(String fileObjectId) {
        this.fileObjectId = fileObjectId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

}
