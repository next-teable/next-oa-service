package in.clouthink.nextoa.bl.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Document(collection = "SystemSettings")
public class SystemSetting extends BaseModel {

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
