package es.alexcortinas.oai.dataprovider.model;

import java.util.List;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ListRecords {
    private List<Record> records;
    private ResumptionToken token;

    public ListRecords(List<Record> records, ResumptionToken token) {
        super();
        this.records = records;
        this.token = token;
    }

    public Element toJdomElement(Granularity granularity) {
        Element xml = new Element("ListRecords", XmlUtils.getOAINamespace());

        if (records != null) {
            for (Record rh : records) {
                xml.addContent(rh.toJdomElement(granularity));
            }
        }

        if (token != null) {
            xml.addContent(token.toJdomElement(null));
        }

        return xml;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public ResumptionToken getToken() {
        return token;
    }

    public void setToken(ResumptionToken token) {
        this.token = token;
    }

}
