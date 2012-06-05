package com.alex.wang.addressbook.persistence;

import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.exception.AddressBookException;

/**
 * 
 * @author ewyabdl
 * 
 */
public interface PersistenceInterface {
    /**
     * read users's data from persistence object,and store data in a AddressBook object and return
     * 
     * @return AddressBook object to store users's data
     * @throws AddressBookException
     */
    AddressBook read() throws AddressBookException;

    /**
     * write users's data customer have added into persistence object
     * 
     * @param book
     *            book object stored users's data customer have added
     * @throws AddressBookException
     */
    void write(AddressBook book) throws AddressBookException;

}
