package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class OREAggregation {

	// Mandatory. Resource identifier
	private String rdf_about;

	private EDMProvidedCHO edm_aggregatedCHO; // Mandatory
	private String edm_provider; // Mandatory
	private String edm_dataProvider; // Mandatory
	private EDMRights edm_rights; // Mandatory
	private String edm_ugc; // [0,1]
	private String edm_object; // [0,1]

	// Mandatory if edm_isShownBy is null
	private String edm_isShownAt;
	// Mandatory if edm_isShownAt is null
	private String edm_isShownBy;

	private MultipleAttribute<String> edm_hasView = new MultipleAttribute<String>();

	public OREAggregation() {
		super();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace edm = Namespace.getNamespace("edm", "http://www.europeana.eu/schemas/edm/");
		Namespace ore = Namespace.getNamespace("ore", "http://www.openarchives.org/ore/terms/");
		Namespace rdf = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");

		Element xml = new Element("Aggregation", ore);
		xml.setAttribute("about", rdf_about, rdf);

		Element aggregatedCHO = new Element("aggregatedCHO", edm);
		if (edm_aggregatedCHO != null) {
			aggregatedCHO.setAttribute("resource", edm_aggregatedCHO.getRdf_about(), rdf);
		} else {
			aggregatedCHO.setAttribute("resource", null, rdf);
		}
		xml.addContent(aggregatedCHO);

		XmlUtils.addTextElement(xml, "provider", edm_provider, edm);
		XmlUtils.addTextElement(xml, "dataProvider", edm_dataProvider, edm);

		if (edm_rights != null) {
			XmlUtils.addTextElement(xml, "rights", edm_rights.toString(), edm);
		} else {
			XmlUtils.addTextElement(xml, "rights", null, edm);
		}

		XmlUtils.addTextElement(xml, "UGC", edm_ugc, true, edm);
		XmlUtils.addTextElement(xml, "object", edm_object, true, edm);

		if (edm_isShownAt == null && edm_isShownBy == null) {
			XmlUtils.addTextElement(xml, "isShownAt", edm_isShownAt, edm);
		} else {
			XmlUtils.addTextElement(xml, "isShownAt", edm_isShownAt, true, edm);
			XmlUtils.addTextElement(xml, "isShownBy", edm_isShownBy, true, edm);
		}

		if (edm_hasView != null) {
			for (String view : edm_hasView.toList()) {
				Element hasView = new Element("hasView", edm);
				hasView.setAttribute("resource", view, rdf);
				xml.addContent(hasView);
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

	public EDMProvidedCHO getEdm_aggregatedCHO() {
		return edm_aggregatedCHO;
	}

	public void setEdm_aggregatedCHO(EDMProvidedCHO edm_aggregatedCHO) {
		this.edm_aggregatedCHO = edm_aggregatedCHO;
	}

	public String getEdm_provider() {
		return edm_provider;
	}

	public void setEdm_provider(String edm_provider) {
		this.edm_provider = edm_provider;
	}

	public String getEdm_dataProvider() {
		return edm_dataProvider;
	}

	public void setEdm_dataProvider(String edm_dataProvider) {
		this.edm_dataProvider = edm_dataProvider;
	}

	public EDMRights getEdm_rights() {
		return edm_rights;
	}

	public void setEdm_rights(EDMRights edm_rights) {
		this.edm_rights = edm_rights;
	}

	public String getEdm_ugc() {
		return edm_ugc;
	}

	public void setEdm_ugc(String edm_ugc) {
		this.edm_ugc = edm_ugc;
	}

	public String getEdm_object() {
		return edm_object;
	}

	public void setEdm_object(String edm_object) {
		this.edm_object = edm_object;
	}

	public String getEdm_isShownAt() {
		return edm_isShownAt;
	}

	public void setEdm_isShownAt(String edm_isShownAt) {
		this.edm_isShownAt = edm_isShownAt;
	}

	public String getEdm_isShownBy() {
		return edm_isShownBy;
	}

	public void setEdm_isShownBy(String edm_isShownBy) {
		this.edm_isShownBy = edm_isShownBy;
	}

	public MultipleAttribute<String> getEdm_hasView() {
		return edm_hasView;
	}

	public void setEdm_hasView(MultipleAttribute<String> edm_hasView) {
		this.edm_hasView = edm_hasView;
	}

}
