package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "code")
public class CodeSniped {

    @Id
    private String id;
    private String code;
    private LocalDateTime localDateTime;
    private int leftTimeSec;
    private int leftViews;

    public CodeSniped() {}

    public CodeSniped(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLeftTimeSec() {
        return leftTimeSec;
    }

    public void setLeftTimeSec(int leftTimeSec) {
        this.leftTimeSec = leftTimeSec;
    }

    public int getLeftViews() {
        return leftViews;
    }

    public void setLeftViews(int leftViews) {
        this.leftViews = leftViews;
    }
}
