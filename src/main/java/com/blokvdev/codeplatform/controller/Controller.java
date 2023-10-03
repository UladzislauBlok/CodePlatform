package com.blokvdev.codeplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.blokvdev.codeplatform.DTO.RequestCodeSpitedDTO;
import com.blokvdev.codeplatform.DTO.ResponseCodeSnipedDTO;
import com.blokvdev.codeplatform.service.Service;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping(path = "/code/{UUID}")
    public ModelAndView getCodeModelPage(@PathVariable String UUID) {
        ResponseCodeSnipedDTO codeSniped = service.getCodeSniped(UUID);
        ModelAndView modelAndView;
        if (codeSniped.getViews() > 0 && codeSniped.getTime() > 0) {
            modelAndView = new ModelAndView("codeAllLimit");
            modelAndView.addObject("endTime", codeSniped.getTime());
            modelAndView.addObject("leftViews", codeSniped.getViews());
        } else if (codeSniped.getViews() != 0) {
            modelAndView = new ModelAndView("codeViewsLimit");
            modelAndView.addObject("leftViews", codeSniped.getViews() == -1 ? 0 : codeSniped.getViews());
        } else if (codeSniped.getTime() != 0) {
            modelAndView = new ModelAndView("codeTimeLimit");
            modelAndView.addObject("endTime", codeSniped.getTime());
        } else {
            modelAndView = new ModelAndView("code");
        }

        modelAndView.addObject("codeBody", codeSniped.getCode());
        modelAndView.addObject("dateTime", codeSniped.getDate());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(path = "/code/new")
    public ModelAndView getCodeWritePage() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(path = "/code/latest")
    public ModelAndView getTenLatestCodePage() {
        ModelAndView modelAndView = new ModelAndView("latest");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("codeList", service.getTenLatestCode());
        return modelAndView;
    }

    @GetMapping(path = "/api/code/{UUID}")
    public ResponseEntity<ResponseCodeSnipedDTO> getCode(@PathVariable String UUID) {
        ResponseCodeSnipedDTO responseCodeSniped = service.getCodeSniped(UUID);
        responseCodeSniped.setViews(responseCodeSniped.getViews() == -1 ? 0 : responseCodeSniped.getViews());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseCodeSniped);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map> setCode(@RequestBody RequestCodeSpitedDTO codeSnipedDTO) {
        String id = service.addCodeSniped(codeSnipedDTO);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("id", id));
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List> getTenLatestCode() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getTenLatestCode());
    }
}
