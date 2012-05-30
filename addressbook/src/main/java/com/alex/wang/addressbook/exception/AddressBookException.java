package com.alex.wang.addressbook.exception;


public class AddressBookException extends Exception {
  

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AddressBookException(String message) {
        super(message);
    }

    public AddressBookException(Throwable exception) {
        super(exception);
    }

    public AddressBookException(String message, Throwable exception) {
        super(message, exception);
    }
}
