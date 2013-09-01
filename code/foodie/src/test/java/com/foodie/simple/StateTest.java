package com.foodie.simple;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;

public class StateTest {
    public class IsNewAddressRequest {
        private Boolean isNewAddress;
        public Boolean getIsNewAddress() {
            return isNewAddress;
        }
        public void setIsNewAddress(Boolean isNewAddress) {
            this.isNewAddress = isNewAddress;
        }
    }
    @Test
    public void reflectionTest() throws NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, SAXException,
            IOException, ParserConfigurationException, ClassNotFoundException,
            DOMException, InstantiationException {
        String methodName = "helloWorld";
        IsNewAddressRequest request = new IsNewAddressRequest();
        request.setIsNewAddress(false);
        if(judge(
                IsNewAddressRequest.class,
                request,
                StateTest.class.getMethod(methodName).getAnnotation(AccessbleJudge.class))) {
            this.helloWorld();
        }

    }

    @AccessbleJudge(xmlPath = "wzj.xml")
    public void helloWorld()
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        System.out.println("helloWorld jim");
    }

    public boolean isNewAddress(Boolean isNewAddress) {
        return isNewAddress;
    }

    public boolean isLogin() {
        return true;
    }

    public boolean judge(Class<?> c, Object z, AccessbleJudge s) throws SAXException,
            IOException, ParserConfigurationException, ClassNotFoundException,
            DOMException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(this.getClass().getClassLoader()
                .getResourceAsStream(s.xmlPath()));
        NodeList nodeList = doc.getElementsByTagName("Method");
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("i:" + i);
            if (!invokeMethod(c, z, nodeList.item(i)))
                return false;
        }

        return true;

    }

    public boolean invokeMethod(Class<?> c, Object z,Node node) throws ClassNotFoundException,
            DOMException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Class<?> thisClass = Class.forName(node.getAttributes()
                .getNamedItem("class").getNodeValue());
        Object obj = thisClass.getConstructor().newInstance();
        Element eleNode = (Element) node;
        boolean expectResult = Boolean.valueOf(eleNode.getElementsByTagName("Return").item(0).getAttributes().getNamedItem("value").getNodeValue());
        Element element = (Element) node;
        if(element.getElementsByTagName("Params").getLength() == 0) {
            Method thisMethod = thisClass.getMethod(node.getAttributes()
                    .getNamedItem("method").getNodeValue());
            return expectResult == Boolean.valueOf(thisMethod.invoke(obj)
                    .toString());
        } else {
            NodeList nodeList = ((Element)element.getElementsByTagName("Params").item(0)).getElementsByTagName("Param");
            Object[] objs = new Object[nodeList.getLength()];
            Class<?>[] types = new Class[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                String param = nodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
                String type = nodeList.item(i).getAttributes().getNamedItem("type").getNodeValue();
                types[i] = Class.forName(type);
                objs[i] = c.getMethod("get" + StringUtil.capitalize(param)).invoke(z);
            }
            Method thisMethod = thisClass.getMethod(node.getAttributes()
                    .getNamedItem("method").getNodeValue(), types);
            return expectResult == Boolean.valueOf(thisMethod.invoke(obj, objs)
                    .toString());
        }
        
    }
}
