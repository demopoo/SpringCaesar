package com.cs.ioc.xml;import com.cs.ioc.BeanDefinition;import com.cs.ioc.BeanRefrence;import com.cs.ioc.PropertyItem;import com.cs.ioc.PropertyItems;import com.cs.ioc.io.Resource;import com.cs.ioc.io.ResourceLoaderURL;import com.cs.ioc.io.URLResource;import com.sun.tools.doclets.internal.toolkit.util.DocFinder;import org.w3c.dom.Document;import org.w3c.dom.Element;import org.w3c.dom.Node;import org.w3c.dom.NodeList;import org.xml.sax.SAXException;import javax.xml.parsers.DocumentBuilder;import javax.xml.parsers.DocumentBuilderFactory;import javax.xml.parsers.ParserConfigurationException;import java.io.IOException;import java.io.InputStream;import java.net.URL;import java.util.Map;/** * @author: demopoo * @Date: Created in 下午10:44 2018/3/19 * @Des: 读取xml中配置的Bean * @Modifyed By: */public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {    public XmlBeanDefinitionReader(){    }    @Override    public void loadXmlBeanDefinition(String location) {        Resource resource = new ResourceLoaderURL().getURL(location);        InputStream inputStream = resource.getInputStream();        getDocument(inputStream);    }    public void getDocument(InputStream inputStream){        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();        try {            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();            Document document = documentBuilder.parse(inputStream);            getElement(document);        } catch (ParserConfigurationException e) {            e.printStackTrace();        } catch (SAXException e) {            e.printStackTrace();        } catch (IOException e) {            e.printStackTrace();        }finally {            try {                inputStream.close();            } catch (IOException e) {                e.printStackTrace();            }        }    }    public void getElement(Document document){        Element element = document.getDocumentElement();        getNodeList(element);    }    public void getNodeList(Element element){        NodeList nodeList = element.getChildNodes();        for (int i=0;i<nodeList.getLength();i++){            Node node = nodeList.item(i);            if (node instanceof Element){                Element ele = (Element)node;                getProperty(ele);            }        }    }    public void getProperty(Element element){        String beanSimpleName = element.getAttribute("name");        String beanName = element.getAttribute("class");        BeanDefinition beanDefinition = new BeanDefinition();        beanDefinition.setBeanName(beanName);        NodeList nodeList = element.getChildNodes();        if (nodeList != null){            PropertyItems propertyItems = new PropertyItems();            for (int i=0;i<nodeList.getLength();i++){                Node node = nodeList.item(i);                if (node instanceof Element){                    Element ele = (Element) node;                    String propertyName = ele.getAttribute("name");                    String propertyObject = ele.getAttribute("value");                    String propertyRef = ele.getAttribute("ref");                    if (propertyItems != null && propertyRef.length()>0){                        BeanRefrence beanRefrence = new BeanRefrence(propertyRef);                        propertyItems.addPropertyItem(new PropertyItem(propertyName,beanRefrence));                    }else {                        PropertyItem propertyItem = new PropertyItem(propertyName,propertyObject);                        propertyItems.addPropertyItem(propertyItem);                    }                }            }            beanDefinition.setPropertyItems(propertyItems);        }        this.map.put(beanSimpleName,beanDefinition);    }}