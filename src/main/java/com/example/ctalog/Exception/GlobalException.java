package com.example.ctalog.Exception;

import com.example.ctalog.Exception.HeaderNullPointerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.example.ctalog.Security")

public class GlobalException {




    @ExceptionHandler(Exception.class)
    public void handleAll(Exception e) {
       System.err.println("asdasda+++++++++++++++++++");
    }


}
