package es.alexcortinas.oai.dataprovider.responses;

import org.jdom2.Document;

import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.Identify;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class IdentifyResponse extends OAIResponse implements IOAIResponse {
	private Identify identify;

	public IdentifyResponse(OAIRequest request, Identify identify) {
		super(request, identify.getBaseURL(), identify.getGranularity());
		this.setIdentify(identify);
	}

	public Document getJdomDocument() {
		Document doc = XmlUtils.getXmlResponseTemplate(getRequest(),
				getBaseURL());

		doc.getRootElement().addContent(identify.toJdomElement());

		return doc;
	}

	public Identify getIdentify() {
		return identify;
	}

	public void setIdentify(Identify identify) {
		this.identify = identify;
	}

}
