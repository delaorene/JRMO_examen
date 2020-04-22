package com.truper.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.truper.samples.util.properties.Constant;
import com.truper.sinv.vo.UserVO;

public class XMLRead {
	
	@Test
	public void leer(){
		UserVO uUserVO = null;
		
		File fXmlFile = new File(Constant.PATH_SAMPLE_USERS);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("user");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Node nodeName = eElement.getElementsByTagName("name").item(0);
				String strUs = nodeName.getFirstChild().getNodeValue();
				String strPwd = eElement.getElementsByTagName("pwd").item(0).getFirstChild().getNodeValue();
				String strRol = eElement.getElementsByTagName("rol").item(0).getFirstChild().getNodeValue();
			}
		}
	}
	
}
