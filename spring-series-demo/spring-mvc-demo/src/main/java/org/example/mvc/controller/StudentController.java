package org.example.mvc.controller;

import org.example.mvc.exception.ServiceException;
import org.example.mvc.exception.ServiceInnerException;
import org.example.mvc.exception.SyncServiceException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
public class StudentController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    @ResponseBody
//    @ResponseStatus
    public String sayHello() throws ServiceException{
        throw new ServiceException("hello");
    }

    @RequestMapping(value = "/hello2", method= RequestMethod.GET)
    @ResponseBody
//    @ResponseStatus
    public String sayHelloa(){
        return "hello";
    }

    @RequestMapping(value = "/hellosync", method= RequestMethod.GET)
    @ResponseBody
//    @ResponseStatus
    public String saySyncException() throws SyncServiceException {
        throw new SyncServiceException("syncService from controller");
    }

    @RequestMapping(value = "/helloinner", method= RequestMethod.GET)
    public String sayInner() throws ServiceInnerException {
        throw new ServiceInnerException("syncInnerException from controller");
    }
//    @ResponseBody
    @ExceptionHandler(ServiceInnerException.class)
    public String sayInnerException(){
       return "innerException ";
    }




}
