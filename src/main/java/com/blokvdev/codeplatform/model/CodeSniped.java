package com.blokvdev.codeplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "code")
@NoArgsConstructor
@AllArgsConstructor
public class CodeSniped {

    @Id
    private String id;
    private String code;
    private LocalDateTime localDateTime;
    private int leftTimeSec;
    private int leftViews;
}
