package com.alex.wang.addressbook.persistence;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileWriter;

import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.entity.UserInfo;
import com.alex.wang.addressbook.exception.AddressBookException;

import org.dom4j.io.*;
import org.dom4j.*;

public class XmlPersistence implements PersistenceInterface {

    private static final String XMLFILENAME = "book.xml";
    private static final String XMLROOTELEMENT_IDENTIFY = "users";
    private static final String XMLELEMENT_IDENTIFY = "user";
    private static final String NAME_IDENTIFY = "name";
    private static final String ADDR_IDENTIFY = "address";
    private static final String PHONENO_IDENTIFY = "phoneNumber";

    public AddressBook read() throws AddressBookException {
        // read AddressBook from xml file
        SAXReader sReader = new SAXReader();
        AddressBook book = new AddressBook();
        try {
            File xmlFile = new File(XMLFILENAME);
            if (xmlFile.exists()) {
                Document document = sReader.read(xmlFile);
                Element rootElement = document.getRootElement();
                @SuppressWarnings("unchecked")
                List<Element> userList = rootElement.elements();
                
                Iterator<Element> userIt = userList.iterator();
                while (userIt.hasNext()) {
                    Element ele = userIt.next();
                    String userName = ele.attributeValue(NAME_IDENTIFY);
                    String address = ele.attributeValue(ADDR_IDENTIFY);
                    String phoneNumber = ele.attributeValue(PHONENO_IDENTIFY);
                    UserInfo newUser = new UserInfo(userName, address, phoneNumber);
                    book.add(newUser);
                }
               
            }
        } catch (Exception e) {
            throw new AddressBookException("read book.xml error", e);
        }
        return book;
    }

    public void write(AddressBook book) throws AddressBookException {
        if (book != null) {
            // XMLROOTELEMENT_IDENTIFY
            // XMLELEMENT_IDENTIFY
            List<UserInfo> resultList = book.list();
            Document dc = DocumentHelper.createDocument();
            Element root = dc.addElement(XMLROOTELEMENT_IDENTIFY);
            for (UserInfo user : resultList) {
                String userName = user.getName();
                String address = user.getAddress();
                String phoneNumber = user.getPhoneNum();
                Element userElement = root.addElement(XMLELEMENT_IDENTIFY);
                userElement.addAttribute(NAME_IDENTIFY, userName);
                userElement.addAttribute(ADDR_IDENTIFY, address);
                userElement.addAttribute(PHONENO_IDENTIFY, phoneNumber);
            }
            XMLWriter writer = null;
            try {
                File  tmpFile = new File("temp.xml");
                if (tmpFile.exists()) {
                    tmpFile.delete();
                }
                OutputFormat format = OutputFormat.createPrettyPrint();
                writer = new XMLWriter(new FileWriter(tmpFile), format);
                writer.write(dc);
                writer.close();
                File xmlFile = new File(XMLFILENAME);
                if(xmlFile.exists()){
                    xmlFile.delete(); 
                }
                  tmpFile.renameTo(xmlFile);
            } catch (Exception e) {
                throw new AddressBookException("write xml file error",e);
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public void insert(UserInfo info) {
        // TODO Auto-generated method stub

    }

    public void update(UserInfo info) {
        // TODO Auto-generated method stub

    }

    public void delete(UserInfo info) {
        // TODO Auto-generated method stub

    }

}
