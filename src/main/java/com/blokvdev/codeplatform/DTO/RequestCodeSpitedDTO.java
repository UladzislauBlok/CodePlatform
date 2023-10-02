package platform.DTO;

public class RequestCodeSpitedDTO {
    private String code;
    private String time;
    private String views;

    public RequestCodeSpitedDTO() {
    }

    public RequestCodeSpitedDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
