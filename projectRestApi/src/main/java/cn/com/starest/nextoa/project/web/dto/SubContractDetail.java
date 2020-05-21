package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.AttachmentSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractSubContract;
import cn.com.starest.nextoa.project.domain.model.SubContract;

import java.util.List;

/**
 * @author dz
 */
public class SubContractDetail extends SubContractSummary {
    
    public static void convert(SubContract fromObj, SubContractDetail result) {
        SubContractSummary.convert((AbstractSubContract) fromObj, result);
    }
    
    public static SubContractDetail from(SubContract fromObj) {
        if (fromObj == null) {
            return null;
        }
        SubContractDetail result = new SubContractDetail();
        convert(fromObj, result);
        return result;
    }
    
    private List<AttachmentSummary> attachments;
    
    public List<AttachmentSummary> getAttachments() {
        return attachments;
    }
    
    public void setAttachments(List<AttachmentSummary> attachments) {
        this.attachments = attachments;
    }
    
}
