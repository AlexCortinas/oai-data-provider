package es.alexcortinas.oai.dataprovider.model.recordmetadatas;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.XMLOutputter;

import es.alexcortinas.oai.dataprovider.exceptions.OAIRuntimeException;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class RecordMetadataDC implements IRecordMetadata {
    private MultipleAttribute<String> title = new MultipleAttribute<String>();
    private MultipleAttribute<String> description = new MultipleAttribute<String>();
    private MultipleAttribute<String> language = new MultipleAttribute<String>();

    private MultipleAttribute<String> subject = new MultipleAttribute<String>();
    private MultipleAttribute<String> type = new MultipleAttribute<String>();
    private MultipleAttribute<String> coverage = new MultipleAttribute<String>();

    private MultipleAttribute<String> creator = new MultipleAttribute<String>();
    private MultipleAttribute<String> contributor = new MultipleAttribute<String>();
    private MultipleAttribute<String> date = new MultipleAttribute<String>();
    private MultipleAttribute<String> publisher = new MultipleAttribute<String>();
    private MultipleAttribute<String> source = new MultipleAttribute<String>();

    private MultipleAttribute<String> format = new MultipleAttribute<String>();
    private MultipleAttribute<String> identifier = new MultipleAttribute<String>();
    private MultipleAttribute<String> relation = new MultipleAttribute<String>();
    private MultipleAttribute<String> rights = new MultipleAttribute<String>();

    public RecordMetadataDC() {
    }

    @Override
    public Element toJdomElement(Granularity granularity) {
        Namespace oai_dc = Namespace.getNamespace("oai_dc", "http://www.openarchives.org/OAI/2.0/oai_dc/");
        Namespace dc = Namespace.getNamespace("dc", "http://purl.org/dc/elements/1.1/");
        Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element xml = new Element("dc", oai_dc);
        xml.addNamespaceDeclaration(oai_dc);
        xml.addNamespaceDeclaration(dc);
        xml.setAttribute("schemaLocation",
                "http://www.openarchives.org/OAI/2.0/oai_dc/ " + "http://www.openarchives.org/OAI/2.0/oai_dc.xsd", xsi);

        XmlUtils.addListElement(xml, "title", title.toList(), true, dc);
        XmlUtils.addListElement(xml, "creator", creator.toList(), true, dc);
        XmlUtils.addListElement(xml, "subject", subject.toList(), true, dc);

        XmlUtils.addListElement(xml, "description", description.toList(), true, dc);

        XmlUtils.addListElement(xml, "publisher", publisher.toList(), true, dc);
        XmlUtils.addListElement(xml, "contributor", contributor.toList(), true, dc);
        XmlUtils.addListElement(xml, "date", date.toList(), true, dc);

        XmlUtils.addListElement(xml, "type", type.toList(), true, dc);

        XmlUtils.addListElement(xml, "format", format.toList(), true, dc);

        XmlUtils.addListElement(xml, "identifier", identifier.toList(), true, dc);
        XmlUtils.addListElement(xml, "source", source.toList(), true, dc);
        XmlUtils.addListElement(xml, "language", language.toList(), true, dc);
        XmlUtils.addListElement(xml, "relation", relation.toList(), true, dc);
        XmlUtils.addListElement(xml, "coverage", coverage.toList(), true, dc);
        XmlUtils.addListElement(xml, "rights", rights.toList(), true, dc);

        return xml;
    }

    public MultipleAttribute<String> getTitle() {
        return title;
    }

    public MultipleAttribute<String> getDescription() {
        return description;
    }

    public MultipleAttribute<String> getLanguage() {
        return language;
    }

    public MultipleAttribute<String> getSubject() {
        return subject;
    }

    public MultipleAttribute<String> getType() {
        return type;
    }

    public MultipleAttribute<String> getCoverage() {
        return coverage;
    }

    public MultipleAttribute<String> getCreator() {
        return creator;
    }

    public MultipleAttribute<String> getContributor() {
        return contributor;
    }

    public MultipleAttribute<String> getDate() {
        return date;
    }

    public MultipleAttribute<String> getPublisher() {
        return publisher;
    }

    public MultipleAttribute<String> getSource() {
        return source;
    }

    public MultipleAttribute<String> getFormat() {
        return format;
    }

    public MultipleAttribute<String> getIdentifier() {
        return identifier;
    }

    public MultipleAttribute<String> getRelation() {
        return relation;
    }

    public MultipleAttribute<String> getRights() {
        return rights;
    }

    public void setTitle(MultipleAttribute<String> title) {
        this.title = title;
    }

    public void setDescription(MultipleAttribute<String> description) {
        this.description = description;
    }

    public void setLanguage(MultipleAttribute<String> language) {
        this.language = language;
    }

    public void setSubject(MultipleAttribute<String> subject) {
        this.subject = subject;
    }

    public void setType(MultipleAttribute<String> type) {
        this.type = type;
    }

    public void setCoverage(MultipleAttribute<String> coverage) {
        this.coverage = coverage;
    }

    public void setCreator(MultipleAttribute<String> creator) {
        this.creator = creator;
    }

    public void setContributor(MultipleAttribute<String> contributor) {
        this.contributor = contributor;
    }

    public void setDate(MultipleAttribute<String> date) {
        this.date = date;
    }

    public void setPublisher(MultipleAttribute<String> publisher) {
        this.publisher = publisher;
    }

    public void setSource(MultipleAttribute<String> source) {
        this.source = source;
    }

    public void setFormat(MultipleAttribute<String> format) {
        this.format = format;
    }

    public void setIdentifier(MultipleAttribute<String> identifier) {
        this.identifier = identifier;
    }

    public void setRelation(MultipleAttribute<String> relation) {
        this.relation = relation;
    }

    public void setRights(MultipleAttribute<String> rights) {
        this.rights = rights;
    }

    public String toString(Granularity granularity) {
        Element e = toJdomElement(granularity);
        return new XMLOutputter().outputString(e);
    }

    public org.w3c.dom.Element toXmlElement(Granularity granularity) {
        DOMOutputter dout = new DOMOutputter();
        try {
            return dout.output(toJdomElement(granularity));
        } catch (JDOMException e) {
            throw new OAIRuntimeException("OAIResponse.getXmlDocument: error converting jdom document");
        }
    }
}
