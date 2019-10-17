package com.downing.controller;

import com.downing.exception.LogicException;
import com.downing.pojo.entity.DowningResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author downing
 * @descript
 */
@ControllerAdvice
@RestController
public class BaseExceptionHandler {

    /**
     * Exception统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(LogicException.class)
    public DowningResult error(LogicException e) {
        //TODO 日志记录
        return new DowningResult(501, e.getMessage());
    }

    /**
     * Exception统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public DowningResult error(Exception e) {
        //TODO 日志记录
        return new DowningResult(500, e.getMessage());
    }
}
