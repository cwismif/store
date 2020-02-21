package com.cistek.store;

public class ItemNotFoundException extends Exception {

    /**
     *  For versioning when serialised
     */
    private static final long serialVersionUID = 1L;

    ItemNotFoundException(String itemId){
        super(String.format("%s is not found", itemId));
    }
}
