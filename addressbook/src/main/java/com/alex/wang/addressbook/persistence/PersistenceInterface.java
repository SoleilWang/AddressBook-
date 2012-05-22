package com.alex.wang.addressbook.persistence;

import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.entity.UserInfo;
import com.alex.wang.addressbook.exception.AddressBookException;

public  interface PersistenceInterface {
     public  AddressBook read() throws AddressBookException;
     public  void write(AddressBook book)throws AddressBookException;
     public  void insert(UserInfo info);
     public  void update(UserInfo info);
     public  void delete(UserInfo info);
  
}
