package com.blokvdev.codeplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.blokvdev.codeplatform.DTO.RequestCodeSpitedDTO;
import com.blokvdev.codeplatform.DTO.ResponseCodeSnipedDTO;
import com.blokvdev.codeplatform.exception.NotFoundElementByIdException;
import com.blokvdev.codeplatform.model.CodeSniped;
import com.blokvdev.codeplatform.repository.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@org.springframework.stereotype.Service
public class Service {

    private final CodeRepository codeRepository;
    private final Function<CodeSniped, ResponseCodeSnipedDTO> responseConverter;

    @Autowired
    public Service(CodeRepository codeRepository, Function<CodeSniped, ResponseCodeSnipedDTO> responseConverter) {
        this.codeRepository = codeRepository;
        this.responseConverter = responseConverter;
    }

    public ResponseCodeSnipedDTO getCodeSniped(String id) {
        CodeSniped codeSniped = codeRepository.findById(id).orElseThrow(NotFoundElementByIdException::new);
        ResponseCodeSnipedDTO responseCodeSniped = responseConverter.apply(codeSniped);
        if (codeSniped.getLeftViews() == 0 && codeSniped.getLeftTimeSec() == 0)
                return responseCodeSniped;


        LocalDateTime localDateTimeLimit = codeSniped.getLocalDateTime().plusSeconds(codeSniped.getLeftTimeSec());

        if (codeSniped.getLeftTimeSec() > 0) {
            if (localDateTimeLimit.isBefore(LocalDateTime.now())) {
                codeRepository.delete(codeSniped);
                throw new NotFoundElementByIdException();
            }

            long time = Duration.between(LocalDateTime.now(), localDateTimeLimit).getSeconds();
            responseCodeSniped.setTime(time);
        }

        if (codeSniped.getLeftViews() > 0) {
            if (codeSniped.getLeftViews() == 1) {
                codeRepository.delete(codeSniped);
                responseCodeSniped.setViews(-1);
            } else {
                int leftViews = codeSniped.getLeftViews() - 1;
                codeSniped.setLeftViews(leftViews);
                responseCodeSniped.setViews(leftViews);
                codeRepository.save(codeSniped);
            }
        }

        return responseCodeSniped;
    }

    public String addCodeSniped(RequestCodeSpitedDTO codeSnipedDTO) {
        CodeSniped codeSniped =  new CodeSniped(
                UUID.randomUUID().toString(),
                codeSnipedDTO.getCode(),
                LocalDateTime.now(),
                Integer.parseInt(codeSnipedDTO.getTime()),
                Integer.parseInt(codeSnipedDTO.getViews())
        );
        codeRepository.save(codeSniped);
        return codeSniped.getId();
    }

    public List<ResponseCodeSnipedDTO> getTenLatestCode() {
        return codeRepository.findTop10ByLeftTimeSecAndLeftViewsOrderByLocalDateTimeDesc(0, 0).stream()
                .map(responseConverter).toList();
    }
}
