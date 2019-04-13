package jock.demo.controller;

import jock.demo.service.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * when exception happened, this class is responsible for capturing them at the
 * controller level, passing the exception information to the requester, and
 * converting it to the corresponding HTTP status code.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * handler all unknow exceptions
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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
    @ExceptionHandler({BusinessException.class,MethodArgumentTypeMismatchException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MyReponseBody handleBusinessException(Exception e) {
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MyReponseBody handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return MyReponseBody.failed("ValidException:" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage());

    }

}
