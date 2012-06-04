package com.alex.wang.addressbook.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressBook {
    private static final Logger logger = LoggerFactory.getLogger(AddressBook.class);
    private List<UserInfo> userList;

    public AddressBook() {
        super();
        userList = new LinkedList<UserInfo>();
    }

    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }

    public boolean add(UserInfo info) {
        if (userList.contains(info)) {
            return false;
        }
        userList.add(info);
        return true;
    }

    public boolean delete(String userName) {
        boolean exist = false;
        Iterator<UserInfo> it = userList.iterator();
        List<UserInfo> removeList = new ArrayList<UserInfo>();
        while (it.hasNext()) {
            UserInfo cur = (UserInfo) (it.next());
            if (cur.getName().equalsIgnoreCase(userName)) {
                removeList.add(cur);
                exist = true;
            }
        }
        userList.removeAll(removeList);
        for (UserInfo user : removeList) {
            logger.info("remove" + user.toString());
        }
        return exist;

    }

    public List<UserInfo> search(String phoneNumber) {
        List<UserInfo> result = new ArrayList<UserInfo>();
        Iterator<UserInfo> it = userList.iterator();
        while (it.hasNext()) {
            UserInfo cur = (UserInfo) (it.next());
            if (cur.getPhoneNum().contains(phoneNumber)) {
                result.add(cur);
            }
        }
        return result;
    }

    public List<UserInfo> list() {
        List<UserInfo> resultList = new ArrayList<UserInfo>();
        resultList.addAll(userList);
        return resultList;
    }
}
