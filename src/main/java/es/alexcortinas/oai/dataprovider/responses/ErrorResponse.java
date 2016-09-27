package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.Document;
import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.exceptions.OAIException;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ErrorResponse extends OAIResponse implements IOAIResponse {
    private OAIException exception;

    public ErrorResponse(OAIException oaiException, String baseUrl) {
        super(null, baseUrl, null);
        this.exception = oaiException;
    }

    public Document getJdomDocument() {
        Document doc = XmlUtils.getXmlResponseTemplate(null, getBaseURL());

        Element error = new Element("error", XmlUtils.getOAINamespace());
        error.setAttribute("code", exception.getErrorCode().toString());
        error.setText(exception.getErrorMessage());
        doc.getRootElement().addContent(error);

        return doc;
    }

    public OAIException getException() {
        return exception;
    }

    public void setException(OAIException exception) {
        this.exception = exception;
    }

}
