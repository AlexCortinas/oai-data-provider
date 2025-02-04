package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class CCLicense implements IContextualClassEDM {

	private String odrl_inheritFrom; // Mandatory

	public CCLicense() {
		super();
	}

	public Element toJdomElement(Granularity granularity) {
		Namespace cc = Namespace.getNamespace("cc", "https://creativecommons.org/ns#");
		Namespace odrl = Namespace.getNamespace("odrl", "http://www.w3.org/ns/odrl/2/");

		Element xml = new Element("License", cc);

		XmlUtils.addTextElement(xml, "inheritFrom", odrl_inheritFrom, odrl);

		return xml;
	}

	public String getOdrl_inheritFrom() {
		return odrl_inheritFrom;
	}

	public void setOdrl_inheritFrom(String odrl_inheritFrom) {
		this.odrl_inheritFrom = odrl_inheritFrom;
	}

}
