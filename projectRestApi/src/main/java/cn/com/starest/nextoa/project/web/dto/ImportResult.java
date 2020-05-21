package cn.com.starest.nextoa.project.web.dto;

public class ImportResult {
    private Boolean result;

    private String message;

    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
