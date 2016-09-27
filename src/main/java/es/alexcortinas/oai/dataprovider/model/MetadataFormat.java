package es.alexcortinas.oai.dataprovider.model;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class MetadataFormat {
    private String metadataPrefix;
    private String schema;
    private String metadataNamespace;

    public MetadataFormat(String metadataPrefix, String schema, String metadataNamespace) {
        this.metadataPrefix = metadataPrefix;
        this.schema = schema;
        this.metadataNamespace = metadataNamespace;
    }

    public Element toJdomElement() {
        Element xml = new Element("metadataFormat", XmlUtils.getOAINamespace());

        XmlUtils.addTextElement(xml, "metadataPrefix", metadataPrefix);
        XmlUtils.addTextElement(xml, "schema", schema);
        XmlUtils.addTextElement(xml, "metadataNamespace", metadataNamespace);

        return xml;
    }

    public String getMetadataPrefix() {
        return metadataPrefix;
    }

    public void setMetadataPrefix(String metadataPrefix) {
        this.metadataPrefix = metadataPrefix;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getMetadataNamespace() {
        return metadataNamespace;
    }

    public void setMetadataNamespace(String metadataNamespace) {
        this.metadataNamespace = metadataNamespace;
    }

}
