package com.griddynamics.api.handler;

import com.griddynamics.api.exception.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String SERVER_ERROR = "Internal Server Error";

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorInfo noHandlerFoundException(HttpServletRequest request, RuntimeException e) {
        return logAndGetErrorInfo(request, e, HttpStatus.INTERNAL_SERVER_ERROR.value(), SERVER_ERROR);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, int status, String error) {
        LOGGER.error("Exception at request " + ": " + req.getRequestURL(), e);
        return new ErrorInfo(status, error, e.getLocalizedMessage());
    }

}
