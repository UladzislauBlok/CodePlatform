package com.blokvdev.codeplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.blokvdev.codeplatform.DTO.ResponseCodeSnipedDTO;
import com.blokvdev.codeplatform.model.CodeSniped;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Configuration
public class Config  {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Bean
    public Function<CodeSniped, ResponseCodeSnipedDTO> responseConverter() {
        return codeSniped -> new ResponseCodeSnipedDTO(
                codeSniped.getCode(),
                codeSniped.getLocalDateTime().format(formatter),
                codeSniped.getLeftTimeSec(),
                codeSniped.getLeftViews()
        );
    }
}
