package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.Document;
import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.Record;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class GetRecordResponse extends OAIResponse implements IOAIResponse {
    private Record record;

    public GetRecordResponse(OAIRequest request, Record record, String baseURL, Granularity granularity) {
        super(request, baseURL, granularity);
        this.record = record;
    }

    public Document getJdomDocument() {
        Document doc = XmlUtils.getXmlResponseTemplate(getRequest(), getBaseURL());

        Element getRecord = new Element("GetRecord", XmlUtils.getOAINamespace());
        getRecord.addContent(record.toJdomElement(getGranularity()));
        doc.getRootElement().addContent(getRecord);

        return doc;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

}
