package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.JDOMException;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.exceptions.OAIRuntimeException;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;

public abstract class OAIResponse implements IOAIResponse {
    private final OAIRequest request;
    private final String baseURL;
    private final Granularity granularity;

    public OAIResponse(OAIRequest request, String baseURL, Granularity granularity) {
        this.request = request;
        this.baseURL = baseURL;
        this.granularity = granularity;
    }

    public String getXmlString() {
        XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
        return xout.outputString(getJdomDocument());
    }

    public org.w3c.dom.Document getXmlDocument() {
        DOMOutputter dout = new DOMOutputter();
        try {
            return dout.output(getJdomDocument());
        } catch (JDOMException e) {
            throw new OAIRuntimeException("OAIResponse.getXmlDocument: error converting jdom document");
        }
    }

    public OAIRequest getRequest() {
        return request;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public Granularity getGranularity() {
        return granularity;
    }

}
