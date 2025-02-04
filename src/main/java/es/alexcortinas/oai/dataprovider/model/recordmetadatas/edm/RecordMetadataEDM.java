package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import java.util.List;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.recordmetadatas.IRecordMetadata;

public class RecordMetadataEDM implements IRecordMetadata {

	private EDMProvidedCHO providerCho;
	private OREAggregation aggregation;
	private List<EDMWebResource> webResource;
	private List<EDMAgent> agent;
	private List<EDMPlace> place;
	private List<EDMTimeSpan> timeSpan;
	private List<SKOSConcept> concept;
	private List<CCLicense> license;

	public RecordMetadataEDM() {
		super();
	}

	public RecordMetadataEDM(EDMProvidedCHO providerCho, OREAggregation aggregation, List<EDMWebResource> webResource,
			List<EDMAgent> agent, List<EDMPlace> place, List<EDMTimeSpan> timeSpan, List<SKOSConcept> concept,
			List<CCLicense> license) {
		super();
		this.providerCho = providerCho;
		this.aggregation = aggregation;
		this.webResource = webResource;
		this.agent = agent;
		this.place = place;
		this.timeSpan = timeSpan;
		this.concept = concept;
		this.license = license;
	}

	@Override
	public Element toJdomElement(Granularity granularity) {
		Namespace dc = Namespace.getNamespace("dc", "http://purl.org/dc/elements/1.1/");
		Namespace dcterms = Namespace.getNamespace("dcterms", "http://purl.org/dc/terms/");
		Namespace edm = Namespace.getNamespace("edm", "http://www.europeana.eu/schemas/edm/");
		Namespace ore = Namespace.getNamespace("ore", "http://www.openarchives.org/ore/terms/");
		Namespace owl = Namespace.getNamespace("owl", "http://www.w3.org/2002/07/owl#");
		Namespace rdf = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		Namespace foaf = Namespace.getNamespace("foaf", "http://xmlns.com/foaf/0.1/");
		Namespace skos = Namespace.getNamespace("skos", "http://www.w3.org/2004/02/skos/core#");
		Namespace rdau = Namespace.getNamespace("rdau", "http://www.rdaregistry.info/Elements/u/");
		Namespace wgs84_pos = Namespace.getNamespace("wgs84_pos", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		Namespace crm = Namespace.getNamespace("crm", "http://www.cidoc-crm.org/rdfs/cidoc-crm#");
		Namespace cc = Namespace.getNamespace("cc", "https://creativecommons.org/ns#");
		Namespace odrl = Namespace.getNamespace("odrl", "http://www.w3.org/ns/odrl/2/");

		Element xml = new Element("RDF", rdf);
		xml.addNamespaceDeclaration(dc);
		xml.addNamespaceDeclaration(dcterms);
		xml.addNamespaceDeclaration(edm);
		xml.addNamespaceDeclaration(ore);
		xml.addNamespaceDeclaration(owl);
		xml.addNamespaceDeclaration(rdf);
		xml.addNamespaceDeclaration(foaf);
		xml.addNamespaceDeclaration(skos);
		xml.addNamespaceDeclaration(rdau);
		xml.addNamespaceDeclaration(wgs84_pos);
		xml.addNamespaceDeclaration(crm);
		xml.addNamespaceDeclaration(cc);
		xml.addNamespaceDeclaration(odrl);
		xml.setAttribute("schemaLocation",
				"http://www.w3.org/1999/02/22-rdf-syntax-ns# " + "http://www.europeana.eu/schemas/edm/EDM.xsd", rdf);

		if (this.providerCho != null) {
			xml.addContent(this.providerCho.toJdomElement(granularity));
		}

		if (this.webResource != null) {
			for (EDMWebResource resource : this.webResource) {
				xml.addContent(resource.toJdomElement(granularity));
			}
		}

		if (this.agent != null) {
			for (EDMAgent agent : this.agent) {
				xml.addContent(agent.toJdomElement(granularity));
			}
		}

		if (this.place != null) {
			for (EDMPlace place : this.place) {
				xml.addContent(place.toJdomElement(granularity));
			}
		}

		if (this.timeSpan != null) {
			for (EDMTimeSpan timeSpan : this.timeSpan) {
				xml.addContent(timeSpan.toJdomElement(granularity));
			}
		}

		if (this.concept != null) {
			for (SKOSConcept concept : this.concept) {
				xml.addContent(concept.toJdomElement(granularity));
			}
		}

		if (this.license != null) {
			for (CCLicense license : this.license) {
				xml.addContent(license.toJdomElement(granularity));
			}
		}

		if (this.aggregation != null) {
			xml.addContent(this.aggregation.toJdomElement(granularity));
		}

		return xml;
	}

	public EDMProvidedCHO getProviderCho() {
		return providerCho;
	}

	public void setProviderCho(EDMProvidedCHO providerCho) {
		this.providerCho = providerCho;
	}

	public OREAggregation getAggregation() {
		return aggregation;
	}

	public void setAggregation(OREAggregation aggregation) {
		this.aggregation = aggregation;
	}

	public List<EDMWebResource> getWebResource() {
		return webResource;
	}

	public void setWebResource(List<EDMWebResource> webResource) {
		this.webResource = webResource;
	}

	public List<EDMAgent> getAgent() {
		return agent;
	}

	public void setAgent(List<EDMAgent> agent) {
		this.agent = agent;
	}

	public List<EDMPlace> getPlace() {
		return place;
	}

	public void setPlace(List<EDMPlace> place) {
		this.place = place;
	}

	public List<EDMTimeSpan> getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(List<EDMTimeSpan> timeSpan) {
		this.timeSpan = timeSpan;
	}

	public List<SKOSConcept> getConcept() {
		return concept;
	}

	public void setConcept(List<SKOSConcept> concept) {
		this.concept = concept;
	}

	public List<CCLicense> getLicense() {
		return license;
	}

	public void setLicense(List<CCLicense> license) {
		this.license = license;
	}

}
