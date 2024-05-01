package tcpWork.Operations;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tcpWork.Data.MetroCard;
import tcpWork.Data.MetroCardBank;
import tcpWork.Data.User;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteToXMLOperation extends CardOperation{
    @Override
    public String execute(MetroCardBank bank) {
        String filepath = "CardBank.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = builder.newDocument();
        Element root = doc.createElement("list");
        doc.appendChild(root);
        for (MetroCard card : bank.getStore()) {
            addElement(card, doc);
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
        } catch (TransformerConfigurationException e) {
            return "error";
        }
        transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath.trim()));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            return "error";
        }
        return "success";
    }

    private void addElement(MetroCard card, Document doc) {
        Element MetroCard = doc.createElement("MetroCard");
        Attr attr = doc.createAttribute("serNum");
        attr.setValue(card.getSerNum());
        MetroCard.setAttributeNode(attr);

        attr = doc.createAttribute("College");
        attr.setValue(card.getCollege());
        MetroCard.setAttributeNode(attr);

        attr = doc.createAttribute("balance");
        attr.setValue(String.valueOf(card.getBalance()));
        MetroCard.setAttributeNode(attr);

        User user = card.getUsr();
        Element User = doc.createElement("User");

        attr = doc.createAttribute("name");
        attr.setValue(user.getName());
        User.setAttributeNode(attr);

        attr = doc.createAttribute("surname");
        attr.setValue(user.getSurName());
        User.setAttributeNode(attr);

        attr = doc.createAttribute("sex");
        attr.setValue(user.getSex());
        User.setAttributeNode(attr);

        attr = doc.createAttribute("birthday");
        attr.setValue(user.getBirthday());
        User.setAttributeNode(attr);

        MetroCard.appendChild(User);
        doc.getDocumentElement().appendChild(MetroCard);
    }
}
