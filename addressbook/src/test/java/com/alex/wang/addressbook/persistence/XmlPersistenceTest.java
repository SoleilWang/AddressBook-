package com.alex.wang.addressbook.persistence;


import java.io.File;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static org.junit.Assert.assertEquals;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.entity.UserInfo;
import com.alex.wang.addressbook.exception.AddressBookException;

public class XmlPersistenceTest {
    private AddressBook book;
    private XmlPersistence persistence = new XmlPersistence();
    private static final String XMLFILENAME = "book.xml";
    private static final String XMLROOTELEMENT_IDENTIFY = "users";
    private static final String XMLELEMENT_IDENTIFY = "user";
    private static final String NAME_IDENTIFY = "name";
    private static final String ADDR_IDENTIFY = "address";
    private static final String PHONENO_IDENTIFY = "phoneNumber";

    @Before
    public void setUp() throws Exception {
        book = new AddressBook();
        File xmlFile = new File("book.xml");
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
        List<UserInfo> userList = new ArrayList<UserInfo>();
        userList.add(new UserInfo("wang zhen", "tianshan road", "021-98764342"));
        userList.add(new UserInfo("wang zhen", "pudong road", "021-22364342"));
        book.setUserList(userList);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = addElement(doc, doc, XMLROOTELEMENT_IDENTIFY, null);

        Element userElement1 = addElement(doc, root, XMLELEMENT_IDENTIFY, null);
        addElement(doc, userElement1, NAME_IDENTIFY, "wang zhen");
        addElement(doc, userElement1, ADDR_IDENTIFY, "tianshan road");
        addElement(doc, userElement1, PHONENO_IDENTIFY, "021-98764342");
        Element userElement2 = addElement(doc, root, XMLELEMENT_IDENTIFY, null);
        addElement(doc, userElement2, NAME_IDENTIFY, "wang zhen");
        addElement(doc, userElement2, ADDR_IDENTIFY, "pudong road");
        addElement(doc, userElement2, PHONENO_IDENTIFY, "021-22364342");
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    }

    @After
    public void tearDown() throws Exception {
        File xmlFile = new File("book.xml");
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
    }

    private Element addElement(Document doc, Node parent, String tagName, String value) {
        Element e = doc.createElement(tagName);
        if (null != value) {
            e.setTextContent(value);
        }
        if (null != parent) {
            parent.appendChild(e);
        }
        return e;
    }

    @Test
    public void testRead() {
        AddressBook readedbook;
        try {
            readedbook = persistence.read();
            assertEquals(readedbook.getUserList(), book.getUserList());
        } catch (AddressBookException e) {

        } finally {
            File logFile = new File("address.log");
            if (logFile.exists()) {
                logFile.delete();
            }
        }

    }

    @Test(expected = AddressBookException.class)
    public void testReadWrongFile() throws AddressBookException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = factory.newDocumentBuilder();
            Document doc = db.parse(XMLFILENAME);
            NodeList users = doc.getElementsByTagName("user");
            Element user = (Element) users.item(0);
            NodeList ftpnodes = user.getChildNodes();
            for (int i = 0; i < ftpnodes.getLength(); i++) {
                user.removeChild(ftpnodes.item(0));
            }
            File xmlFile = new File("book.xml");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
            persistence.read();
        } catch (AddressBookException e) {
            throw e;
        } catch (Exception ele) {
            System.out.print(ele.getMessage());
        } finally {
            File logFile = new File("address.log");
            if (logFile.exists()) {
                logFile.delete();
            }
        }

    }

    @Test
    public void testWrite() {

        File xmlFile = new File("book.xml");
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
        try {
            persistence.write(book);

            xmlFile = new File(XMLFILENAME);
            List<UserInfo> userList = new ArrayList<UserInfo>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document doc = db.parse(XMLFILENAME);
            NodeList users = doc.getElementsByTagName("user");
            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);

                Node nameNode = user.getElementsByTagName(NAME_IDENTIFY).item(0);
                String userName = nameNode.getFirstChild().getNodeValue();

                Node addressNode = user.getElementsByTagName(ADDR_IDENTIFY).item(0);
                String address = addressNode.getFirstChild().getNodeValue();

                Node numberNode = user.getElementsByTagName(PHONENO_IDENTIFY).item(0);
                String phoneNumber = numberNode.getFirstChild().getNodeValue();

                UserInfo newUser = new UserInfo(userName, address, phoneNumber);
                userList.add(newUser);
            }
            assertEquals(userList, book.getUserList());

        } catch (Exception e) {

        } finally {
            File logFile = new File("address.log");
            if (logFile.exists()) {
                logFile.delete();
            }
        }

    }

}
