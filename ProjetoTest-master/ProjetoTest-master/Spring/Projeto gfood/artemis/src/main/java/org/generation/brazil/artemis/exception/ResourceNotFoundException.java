package org.generation.brazil.artemis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    public class ResourceNotFoundException extends Exception {

        public ResourceNotFoundException(String message) {
            super(message);
        }


    }
