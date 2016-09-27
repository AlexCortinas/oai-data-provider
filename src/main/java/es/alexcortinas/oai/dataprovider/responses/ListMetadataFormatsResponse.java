package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.Document;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.ListMetadataFormats;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ListMetadataFormatsResponse extends OAIResponse implements IOAIResponse {
    private ListMetadataFormats listMetadataFormats;

    public ListMetadataFormatsResponse(OAIRequest request, ListMetadataFormats listMetadataFormats, String baseURL) {
        super(request, baseURL, null);
        this.listMetadataFormats = listMetadataFormats;
    }

    public Document getJdomDocument() {
        Document doc = XmlUtils.getXmlResponseTemplate(getRequest(), getBaseURL());

        doc.getRootElement().addContent(listMetadataFormats.toJdomElement());

        return doc;
    }

    public ListMetadataFormats getListMetadataFormats() {
        return listMetadataFormats;
    }

    public void setListMetadataFormats(ListMetadataFormats listMetadataFormats) {
        this.listMetadataFormats = listMetadataFormats;
    }

}
