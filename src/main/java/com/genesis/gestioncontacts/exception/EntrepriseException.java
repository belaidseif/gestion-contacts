package com.genesis.gestioncontacts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntrepriseException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class EntrepriseNotFound extends RuntimeException{
        public EntrepriseNotFound(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class TowOrEnterpriseHaveTheSameTva extends RuntimeException{
        public TowOrEnterpriseHaveTheSameTva(String message) {
            super(message);
        }
    }
}
