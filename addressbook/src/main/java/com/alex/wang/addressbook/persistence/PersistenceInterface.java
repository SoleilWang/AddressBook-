package com.alex.wang.addressbook.persistence;

import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.exception.AddressBookException;

public interface PersistenceInterface {
    AddressBook read() throws AddressBookException;

    void write(AddressBook book) throws AddressBookException;

}
