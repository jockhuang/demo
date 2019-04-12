package jock.demo.controller;

import jock.demo.service.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * handler all unknow exceptions
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    MyReponseBody handleException(Exception e) {
        logger.error(e.getMessage(), e);

        return MyReponseBody.failed("operation failed:" + e.getMessage());


    }

    /**
     * handler all business exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    MyReponseBody handleBusinessException(BusinessException e) {
        logger.error(e.getMessage(), e);

        return MyReponseBody.failed(e.getMessage());
    }

    /**
     * handler MethodArgumentNotValidException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    MyReponseBody handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return MyReponseBody.failed("ValidException:" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage());

    }

}
