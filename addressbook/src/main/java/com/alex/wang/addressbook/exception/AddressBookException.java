package com.alex.wang.addressbook.exception;

/**
 * The Exception class that indicates exception conditions of this program
 * 
 * @author ewyabdl
 * 
 */
public class AddressBookException extends Exception {

    private static final long serialVersionUID = -8600577646334661352L;

    /**
     * Constructs a new AddressBookException with null as its detail message.
     */
    public AddressBookException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message
     * @param exception
     */
    public AddressBookException(String message, Throwable exception) {
        super(message, exception);
    }
}
