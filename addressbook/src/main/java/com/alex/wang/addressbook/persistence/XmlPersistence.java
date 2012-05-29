package com.alex.wang.addressbook.persistence;



import java.util.List;
import java.io.File;
import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.entity.UserInfo;
import com.alex.wang.addressbook.exception.AddressBookException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlPersistence implements PersistenceInterface {
	private static final Logger logger = LoggerFactory.getLogger(XmlPersistence.class);
	private static final String XMLFILENAME = "book.xml";
	private static final String XMLROOTELEMENT_IDENTIFY = "users";
	private static final String XMLELEMENT_IDENTIFY = "user";
	private static final String NAME_IDENTIFY = "name";
	private static final String ADDR_IDENTIFY = "address";
	private static final String PHONENO_IDENTIFY = "phoneNumber";
    public XmlPersistence(){
        
    }
	public AddressBook read() throws AddressBookException {
	    
		// read AddressBook from xml file
		AddressBook book = new AddressBook();
		try {
			
			File xmlFile = new File(XMLFILENAME);
			if (xmlFile.exists()) {
				logger.info("read addressBook from " + XMLFILENAME  + "..........");
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = factory.newDocumentBuilder();
				Document doc = db.parse(XMLFILENAME);
				NodeList users = doc.getElementsByTagName("user");
				for (int i = 0; i < users.getLength(); i++) {
					Element user = (Element) users.item(i);

					Node nameNode = user.getElementsByTagName(NAME_IDENTIFY)
							.item(0);
					String userName = nameNode.getFirstChild().getNodeValue();

					Node addressNode = user.getElementsByTagName(ADDR_IDENTIFY)
							.item(0);
					String address = addressNode.getFirstChild().getNodeValue();

					Node numberNode = user.getElementsByTagName(
							PHONENO_IDENTIFY).item(0);
					String phoneNumber = numberNode.getFirstChild()
							.getNodeValue();

					UserInfo newUser = new UserInfo(userName, address,
							phoneNumber);
					book.add(newUser);
				}
				logger.info("finished reading " + XMLFILENAME);
			}else{
				logger.info("not exists" + XMLFILENAME+ "will auto create the file");
			}
		} catch (Exception e) {
			logger.error("read book.xml error " + e.getMessage() );
			throw new AddressBookException("read book.xml error", e);
		}
		return book;
	}

	private static Element addElement(Document doc, Node parent,
			String tagName, String value) {
		Element e = doc.createElement(tagName);
		if (null != value) {
			e.setTextContent(value);
		}
		if (null != parent) {
			parent.appendChild(e);
		}
		return e;
	}

	public void write(AddressBook book) throws AddressBookException {
		if (book != null) {
			logger.info("write addressBook to " + XMLFILENAME + "........");
			try {
				List<UserInfo> resultList = book.list();
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = factory.newDocumentBuilder();
				Document doc = db.newDocument();
				Element root = addElement(doc, doc, XMLROOTELEMENT_IDENTIFY,
						null);
				for (UserInfo user : resultList) {
					Element userElement = addElement(doc, root,
							XMLELEMENT_IDENTIFY, null);
					String userName = user.getName();
					String address = user.getAddress();
					String phoneNumber = user.getPhoneNum();
					addElement(doc, userElement, NAME_IDENTIFY, userName);
					addElement(doc, userElement, ADDR_IDENTIFY, address);
					addElement(doc, userElement, PHONENO_IDENTIFY, phoneNumber);
				}

				TransformerFactory tFactory = TransformerFactory.newInstance();

				File tmpFile = new File("temp.xml");
				if (tmpFile.exists()) {
					tmpFile.delete();
					logger.info("remove original temp.xml successfully");
				}
				Transformer transformer = tFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(tmpFile);
				transformer.transform(source, result);
				File xmlFile = new File(XMLFILENAME);
				if (xmlFile.exists()) {
					xmlFile.delete();
					logger.info("remove original "+ XMLFILENAME + " successfully");
				}
				
				tmpFile.renameTo(xmlFile);
				logger.info("rename temp.xml to " +  XMLFILENAME + "successfully");
				logger.info("finished writing xml file ");
			} catch (Exception e) {
				logger.error("write xml file error " + e.getMessage() );
				throw new AddressBookException("write xml file error", e);
			}
		}else{
			logger.info("addressbook not change,not needed to rewrite xml file ");
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
