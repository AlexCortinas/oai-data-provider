package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class EDMAgent implements IContextualClassEDM {

	private String skos_prefLabel;
	private MultipleAttribute<String> skos_altLabel = new MultipleAttribute<String>();
	private String rdau_dateOfBirth;
	private String rdau_dateOfDeath;
	private String rdau_placeOfBirth;
	private String rdau_placeOfDeath;
	private String rdau_gender;
	private MultipleAttribute<String> rdau_professionOrOccupation = new MultipleAttribute<String>();

	public EDMAgent() {
		super();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace edm = Namespace.getNamespace("edm", "http://www.europeana.eu/schemas/edm/");
		Namespace skos = Namespace.getNamespace("skos", "http://www.w3.org/2004/02/skos/core#");
		Namespace rdau = Namespace.getNamespace("rdau", "http://www.rdaregistry.info/Elements/u/");

		Element xml = new Element("Agent", edm);

		XmlUtils.addTextElement(xml, "prefLabel", skos_prefLabel, skos);
		XmlUtils.addListElement(xml, "altLabel", skos_altLabel.toList(), true, skos);
		XmlUtils.addTextElement(xml, "dateOfBirth", rdau_dateOfBirth, true, rdau);
		XmlUtils.addTextElement(xml, "dateOfDeath", rdau_dateOfDeath, true, rdau);
		XmlUtils.addTextElement(xml, "placeOfBirth", rdau_placeOfBirth, true, rdau);
		XmlUtils.addTextElement(xml, "placeOfDeath", rdau_placeOfDeath, true, rdau);
		XmlUtils.addTextElement(xml, "gender", rdau_gender, true, rdau);
		XmlUtils.addListElement(xml, "professionOrOccupation", rdau_professionOrOccupation.toList(), true, rdau);

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

	public String getRdau_dateOfBirth() {
		return rdau_dateOfBirth;
	}

	public void setRdau_dateOfBirth(String rdau_dateOfBirth) {
		this.rdau_dateOfBirth = rdau_dateOfBirth;
	}

	public String getRdau_dateOfDeath() {
		return rdau_dateOfDeath;
	}

	public void setRdau_dateOfDeath(String rdau_dateOfDeath) {
		this.rdau_dateOfDeath = rdau_dateOfDeath;
	}

	public String getRdau_placeOfBirth() {
		return rdau_placeOfBirth;
	}

	public void setRdau_placeOfBirth(String rdau_placeOfBirth) {
		this.rdau_placeOfBirth = rdau_placeOfBirth;
	}

	public String getRdau_placeOfDeath() {
		return rdau_placeOfDeath;
	}

	public void setRdau_placeOfDeath(String rdau_placeOfDeath) {
		this.rdau_placeOfDeath = rdau_placeOfDeath;
	}

	public String getRdau_gender() {
		return rdau_gender;
	}

	public void setRdau_gender(String rdau_gender) {
		this.rdau_gender = rdau_gender;
	}

	public MultipleAttribute<String> getRdau_professionOrOccupation() {
		return rdau_professionOrOccupation;
	}

	public void setRdau_professionOrOccupation(MultipleAttribute<String> rdau_professionOrOccupation) {
		this.rdau_professionOrOccupation = rdau_professionOrOccupation;
	}

}
