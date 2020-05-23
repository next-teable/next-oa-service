package in.clouthink.nextoa.openapi.dto;

import java.util.List;

/**
 */
public abstract class AbstractPaperParameter {
    
    private List<ReceiverParameter> to;
    
    private List<ReceiverParameter> cc;
    
    private String paperContent;
    
    public List<ReceiverParameter> getTo() {
        return to;
    }
    
    public void setTo(List<ReceiverParameter> to) {
        this.to = to;
    }
    
    public List<ReceiverParameter> getCc() {
        return cc;
    }
    
    public void setCc(List<ReceiverParameter> cc) {
        this.cc = cc;
    }
    
    public String getPaperContent() {
        return paperContent;
    }
    
    public void setPaperContent(String paperContent) {
        this.paperContent = paperContent;
    }
}
