package com.alex.wang.addressbook.exception;

/**
 * The AddressBookException class that indicates exception conditions of this program
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
     * Constructs a new AddressBookException with the specified detail message.
     * 
     * @param message
     *            the detail message.
     */
    public AddressBookException(String message) {
        super(message);
    }

    /**
     * Constructs a new AddressBookException with the specified detail message and cause.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is
     *            nonexistent or unknown.)
     */
    public AddressBookException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new AddressBookException with the specified cause and a detail message of (cause==null ? null : cause.toString()) (which typically contains
     * the class and detail message of cause).
     * 
     * @param cause
     *            the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is
     *            nonexistent or unknown.)
     */
    public AddressBookException(Throwable cause) {
        super(cause);
    }

}
