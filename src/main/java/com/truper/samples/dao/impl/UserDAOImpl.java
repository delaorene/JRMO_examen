package com.truper.samples.dao.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *
 * @author cgarcias
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

	/**
	 * @author cgarcias Devuelve el usuario si existe en el archivo XML configurado
	 *         en Constant.PATH_SAMPLE_USERS
	 */
	@Override
	public UserVO getByUser(final String strUser) {

		UserVO uUserVO = null;

		final String filePath = new String(Paths.get("src", "main", "resources", Constant.PATH_SAMPLE_USERS).toString());

		final File fXmlFile = new File(filePath);
		final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			LOG.error(e.getMessage(), e);
		}

		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException | IOException e) {
			LOG.error(e.getMessage(), e);
		}

		doc.getDocumentElement().normalize();

		final NodeList nList = doc.getElementsByTagName("user");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			final Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				final Element eElement = (Element) nNode;
				final Node nodeName = eElement.getElementsByTagName("name").item(0);
				final String strUs = nodeName.getFirstChild().getNodeValue();
				final String strPwd = eElement.getElementsByTagName("pwd").item(0).getFirstChild().getNodeValue();
				final String strRol = eElement.getElementsByTagName("rol").item(0).getFirstChild().getNodeValue();

				if (strUs.equals(strUser)) {
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
