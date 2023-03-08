package org.example.mvc.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 测试exceptionhandler的使用， 如何在返回前台的时候，进行异常包装；
 */
@ControllerAdvice
public class DemoExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public String sayEx() {
        return "serviceException";
    }

    @ExceptionHandler(SyncServiceException.class)
    @ResponseBody
    public String saySyncException() {

        return "syncServiceException";
    }

}
