package es.alexcortinas.oai.dataprovider.model;

import java.util.List;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ListIdentifiers {
    private List<RecordHeader> headers;
    private ResumptionToken token;

    public ListIdentifiers(List<RecordHeader> headers, ResumptionToken token) {
        super();
        this.headers = headers;
        this.token = token;
    }

    public Element toJdomElement(Granularity granularity) {
        Element xml = new Element("ListIdentifiers", XmlUtils.getOAINamespace());

        if (headers != null) {
            for (RecordHeader rh : headers) {
                xml.addContent(rh.toJdomElement(granularity));
            }
        }

        if (token != null) {
            xml.addContent(token.toJdomElement(null));
        }

        return xml;
    }

    public List<RecordHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<RecordHeader> headers) {
        this.headers = headers;
    }

    public ResumptionToken getToken() {
        return token;
    }

    public void setToken(ResumptionToken token) {
        this.token = token;
    }

}
