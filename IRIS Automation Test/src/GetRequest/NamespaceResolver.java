package GetRequest;

import javax.xml.namespace.NamespaceContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
 
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

class NamespaceResolver implements NamespaceContext 
{
    //Store the source document to search the namespaces
    private Document sourceDocument;
 
    public NamespaceResolver(Document document) {
        sourceDocument = document;
    }
 
    //The lookup for the namespace uris is delegated to the stored document.
    public String getNamespaceURI(String prefix) {
        if (prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
            return sourceDocument.lookupNamespaceURI(null);
        } else {
            return sourceDocument.lookupNamespaceURI(prefix);
        }
    }
 
    public String getPrefix(String namespaceURI) {
        return sourceDocument.lookupPrefix(namespaceURI);
    }
 
    @SuppressWarnings("rawtypes")
    public Iterator getPrefixes(String namespaceURI) {
        return null;
    }
}