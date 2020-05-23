package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.PaperType;
import in.clouthink.nextoa.model.dtr.SavePaperRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SavePaperParameter implements SavePaperRequest {
    
    private String title;
    
    private String category;
    
    private String content;
    
    private Boolean isUrgent;
    
    private PaperType type;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setType(PaperType type) {
        this.type = type;
    }
    
    @Override
    public PaperType getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Boolean getUrgent() {
        return isUrgent;
    }
    
    public void setUrgent(Boolean urgent) {
        isUrgent = urgent;
    }
    
}
