package com.i2i.bicell.parsexml;

import org.apache.logging.log4j.*;
import org.w3c.dom.*;  // nodes creation
import com.i2i.bicell.Message.Message.service;

import java.io.File;
import java.io.IOException;
import java.util.*; // types of list
import javax.xml.parsers.*; // this package makes the parsing
import com.i2i.bicell.config.config;
import org.xml.sax.SAXException;


public class parseXml{
    private Logger log = LogManager.getLogger(parseXml.class);
    private List<service> list_of_service = new ArrayList<service>();
    private DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    private Document document = builder.parse(new File(config.get_FilePath())); // charging.xml

    public parseXml() throws ParserConfigurationException, IOException, SAXException {
    }
    // this class made for parsing xml file, for billing the usege

    public List<service> get_Service_Info(){
        log.info("XML parsing");
        NodeList nlist = document.getElementsByTagName("service");
        for(int temp=0; temp < nlist.getLength(); temp++){
            Node nNode = nlist.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                service service = new service();

                service.set_Pck_name(eElement.getAttribute("serviceName")); // charging..xml -> package name
                service.set_regex((eElement // charging..xml -> regex
                        .getElementsByTagName("regex")
                        .item(0)
                        .getTextContent()));
                service.set_round((Integer.parseInt(eElement // charging..xml -> round
                        .getElementsByTagName("round")
                        .item(0)
                        .getTextContent())));
                service.set_price((Double.parseDouble(eElement // charging..xml -> price
                        .getElementsByTagName("price")
                        .item(0)
                        .getTextContent())));

                list_of_service.add(service);
            }
        }
        return list_of_service;
    }

}