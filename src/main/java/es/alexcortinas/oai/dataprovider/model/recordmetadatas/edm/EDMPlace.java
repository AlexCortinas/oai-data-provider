package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class EDMPlace implements IContextualClassEDM {

	private String wgs84_pos_lat;
	private String wgs84_pos_long;
	private String skos_prefLabel;
	private MultipleAttribute<String> skos_altLabel = new MultipleAttribute<String>();

	public EDMPlace() {
		super();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace edm = Namespace.getNamespace("edm", "http://www.europeana.eu/schemas/edm/");
		Namespace skos = Namespace.getNamespace("skos", "http://www.w3.org/2004/02/skos/core#");
		Namespace wgs84_pos = Namespace.getNamespace("wgs84_pos", "http://www.w3.org/2003/01/geo/wgs84_pos#");

		Element xml = new Element("Place", edm);

		XmlUtils.addTextElement(xml, "pos_lat", wgs84_pos_lat, true, wgs84_pos);
		XmlUtils.addTextElement(xml, "pos_long", wgs84_pos_long, true, wgs84_pos);
		XmlUtils.addTextElement(xml, "prefLabel", skos_prefLabel, skos);
		XmlUtils.addListElement(xml, "altLabel", skos_altLabel.toList(), true, skos);

		return xml;
	}

	public String getWgs84_pos_lat() {
		return wgs84_pos_lat;
	}

	public void setWgs84_pos_lat(String wgs84_pos_lat) {
		this.wgs84_pos_lat = wgs84_pos_lat;
	}

	public String getWgs84_pos_long() {
		return wgs84_pos_long;
	}

	public void setWgs84_pos_long(String wgs84_pos_long) {
		this.wgs84_pos_long = wgs84_pos_long;
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
