package com.blokvdev.codeplatform.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCodeSpitedDTO {
    private String code;
    private String time;
    private String views;
}
