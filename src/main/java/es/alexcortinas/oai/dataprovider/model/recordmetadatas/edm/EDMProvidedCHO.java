package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.RecordMetadataDC;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class EDMProvidedCHO {

	// Mandatory. Resource identifier
	private String rdf_about;
	// Mandatory if dc_description is null
	private MultipleAttribute<String> dc_title = new MultipleAttribute<String>();
	// Mandatory if dc_title is null
	private MultipleAttribute<String> dc_description = new MultipleAttribute<String>();
	// Mandatory if edm_type is TEXT
	private MultipleAttribute<String> dc_language = new MultipleAttribute<String>();

	// One at least of the four next is mandatory
	private ComplexAttribute<String> dc_subject = new ComplexAttribute<String>(new SKOSConcept());
	private ComplexAttribute<String> dc_type = new ComplexAttribute<String>(new SKOSConcept());
	private ComplexAttribute<String> dcterms_spatial = new ComplexAttribute<String>(new EDMTimeSpan());
	private ComplexAttribute<String> dcterms_temporal = new ComplexAttribute<String>(new EDMTimeSpan());

	private ComplexAttribute<String> dc_coverage = new ComplexAttribute<String>(new EDMPlace());
	private ComplexAttribute<String> dc_creator = new ComplexAttribute<String>(new EDMAgent());
	private ComplexAttribute<String> dc_contributor = new ComplexAttribute<String>(new EDMAgent());
	private ComplexAttribute<String> dc_date = new ComplexAttribute<String>(new EDMTimeSpan());
	private ComplexAttribute<String> dc_publisher = new ComplexAttribute<String>(new EDMAgent());
	private MultipleAttribute<String> dc_source = new MultipleAttribute<String>();
	private MultipleAttribute<String> dc_identifier = new MultipleAttribute<String>();
	private MultipleAttribute<String> dc_format = new MultipleAttribute<String>();
	private MultipleAttribute<String> dc_relation = new MultipleAttribute<String>();
	private MultipleAttribute<String> dc_rights = new MultipleAttribute<String>();

	private MultipleAttribute<String> dcterms_isPartOf = new MultipleAttribute<String>();
	private MultipleAttribute<String> dcterms_alternative = new MultipleAttribute<String>();
	private ComplexAttribute<String> dcterms_created = new ComplexAttribute<String>(new EDMTimeSpan());
	private ComplexAttribute<String> dcterms_issued = new ComplexAttribute<String>(new EDMTimeSpan());
	private MultipleAttribute<String> dcterms_extent = new MultipleAttribute<String>();

	private EDMType edm_type; // Mandatory
	private MultipleAttribute<String> edm_isNextInSequence = new MultipleAttribute<String>();

	public EDMProvidedCHO() {
		super();
	}

	public EDMProvidedCHO(RecordMetadataDC dc) {
		this();
		this.dc_title = dc.getTitle();
		this.dc_description = dc.getDescription();
		this.dc_language = dc.getLanguage();

		this.dc_subject.setValues(dc.getSubject());
		this.dc_type.setValues(dc.getType());
		this.dc_coverage.setValues(dc.getCoverage());

		this.dc_creator.setValues(dc.getCreator());
		this.dc_contributor.setValues(dc.getContributor());
		this.dc_date.setValues(dc.getDate());
		this.dc_publisher.setValues(dc.getPublisher());
		this.dc_source = dc.getSource();
		this.dc_identifier = dc.getIdentifier();
		this.dc_format = dc.getFormat();
		this.dc_relation = dc.getRelation();
		this.dc_rights = dc.getRights();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace dc = Namespace.getNamespace("dc", "http://purl.org/dc/elements/1.1/");
		Namespace dcterms = Namespace.getNamespace("dcterms", "http://purl.org/dc/terms/");
		Namespace edm = Namespace.getNamespace("edm", "http://www.europeana.eu/schemas/edm/");
		Namespace rdf = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");

		Element xml = new Element("ProvidedCHO", edm);
		xml.setAttribute("about", rdf_about, rdf);

		if (!dc_title.hasValues() && !dc_description.hasValues()) {
			XmlUtils.addTextElement(xml, "title", null, dc);
		} else {
			XmlUtils.addListElement(xml, "title", dc_title.toList(), true, dc);
			XmlUtils.addListElement(xml, "description", dc_description.toList(), true, dc);
		}

		if (edm_type != null && edm_type == EDMType.TEXT && !dc_language.hasValues()) {
			XmlUtils.addTextElement(xml, "language", null, dc);
		} else {
			XmlUtils.addListElement(xml, "language", dc_language.toList(), true, dc);
		}

		if (!dc_subject.getValues().hasValues() && !dc_type.getValues().hasValues()
				&& !dcterms_temporal.getValues().hasValues() && !dcterms_spatial.getValues().hasValues()) {
			XmlUtils.addTextElement(xml, "subject", null, dc);
		} else {
			XmlUtils.addListElement(xml, "subject", dc_subject.getValues().toList(), true, dc);
			XmlUtils.addListElement(xml, "type", dc_type.getValues().toList(), true, dc);
			XmlUtils.addListElement(xml, "temporal", dcterms_temporal.getValues().toList(), true, dcterms);
			XmlUtils.addListElement(xml, "spatial", dcterms_spatial.getValues().toList(), true, dcterms);
		}

		XmlUtils.addListElement(xml, "coverage", dc_coverage.getValues().toList(), true, dc);

		XmlUtils.addListElement(xml, "creator", dc_creator.getValues().toList(), true, dc);
		XmlUtils.addListElement(xml, "contributor", dc_contributor.getValues().toList(), true, dc);
		XmlUtils.addListElement(xml, "date", dc_date.getValues().toList(), true, dc);
		XmlUtils.addListElement(xml, "publisher", dc_publisher.getValues().toList(), true, dc);
		XmlUtils.addListElement(xml, "source", dc_source.toList(), true, dc);
		XmlUtils.addListElement(xml, "identifier", dc_identifier.toList(), true, dc);
		XmlUtils.addListElement(xml, "format", dc_format.toList(), true, dc);
		XmlUtils.addListElement(xml, "relation", dc_relation.toList(), true, dc);
		XmlUtils.addListElement(xml, "rights", dc_rights.toList(), true, dc);

		XmlUtils.addListElement(xml, "isPartOf", dcterms_isPartOf.toList(), true, dcterms);
		XmlUtils.addListElement(xml, "alternative", dcterms_alternative.toList(), true, dcterms);
		XmlUtils.addListElement(xml, "created", dcterms_created.getValues().toList(), true, dcterms);
		XmlUtils.addListElement(xml, "issued", dcterms_issued.getValues().toList(), true, dcterms);
		XmlUtils.addListElement(xml, "extent", dcterms_extent.toList(), true, dcterms);

		if (edm_type != null) {
			XmlUtils.addTextElement(xml, "type", edm_type.toString(), edm);
		} else {
			XmlUtils.addTextElement(xml, "type", null, edm);
		}
		XmlUtils.addListElement(xml, "isNextInSequence", edm_isNextInSequence.toList(), true, edm);

		return xml;
	}

	public String getRdf_about() {
		return rdf_about;
	}

	public void setRdf_about(String rdf_about) {
		this.rdf_about = rdf_about;
	}

	public MultipleAttribute<String> getDc_language() {
		return dc_language;
	}

	public void setDc_language(MultipleAttribute<String> dc_language) {
		this.dc_language = dc_language;
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

	public ComplexAttribute<String> getDc_subject() {
		return dc_subject;
	}

	public void setDc_subject(ComplexAttribute<String> dc_subject) {
		this.dc_subject = dc_subject;
	}

	public ComplexAttribute<String> getDc_type() {
		return dc_type;
	}

	public void setDc_type(ComplexAttribute<String> dc_type) {
		this.dc_type = dc_type;
	}

	public ComplexAttribute<String> getDcterms_spatial() {
		return dcterms_spatial;
	}

	public void setDcterms_spatial(ComplexAttribute<String> dcterms_spatial) {
		this.dcterms_spatial = dcterms_spatial;
	}

	public ComplexAttribute<String> getDcterms_temporal() {
		return dcterms_temporal;
	}

	public void setDcterms_temporal(ComplexAttribute<String> dcterms_temporal) {
		this.dcterms_temporal = dcterms_temporal;
	}

	public ComplexAttribute<String> getDc_coverage() {
		return dc_coverage;
	}

	public void setDc_coverage(ComplexAttribute<String> dc_coverage) {
		this.dc_coverage = dc_coverage;
	}

	public ComplexAttribute<String> getDc_creator() {
		return dc_creator;
	}

	public void setDc_creator(ComplexAttribute<String> dc_creator) {
		this.dc_creator = dc_creator;
	}

	public ComplexAttribute<String> getDc_contributor() {
		return dc_contributor;
	}

	public void setDc_contributor(ComplexAttribute<String> dc_contributor) {
		this.dc_contributor = dc_contributor;
	}

	public ComplexAttribute<String> getDc_date() {
		return dc_date;
	}

	public void setDc_date(ComplexAttribute<String> dc_date) {
		this.dc_date = dc_date;
	}

	public ComplexAttribute<String> getDc_publisher() {
		return dc_publisher;
	}

	public void setDc_publisher(ComplexAttribute<String> dc_publisher) {
		this.dc_publisher = dc_publisher;
	}

	public MultipleAttribute<String> getDc_source() {
		return dc_source;
	}

	public void setDc_source(MultipleAttribute<String> dc_source) {
		this.dc_source = dc_source;
	}

	public MultipleAttribute<String> getDc_identifier() {
		return dc_identifier;
	}

	public void setDc_identifier(MultipleAttribute<String> dc_identifier) {
		this.dc_identifier = dc_identifier;
	}

	public MultipleAttribute<String> getDc_format() {
		return dc_format;
	}

	public void setDc_format(MultipleAttribute<String> dc_format) {
		this.dc_format = dc_format;
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

	public ComplexAttribute<String> getDcterms_created() {
		return dcterms_created;
	}

	public void setDcterms_created(ComplexAttribute<String> dcterms_created) {
		this.dcterms_created = dcterms_created;
	}

	public ComplexAttribute<String> getDcterms_issued() {
		return dcterms_issued;
	}

	public void setDcterms_issued(ComplexAttribute<String> dcterms_issued) {
		this.dcterms_issued = dcterms_issued;
	}

	public MultipleAttribute<String> getDcterms_extent() {
		return dcterms_extent;
	}

	public void setDcterms_extent(MultipleAttribute<String> dcterms_extent) {
		this.dcterms_extent = dcterms_extent;
	}

	public EDMType getEdm_type() {
		return edm_type;
	}

	public void setEdm_type(EDMType edm_type) {
		this.edm_type = edm_type;
	}

	public MultipleAttribute<String> getEdm_isNextInSequence() {
		return edm_isNextInSequence;
	}

	public void setEdm_isNextInSequence(MultipleAttribute<String> edm_isNextInSequence) {
		this.edm_isNextInSequence = edm_isNextInSequence;
	}

}
