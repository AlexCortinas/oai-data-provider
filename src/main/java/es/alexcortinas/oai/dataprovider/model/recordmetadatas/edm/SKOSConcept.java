package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class SKOSConcept implements IContextualClassEDM {

	private String skos_prefLabel;
	private MultipleAttribute<String> skos_altLabel = new MultipleAttribute<String>();

	public SKOSConcept() {
		super();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace skos = Namespace.getNamespace("skos", "http://www.w3.org/2004/02/skos/core#");

		Element xml = new Element("Concept", skos);

		XmlUtils.addTextElement(xml, "prefLabel", skos_prefLabel, skos);
		XmlUtils.addListElement(xml, "altLabel", skos_altLabel.toList(), true, skos);

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

}
