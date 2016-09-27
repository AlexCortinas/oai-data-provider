package es.alexcortinas.oai.dataprovider.model.recordmetadatas;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.XMLOutputter;

import es.alexcortinas.oai.dataprovider.exceptions.OAIRuntimeException;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class RecordMetadataESE implements IRecordMetadata {
    // Mandatory if description is null
    private MultipleAttribute<String> dc_title = new MultipleAttribute<String>();
    // Mandatory if title is null
    private MultipleAttribute<String> dc_description = new MultipleAttribute<String>();
    // Mandatory if europeana_type is TEXT
    private MultipleAttribute<String> dc_language = new MultipleAttribute<String>();

    // One at least of the four next is mandatory
    private MultipleAttribute<String> dc_subject = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_type = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_coverage = new MultipleAttribute<String>();
    private MultipleAttribute<String> dcterms_spatial = new MultipleAttribute<String>();

    private MultipleAttribute<String> dc_creator = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_contributor = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_date = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_publisher = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_source = new MultipleAttribute<String>();

    private MultipleAttribute<String> dcterms_isPartOf = new MultipleAttribute<String>();
    private MultipleAttribute<String> dcterms_alternative = new MultipleAttribute<String>();
    private MultipleAttribute<String> dcterms_created = new MultipleAttribute<String>();
    private MultipleAttribute<String> dcterms_issued = new MultipleAttribute<String>();
    private MultipleAttribute<String> dcterms_temporal = new MultipleAttribute<String>();

    private MultipleAttribute<String> dc_format = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_identifier = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_relation = new MultipleAttribute<String>();
    private MultipleAttribute<String> dc_rights = new MultipleAttribute<String>();

    private MultipleAttribute<String> europeana_year = new MultipleAttribute<String>();
    private MultipleAttribute<String> europeana_unstored = new MultipleAttribute<String>();
    private String europeana_object; // [0,1]
    private String europeana_provider; // Mandatory
    private EUType europeana_type; // Mandatory
    private EURights europeana_rights; // Mandatory
    private String europeana_dataProvider; // Mandatory
    private String europeana_isShownAt; // Mandatory if isShownBy is null
    private String europeana_isShownBy; // Mandatory if isShownAt is null
    private String europeana_ugc;// [0,1]

    // less used

    private MultipleAttribute<String> dcterms_extent = new MultipleAttribute<String>();

    public RecordMetadataESE() {
        super();
    }

    public RecordMetadataESE(RecordMetadataDC dc) {
        this();
        this.dc_title = dc.getTitle();
        this.dc_description = dc.getDescription();
        this.dc_language = dc.getLanguage();

        this.dc_date = dc.getDate();

        this.dc_subject = dc.getSubject();
        this.dc_type = dc.getType();
        this.dc_coverage = dc.getCoverage();

        this.dc_creator = dc.getCreator();
        this.dc_contributor = dc.getContributor();
        this.dc_date = dc.getDate();

        this.dc_publisher = dc.getPublisher();
        this.dc_source = dc.getSource();
        this.dc_format = dc.getFormat();
        this.dc_identifier = dc.getIdentifier();
        this.dc_relation = dc.getRelation();
        this.dc_rights = dc.getRights();
    }

    public RecordMetadataESE(RecordMetadataDC dc, String provider, EUType type, EURights rights, String dataProvider,
            String isShownAt) {

        this(dc);

        this.europeana_provider = provider;
        this.europeana_type = type;
        this.europeana_rights = rights;
        this.europeana_dataProvider = dataProvider;
        this.europeana_isShownAt = isShownAt;
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

    @Override
    public Element toJdomElement(Granularity granularity) {
        Namespace dcterms = Namespace.getNamespace("dcterms", "http://purl.org/dc/terms/");
        Namespace europeana = Namespace.getNamespace("europeana", "http://www.europeana.eu/schemas/ese/");
        Namespace dc = Namespace.getNamespace("dc", "http://purl.org/dc/elements/1.1/");
        Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element xml = new Element("record", europeana);
        xml.addNamespaceDeclaration(europeana);
        xml.addNamespaceDeclaration(dcterms);
        xml.addNamespaceDeclaration(dc);
        xml.setAttribute("schemaLocation",
                "http://www.europeana.eu/schemas/ese/ " + "http://www.europeana.eu/schemas/ese/ESE-V3.4.xsd", xsi);

        if (!dc_title.hasValues() && !dc_description.hasValues()) {
            XmlUtils.addTextElement(xml, "title", null, dc);
        } else {
            XmlUtils.addListElement(xml, "title", dc_title.toList(), true, dc);
            XmlUtils.addListElement(xml, "description", dc_description.toList(), true, dc);
        }

        if (europeana_type != null && europeana_type == EUType.TEXT && !dc_language.hasValues()) {
            XmlUtils.addTextElement(xml, "language", null, dc);
        } else {
            XmlUtils.addListElement(xml, "language", dc_language.toList(), true, dc);
        }

        if (!dc_subject.hasValues() && !dc_type.hasValues() && !dc_coverage.hasValues()
                && !dcterms_spatial.hasValues()) {
            XmlUtils.addTextElement(xml, "subject", null, dc);
        } else {
            XmlUtils.addListElement(xml, "subject", dc_subject.toList(), true, dc);
            XmlUtils.addListElement(xml, "type", dc_type.toList(), true, dc);
            XmlUtils.addListElement(xml, "coverage", dc_coverage.toList(), true, dc);
            XmlUtils.addListElement(xml, "spatial", dcterms_spatial.toList(), true, dcterms);
        }

        XmlUtils.addListElement(xml, "temporal", dcterms_temporal.toList(), true, dcterms);

        XmlUtils.addListElement(xml, "creator", dc_creator.toList(), true, dc);
        XmlUtils.addListElement(xml, "contributor", dc_contributor.toList(), true, dc);
        XmlUtils.addListElement(xml, "date", dc_date.toList(), true, dc);
        XmlUtils.addListElement(xml, "created", dcterms_created.toList(), true, dcterms);
        XmlUtils.addListElement(xml, "issued", dcterms_issued.toList(), true, dcterms);
        XmlUtils.addListElement(xml, "publisher", dc_publisher.toList(), true, dc);
        XmlUtils.addListElement(xml, "source", dc_source.toList(), true, dc);
        XmlUtils.addListElement(xml, "isPartOf", dcterms_isPartOf.toList(), true, dcterms);

        XmlUtils.addListElement(xml, "alternative", dcterms_alternative.toList(), true, dcterms);

        XmlUtils.addListElement(xml, "format", dc_format.toList(), true, dc);
        XmlUtils.addListElement(xml, "identifier", dc_identifier.toList(), true, dc);
        XmlUtils.addListElement(xml, "relation", dc_relation.toList(), true, dc);
        XmlUtils.addListElement(xml, "rights", dc_rights.toList(), true, dc);

        XmlUtils.addListElement(xml, "extent", dcterms_extent.toList(), true, dcterms);

        XmlUtils.addListElement(xml, "unstored", europeana_unstored.toList(), true, europeana);
        XmlUtils.addTextElement(xml, "object", europeana_object, true, europeana);

        XmlUtils.addTextElement(xml, "provider", europeana_provider, europeana);
        if (europeana_type != null) {
            XmlUtils.addTextElement(xml, "type", europeana_type.toString(), europeana);
        }
        if (europeana_rights != null) {
            XmlUtils.addTextElement(xml, "rights", europeana_rights.toString(), europeana);
        }
        XmlUtils.addTextElement(xml, "dataProvider", europeana_dataProvider, true, europeana);

        if (europeana_isShownAt == null && europeana_isShownBy == null) {
            XmlUtils.addTextElement(xml, "isShownAt", europeana_isShownAt, europeana);
        } else {
            XmlUtils.addTextElement(xml, "isShownAt", europeana_isShownAt, true, europeana);
            XmlUtils.addTextElement(xml, "isShownBy", europeana_isShownBy, true, europeana);
        }

        XmlUtils.addTextElement(xml, "UGC", europeana_ugc, true, europeana);

        return xml;
    }

    public MultipleAttribute<String> getDc_title() {
        return dc_title;
    }

    public void setDc_title(MultipleAttribute<String> dc_title) {
        this.dc_title = dc_title;
    }

    public MultipleAttribute<String> getDc_description() {
        return dc_description;
    }

    public void setDc_description(MultipleAttribute<String> dc_description) {
        this.dc_description = dc_description;
    }

    public MultipleAttribute<String> getDc_language() {
        return dc_language;
    }

    public void setDc_language(MultipleAttribute<String> dc_language) {
        this.dc_language = dc_language;
    }

    public MultipleAttribute<String> getDc_subject() {
        return dc_subject;
    }

    public void setDc_subject(MultipleAttribute<String> dc_subject) {
        this.dc_subject = dc_subject;
    }

    public MultipleAttribute<String> getDc_type() {
        return dc_type;
    }

    public void setDc_type(MultipleAttribute<String> dc_type) {
        this.dc_type = dc_type;
    }

    public MultipleAttribute<String> getDc_coverage() {
        return dc_coverage;
    }

    public void setDc_coverage(MultipleAttribute<String> dc_coverage) {
        this.dc_coverage = dc_coverage;
    }

    public MultipleAttribute<String> getDcterms_spatial() {
        return dcterms_spatial;
    }

    public void setDcterms_spatial(MultipleAttribute<String> dcterms_spatial) {
        this.dcterms_spatial = dcterms_spatial;
    }

    public MultipleAttribute<String> getDc_creator() {
        return dc_creator;
    }

    public void setDc_creator(MultipleAttribute<String> dc_creator) {
        this.dc_creator = dc_creator;
    }

    public MultipleAttribute<String> getDc_contributor() {
        return dc_contributor;
    }

    public void setDc_contributor(MultipleAttribute<String> dc_contributor) {
        this.dc_contributor = dc_contributor;
    }

    public MultipleAttribute<String> getDc_date() {
        return dc_date;
    }

    public void setDc_date(MultipleAttribute<String> dc_date) {
        this.dc_date = dc_date;
    }

    public MultipleAttribute<String> getDc_publisher() {
        return dc_publisher;
    }

    public void setDc_publisher(MultipleAttribute<String> dc_publisher) {
        this.dc_publisher = dc_publisher;
    }

    public MultipleAttribute<String> getDc_source() {
        return dc_source;
    }

    public void setDc_source(MultipleAttribute<String> dc_source) {
        this.dc_source = dc_source;
    }

    public MultipleAttribute<String> getDcterms_isPartOf() {
        return dcterms_isPartOf;
    }

    public void setDcterms_isPartOf(MultipleAttribute<String> dcterms_isPartOf) {
        this.dcterms_isPartOf = dcterms_isPartOf;
    }

    public MultipleAttribute<String> getDcterms_alternative() {
        return dcterms_alternative;
    }

    public void setDcterms_alternative(MultipleAttribute<String> dcterms_alternative) {
        this.dcterms_alternative = dcterms_alternative;
    }

    public MultipleAttribute<String> getDcterms_created() {
        return dcterms_created;
    }

    public void setDcterms_created(MultipleAttribute<String> dcterms_created) {
        this.dcterms_created = dcterms_created;
    }

    public MultipleAttribute<String> getDcterms_issued() {
        return dcterms_issued;
    }

    public void setDcterms_issued(MultipleAttribute<String> dcterms_issued) {
        this.dcterms_issued = dcterms_issued;
    }

    public MultipleAttribute<String> getDcterms_temporal() {
        return dcterms_temporal;
    }

    public void setDcterms_temporal(MultipleAttribute<String> dcterms_temporal) {
        this.dcterms_temporal = dcterms_temporal;
    }

    public MultipleAttribute<String> getDc_format() {
        return dc_format;
    }

    public void setDc_format(MultipleAttribute<String> dc_format) {
        this.dc_format = dc_format;
    }

    public MultipleAttribute<String> getDc_identifier() {
        return dc_identifier;
    }

    public void setDc_identifier(MultipleAttribute<String> dc_identifier) {
        this.dc_identifier = dc_identifier;
    }

    public MultipleAttribute<String> getDc_relation() {
        return dc_relation;
    }

    public void setDc_relation(MultipleAttribute<String> dc_relation) {
        this.dc_relation = dc_relation;
    }

    public MultipleAttribute<String> getDc_rights() {
        return dc_rights;
    }

    public void setDc_rights(MultipleAttribute<String> dc_rights) {
        this.dc_rights = dc_rights;
    }

    public MultipleAttribute<String> getEuropeana_year() {
        return europeana_year;
    }

    public void setEuropeana_year(MultipleAttribute<String> europeana_year) {
        this.europeana_year = europeana_year;
    }

    public MultipleAttribute<String> getEuropeana_unstored() {
        return europeana_unstored;
    }

    public void setEuropeana_unstored(MultipleAttribute<String> europeana_unstored) {
        this.europeana_unstored = europeana_unstored;
    }

    public String getEuropeana_object() {
        return europeana_object;
    }

    public void setEuropeana_object(String europeana_object) {
        this.europeana_object = europeana_object;
    }

    public String getEuropeana_provider() {
        return europeana_provider;
    }

    public void setEuropeana_provider(String europeana_provider) {
        this.europeana_provider = europeana_provider;
    }

    public EUType getEuropeana_type() {
        return europeana_type;
    }

    public void setEuropeana_type(EUType europeana_type) {
        this.europeana_type = europeana_type;
    }

    public EURights getEuropeana_rights() {
        return europeana_rights;
    }

    public void setEuropeana_rights(EURights europeana_rights) {
        this.europeana_rights = europeana_rights;
    }

    public String getEuropeana_dataProvider() {
        return europeana_dataProvider;
    }

    public void setEuropeana_dataProvider(String europeana_dataProvider) {
        this.europeana_dataProvider = europeana_dataProvider;
    }

    public String getEuropeana_isShownAt() {
        return europeana_isShownAt;
    }

    public void setEuropeana_isShownAt(String europeana_isShownAt) {
        this.europeana_isShownAt = europeana_isShownAt;
    }

    public String getEuropeana_isShownBy() {
        return europeana_isShownBy;
    }

    public void setEuropeana_isShownBy(String europeana_isShownBy) {
        this.europeana_isShownBy = europeana_isShownBy;
    }

    public String getEuropeana_ugc() {
        return europeana_ugc;
    }

    public void setEuropeana_ugc(String europeana_ugc) {
        this.europeana_ugc = europeana_ugc;
    }

    public MultipleAttribute<String> getDcterms_extent() {
        return dcterms_extent;
    }

    public void setDcterms_extent(MultipleAttribute<String> dcterms_extent) {
        this.dcterms_extent = dcterms_extent;
    }

}
