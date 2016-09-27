package es.alexcortinas.oai.dataprovider.util;

import java.util.Calendar;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;

public class XmlUtils {

    public static Namespace getOAINamespace() {
        return Namespace.getNamespace("http://www.openarchives.org/OAI/2.0/");
    }

    public static Document getXmlResponseTemplate(OAIRequest request, String baseUrl) {
        Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element oai_pmh = new Element("OAI-PMH", getOAINamespace());
        oai_pmh.addNamespaceDeclaration(getOAINamespace());
        oai_pmh.setAttribute("schemaLocation",
                "http://www.openarchives.org/OAI/2.0/ " + "http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd", xsi);

        Document doc = new Document(oai_pmh);

        Element responseDate = new Element("responseDate", getOAINamespace());
        responseDate.setText(DateUtils.calendarToString(Calendar.getInstance(), Granularity.SECONDS));
        doc.getRootElement().addContent(responseDate);

        if (request != null) {
            Element requestElement = request.toXmlElement(baseUrl);
            requestElement.setNamespace(getOAINamespace());
            doc.getRootElement().addContent(requestElement);
        } else {
            Element r = new Element("request", getOAINamespace());
            r.setText(baseUrl);
            doc.getRootElement().addContent(r);
        }
        return doc;
    }

    public static void addTextElement(Element xml, String name, String value) {

        addTextElement(xml, name, value, false);
    }

    public static void addTextElement(Element xml, String name, String value, Namespace namespace) {

        addTextElement(xml, name, value, false, namespace);
    }

    public static void addTextElement(Element xml, String name, String value, boolean onlyIfValueExists) {

        if (onlyIfValueExists && StringUtils.isBlank(value)) {
            return;
        }

        Element aux = new Element(name, getOAINamespace());
        aux.setText(value);
        xml.addContent(aux);
    }

    public static void addTextElement(Element xml, String name, String value, boolean onlyIfValueExists,
            Namespace namespace) {

        if (onlyIfValueExists && StringUtils.isBlank(value)) {
            return;
        }

        Element aux = new Element(name, namespace);
        aux.setText(value);
        xml.addContent(aux);
    }

    public static void addListElement(Element xml, String name, List<String> valueList, boolean onlyIfValueExists,
            Namespace namespace) {

        if (valueList == null || valueList.size() == 0) {
            if (!onlyIfValueExists) {
                addTextElement(xml, name, null, namespace);
            }
            return;
        }

        for (String aux : valueList) {
            XmlUtils.addTextElement(xml, name, aux, namespace);
        }
    }

}
