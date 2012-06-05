package com.alex.wang.addressbook.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AddressBook used to store UserInfo objects which customer has added
 * 
 * @author ewyabdl
 * 
 */
public class AddressBook {
    private static final Logger logger = LoggerFactory.getLogger(AddressBook.class);
    private List<UserInfo> userList;

    /**
     * default constructor
     */
    public AddressBook() {
        super();
        userList = new LinkedList<UserInfo>();
    }

    /**
     * get userList which used to actually store the UserInfo Objects
     * 
     * @return userList the list which used to actually store the UserInfo Objects
     */
    public List<UserInfo> getUserList() {
        return userList;
    }

    /**
     * set List<UserInfo> object to actually store UserInfo Objects
     * 
     * @param userList
     *            UserInfo list to actually store UserInfo Objects
     */
    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }

    /**
     * add a userInfo object
     * 
     * @param info
     *            the userInfo Object wanted to add into the AddressBook
     * @return
     */
    public boolean add(UserInfo info) {
        if (userList.contains(info)) {
            return false;
        }
        userList.add(info);
        return true;
    }

    /**
     * delete userInfo Object
     * 
     * @param userName
     *            the name of user you want to delete
     * @return
     */
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

    /**
     * search userInfo Object by phoneNumber
     * 
     * @param phoneNumber
     *            the phoneNumber customer want to search
     * @return
     */
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

    /**
     * return the copy of userList
     * 
     * @return the copy of userList
     */
    public List<UserInfo> list() {
        List<UserInfo> resultList = new ArrayList<UserInfo>();
        resultList.addAll(userList);
        return resultList;
    }
}
