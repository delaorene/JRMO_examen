package com.truper.samples.dao.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.truper.samples.dao.UserDAO;
import com.truper.samples.util.properties.Constant;
import com.truper.sinv.vo.UserVO;

/**
 * Clase para leer los usuarios diponibles para acceder a los WS Rest
 * @author cgarcias
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {
	
	/**
	 * @author cgarcias
	 * Devuelve el usuario si existe en el archivo XML configurado en Constant.PATH_SAMPLE_USERS
	 */
	@Override
	public UserVO getByUser(String strUser) {
		
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
				
				if(strUs.equals(strUser)){
					uUserVO = new UserVO();
					uUserVO.setUser(strUs);
					uUserVO.setPassword(strPwd);
					uUserVO.setRol(strRol);
					break;
				}

			}
		}
		return uUserVO;
	}

}
