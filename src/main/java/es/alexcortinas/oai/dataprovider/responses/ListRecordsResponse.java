package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.Document;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.ListRecords;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ListRecordsResponse extends OAIResponse implements IOAIResponse {
    private ListRecords listRecords;

    public ListRecordsResponse(OAIRequest request, ListRecords listRecords, String baseURL, Granularity granularity) {
        super(request, baseURL, granularity);
        this.listRecords = listRecords;
    }

    public Document getJdomDocument() {
        Document doc = XmlUtils.getXmlResponseTemplate(getRequest(), getBaseURL());

        doc.getRootElement().addContent(listRecords.toJdomElement(getGranularity()));

        return doc;
    }

    public ListRecords getListRecords() {
        return listRecords;
    }

    public void setListRecords(ListRecords listRecords) {
        this.listRecords = listRecords;
    }

}
