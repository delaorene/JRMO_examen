package com.truper.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.truper.samples.dao.impl.UserDAOImpl;
import com.truper.samples.util.properties.Constant;

public class XMLRead {

	private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

	@Test
	public void leer() {

		File fXmlFile = new File(Constant.PATH_SAMPLE_USERS);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOG.error(e.getMessage(), e);
		}

		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException | IOException e) {
			LOG.error(e.getMessage(), e);
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
