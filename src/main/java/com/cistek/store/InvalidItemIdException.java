package com.cistek.store;

public class InvalidItemIdException extends Exception {

    /**
     *  For versioning when serialised
     */
    private static final long serialVersionUID = 1L;

    InvalidItemIdException(String itemId){
        super(String.format("%s is invalid", itemId));
    }
}
