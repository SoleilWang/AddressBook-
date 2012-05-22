package com.alex.wang.addressbook.exception;

@SuppressWarnings("serial")
public class AddressBookException extends Exception {
     public AddressBookException(String message){
         super(message);
     }
     public AddressBookException(Throwable exception){
         super(exception);
     }
     public AddressBookException(String message,Throwable exception){
         super(message,exception);
     }
}
