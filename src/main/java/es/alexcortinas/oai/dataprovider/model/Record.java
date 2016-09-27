package es.alexcortinas.oai.dataprovider.model;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.IRecordMetadata;
import es.alexcortinas.oai.dataprovider.util.IJdomConvertible;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class Record implements IJdomConvertible {
    private RecordHeader header;
    private IRecordMetadata metadata;
    private RecordAbout about;

    public Record(RecordHeader header, IRecordMetadata metadata, RecordAbout about) {
        super();
        this.header = header;
        this.metadata = metadata;
        this.about = about;
    }

    public Element toJdomElement(Granularity granularity) {
        Element xml = new Element("record", XmlUtils.getOAINamespace());

        xml.addContent(header.toJdomElement(granularity));

        if (metadata != null) {
            Element met = new Element("metadata", XmlUtils.getOAINamespace());
            met.addContent(metadata.toJdomElement(granularity));
            xml.addContent(met);
        }

        if (about != null)
            xml.addContent(about.toJdomElement(granularity));

        return xml;
    }

    public RecordHeader getHeader() {
        return header;
    }

    public void setHeader(RecordHeader header) {
        this.header = header;
    }

    public RecordAbout getAbout() {
        return about;
    }

    public void setAbout(RecordAbout about) {
        this.about = about;
    }

    public IRecordMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(IRecordMetadata metadata) {
        this.metadata = metadata;
    }

}
