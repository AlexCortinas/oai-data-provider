package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.RecordMetadataDC;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class EDMWebResource {

	// Mandatory. Resource identifier
	private String rdf_about;

	private MultipleAttribute<String> dc_rights = new MultipleAttribute<String>();
	private EDMRights edm_rights;

	private ComplexAttribute<String> dc_creator = new ComplexAttribute<String>(new EDMAgent());
	private MultipleAttribute<String> dc_description = new MultipleAttribute<String>();
	private MultipleAttribute<String> dc_format = new MultipleAttribute<String>();
	private MultipleAttribute<String> dc_source = new MultipleAttribute<String>();
	private ComplexAttribute<String> dc_type = new ComplexAttribute<String>(new SKOSConcept());

	private ComplexAttribute<String> dcterms_created = new ComplexAttribute<String>(new EDMTimeSpan());
	private MultipleAttribute<String> dcterms_extent = new MultipleAttribute<String>();
	private MultipleAttribute<String> dcterms_isPartOf = new MultipleAttribute<String>();
	private ComplexAttribute<String> dcterms_issued = new ComplexAttribute<String>(new EDMTimeSpan());

	public EDMWebResource() {
		super();
	}

	public EDMWebResource(RecordMetadataDC dc) {
		this();
		this.dc_rights = dc.getRights();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace dc = Namespace.getNamespace("dc", "http://purl.org/dc/elements/1.1/");
		Namespace edm = Namespace.getNamespace("edm", "http://www.europeana.eu/schemas/edm/");
		Namespace rdf = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		Namespace dcterms = Namespace.getNamespace("dcterms", "http://purl.org/dc/terms/");

		Element xml = new Element("WebResource", edm);
		xml.setAttribute("about", rdf_about, rdf);

		if (edm_rights != null) {
			XmlUtils.addTextElement(xml, "rights", edm_rights.toString(), true, edm);
		}
		XmlUtils.addListElement(xml, "rights", dc_rights.toList(), true, dc);

		XmlUtils.addListElement(xml, "creator", dc_creator.getValues().toList(), true, dc);
		XmlUtils.addListElement(xml, "description", dc_description.toList(), true, dc);
		XmlUtils.addListElement(xml, "source", dc_source.toList(), true, dc);
		XmlUtils.addListElement(xml, "format", dc_format.toList(), true, dc);
		XmlUtils.addListElement(xml, "type", dc_type.getValues().toList(), true, dc);

		XmlUtils.addListElement(xml, "created", dcterms_created.getValues().toList(), true, dcterms);
		XmlUtils.addListElement(xml, "issued", dcterms_issued.getValues().toList(), true, dcterms);
		XmlUtils.addListElement(xml, "extent", dcterms_extent.toList(), true, dcterms);

		if (dcterms_isPartOf != null) {
			for (String resource : dcterms_isPartOf.toList()) {
				Element isPartOf = new Element("isPartOf", dcterms);
				isPartOf.setAttribute("resource", resource, rdf);
				xml.addContent(isPartOf);
			}
		}

		return xml;
	}

	public String getRdf_about() {
		return rdf_about;
	}

	public void setRdf_about(String rdf_about) {
		this.rdf_about = rdf_about;
	}

	public MultipleAttribute<String> getDc_rights() {
		return dc_rights;
	}

	public void setDc_rights(MultipleAttribute<String> dc_rights) {
		this.dc_rights = dc_rights;
	}

	public EDMRights getEdm_rights() {
		return edm_rights;
	}

	public void setEdm_rights(EDMRights edm_rights) {
		this.edm_rights = edm_rights;
	}

	public ComplexAttribute<String> getDc_creator() {
		return dc_creator;
	}

	public void setDc_creator(ComplexAttribute<String> dc_creator) {
		this.dc_creator = dc_creator;
	}

	public MultipleAttribute<String> getDc_description() {
		return dc_description;
	}

	public void setDc_description(MultipleAttribute<String> dc_description) {
		this.dc_description = dc_description;
	}

	public MultipleAttribute<String> getDc_format() {
		return dc_format;
	}

	public void setDc_format(MultipleAttribute<String> dc_format) {
		this.dc_format = dc_format;
	}

	public MultipleAttribute<String> getDc_source() {
		return dc_source;
	}

	public void setDc_source(MultipleAttribute<String> dc_source) {
		this.dc_source = dc_source;
	}

	public ComplexAttribute<String> getDc_type() {
		return dc_type;
	}

	public void setDc_type(ComplexAttribute<String> dc_type) {
		this.dc_type = dc_type;
	}

	public ComplexAttribute<String> getDcterms_created() {
		return dcterms_created;
	}

	public void setDcterms_created(ComplexAttribute<String> dcterms_created) {
		this.dcterms_created = dcterms_created;
	}

	public MultipleAttribute<String> getDcterms_extent() {
		return dcterms_extent;
	}

	public void setDcterms_extent(MultipleAttribute<String> dcterms_extent) {
		this.dcterms_extent = dcterms_extent;
	}

	public MultipleAttribute<String> getDcterms_isPartOf() {
		return dcterms_isPartOf;
	}

	public void setDcterms_isPartOf(MultipleAttribute<String> dcterms_isPartOf) {
		this.dcterms_isPartOf = dcterms_isPartOf;
	}

	public ComplexAttribute<String> getDcterms_issued() {
		return dcterms_issued;
	}

	public void setDcterms_issued(ComplexAttribute<String> dcterms_issued) {
		this.dcterms_issued = dcterms_issued;
	}

}
