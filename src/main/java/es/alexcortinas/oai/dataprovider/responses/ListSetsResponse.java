package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.Document;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.ListSets;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ListSetsResponse extends OAIResponse implements IOAIResponse {
    private ListSets listSets;

    public ListSetsResponse(OAIRequest request, ListSets listSets, String baseURL) {
        super(request, baseURL, null);
        this.listSets = listSets;
    }

    public Document getJdomDocument() {
        Document doc = XmlUtils.getXmlResponseTemplate(getRequest(), getBaseURL());

        doc.getRootElement().addContent(listSets.toJdomElement());

        return doc;
    }

    public ListSets getListSets() {
        return listSets;
    }

    public void setListSets(ListSets listSets) {
        this.listSets = listSets;
    }

}
