package com.alex.wang.addressbook.entity;

/**
 * represent data of one user,store user's name,address,phoneNumber
 * 
 * @author EWYABDL
 * 
 */
public class UserInfo {
    private String name;
    private String address;
    private String phoneNum;

    /**
     * default constructor
     */
    public UserInfo() {
        super();
    }

    /**
     * Constructs a UserInfo instance with user's name,address,telephone number
     * 
     * @param name
     *            user's name
     * @param address
     *            user's address
     * @param phoneNum
     *            user's telephone number
     */
    public UserInfo(String name, String address, String phoneNum) {
        super();
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    /**
     * get user's name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set user's name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get user's address
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * set user's address
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get user's telephone Number
     * 
     * @return
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * set user's telephone Number
     * 
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Returns a string representation of the UserInfo Object ,like[ Name: wang ,Address:tishan , PhoneNum: 021-99892312 ]
     */
    @Override
    public String toString() {
        return new StringBuilder().append("[ Name: ").append(name).append(" ,Address: ").append(address).append(" , PhoneNum: ").append(phoneNum).append(" ]")
                .toString();
    }

    /**
     * Returns the hash code of the UserInfo Object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserInfo other = (UserInfo) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equalsIgnoreCase(other.address))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equalsIgnoreCase(other.name))
            return false;
        if (phoneNum == null) {
            if (other.phoneNum != null)
                return false;
        } else if (!phoneNum.equalsIgnoreCase(other.phoneNum))
            return false;
        return true;
    }

}
