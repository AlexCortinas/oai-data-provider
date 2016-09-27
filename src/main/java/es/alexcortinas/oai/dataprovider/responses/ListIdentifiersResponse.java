package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.Document;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.ListIdentifiers;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ListIdentifiersResponse extends OAIResponse implements IOAIResponse {
    private ListIdentifiers listIdentifiers;

    public ListIdentifiersResponse(OAIRequest request, ListIdentifiers listIdentifiers, String baseURL,
            Granularity granularity) {
        super(request, baseURL, granularity);
        this.listIdentifiers = listIdentifiers;
    }

    public Document getJdomDocument() {
        Document doc = XmlUtils.getXmlResponseTemplate(getRequest(), getBaseURL());

        doc.getRootElement().addContent(listIdentifiers.toJdomElement(getGranularity()));

        return doc;
    }

    public ListIdentifiers getListIdentifiers() {
        return listIdentifiers;
    }

    public void setListIdentifiers(ListIdentifiers listIdentifiers) {
        this.listIdentifiers = listIdentifiers;
    }

}
