package com.genesis.gestioncontacts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ContactException {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public static class ContactMustHaveTvaNumber extends RuntimeException{
        public ContactMustHaveTvaNumber(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ContactNotFound extends RuntimeException{
        public ContactNotFound(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public static class ContactHasAlreadyThisEnterprise extends RuntimeException{
        public ContactHasAlreadyThisEnterprise(String message) {
            super(message);
        }
    }
}
