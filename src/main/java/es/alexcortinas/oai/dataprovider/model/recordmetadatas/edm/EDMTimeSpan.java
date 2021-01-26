package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class EDMTimeSpan implements IContextualClassEDM {

	private String skos_prefLabel;
	private MultipleAttribute<String> skos_altLabel = new MultipleAttribute<String>();
	private String edm_begin;
	private String edm_end;

	public EDMTimeSpan() {
		super();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace edm = Namespace.getNamespace("edm", "http://www.europeana.eu/schemas/edm/");
		Namespace skos = Namespace.getNamespace("skos", "http://www.w3.org/2004/02/skos/core#");

		Element xml = new Element("TimeSpan", edm);

		XmlUtils.addTextElement(xml, "prefLabel", skos_prefLabel, skos);
		XmlUtils.addListElement(xml, "altLabel", skos_altLabel.toList(), true, skos);
		XmlUtils.addTextElement(xml, "begin", edm_begin, edm);
		XmlUtils.addTextElement(xml, "end", edm_end, edm);

		return xml;
	}

	public String getSkos_prefLabel() {
		return skos_prefLabel;
	}

	public void setSkos_prefLabel(String skos_prefLabel) {
		this.skos_prefLabel = skos_prefLabel;
	}

	public MultipleAttribute<String> getSkos_altLabel() {
		return skos_altLabel;
	}

	public void setSkos_altLabel(MultipleAttribute<String> skos_altLabel) {
		this.skos_altLabel = skos_altLabel;
	}

	public String getEdm_begin() {
		return edm_begin;
	}

	public void setEdm_begin(String edm_begin) {
		this.edm_begin = edm_begin;
	}

	public String getEdm_end() {
		return edm_end;
	}

	public void setEdm_end(String edm_end) {
		this.edm_end = edm_end;
	}

}
