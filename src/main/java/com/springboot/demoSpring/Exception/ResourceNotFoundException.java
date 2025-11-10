package com.springboot.demoSpring.Exception;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
