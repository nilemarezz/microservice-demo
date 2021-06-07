package com.nile.springdemo.exception;

public class UserException extends BaseException{

    public UserException(String message) {
        super("user." + message );
    }

    public static UserException userNull(){
        return new UserException("register.user.null");
    }

    public static UserException emailNull(){
        return new UserException("register.email.null");
    }

    public static UserException passwordNull(){
        return new UserException("register.password.null");
    }

    public static UserException emailDuplicate(){
        return new UserException("create.email.duplicate");
    }

    public static UserException loginEmailNotFound(){
        return new UserException("login.email.notfound");
    }

    public static UserException loginPasswordNotMatch(){
        return new UserException("login.password.notmatch");
    }
    public static UserException userNotFound(){
        return new UserException("not.found");
    }

    public static UserException unAuthorized(){
        return new UserException("unAuthorized");
    }


}
