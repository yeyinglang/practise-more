package org.example.mvc.exception;

public class ServiceException extends Exception{

    public ServiceException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {

        return super.toString()+": sayServiceException";
    }
}
