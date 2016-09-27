package es.alexcortinas.oai.dataprovider.model;

import java.util.List;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ListMetadataFormats {
    private List<MetadataFormat> metadataFormats;

    public ListMetadataFormats(List<MetadataFormat> metadataFormats) {
        this.metadataFormats = metadataFormats;
    }

    public Element toJdomElement() {
        Element xml = new Element("ListMetadataFormats", XmlUtils.getOAINamespace());

        for (MetadataFormat mf : metadataFormats) {
            xml.addContent(mf.toJdomElement());
        }

        return xml;
    }

    public List<MetadataFormat> getMetadataFormats() {
        return metadataFormats;
    }

    public void setMetadataFormats(List<MetadataFormat> metadataFormats) {
        this.metadataFormats = metadataFormats;
    }

}
