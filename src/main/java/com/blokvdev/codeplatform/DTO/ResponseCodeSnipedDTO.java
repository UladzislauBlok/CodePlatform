package com.blokvdev.codeplatform.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCodeSnipedDTO {
    private String code;
    private String date;
    private long time;
    private int views;
}
