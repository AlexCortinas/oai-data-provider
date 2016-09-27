package es.alexcortinas.oai.dataprovider.model.identifydescriptions;

import org.jdom2.Element;
import org.jdom2.Namespace;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class OAIIdentifierDescription implements IIdentifyDescription {
    private final String scheme;
    private final String repositoryIdentifier;
    private final String delimiter;
    private final String sampleIdentifier;

    public OAIIdentifierDescription(String scheme, String repositoryIdentifier, String delimiter,
            String sampleIdentifier) {
        super();
        this.scheme = scheme;
        this.repositoryIdentifier = repositoryIdentifier;
        this.delimiter = delimiter;
        this.sampleIdentifier = sampleIdentifier;
    }

    public Element toJdomElement(Granularity granularity) {
        Namespace oaiIdentifier = Namespace.getNamespace("http://www.openarchives.org/OAI/2.0/oai-identifier");
        Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element xml = new Element("oai-identifier", oaiIdentifier);
        xml.addNamespaceDeclaration(oaiIdentifier);
        xml.addNamespaceDeclaration(xsi);
        xml.setAttribute("schemaLocation", "http://www.openarchives.org/OAI/2.0/oai-identifier "
                + "http://www.openarchives.org/OAI/2.0/oai-identifier.xsd", xsi);

        XmlUtils.addTextElement(xml, "scheme", scheme, oaiIdentifier);
        XmlUtils.addTextElement(xml, "repositoryIdentifier", repositoryIdentifier, oaiIdentifier);
        XmlUtils.addTextElement(xml, "delimiter", delimiter, oaiIdentifier);
        XmlUtils.addTextElement(xml, "sampleIdentifier", sampleIdentifier, oaiIdentifier);

        return xml;
    }

    public String getScheme() {
        return scheme;
    }

    public String getRepositoryIdentifier() {
        return repositoryIdentifier;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getSampleIdentifier() {
        return sampleIdentifier;
    }

}