package es.alexcortinas.oai.dataprovider;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.operationhandlers.Verb;
import es.alexcortinas.oai.dataprovider.util.StringUtils;

public class OAIRequest {
    private Verb verb;
    private String identifier;
    private String metadataPrefix;
    private String from;
    private String until;
    private String set;
    private String resumptionToken;

    public OAIRequest() {
    }

    public OAIRequest(Verb verb, String identifier, String metadataPrefix, String from, String until, String set,
            String resumptionToken) {

        this.verb = verb;

        if (!StringUtils.isBlank(identifier)) {
            this.identifier = identifier;
        }

        if (!StringUtils.isBlank(metadataPrefix)) {
            this.metadataPrefix = metadataPrefix;
        }

        if (!StringUtils.isBlank(from)) {
            this.from = from;
        }

        if (!StringUtils.isBlank(until)) {
            this.until = until;
        }

        if (!StringUtils.isBlank(set)) {
            this.set = set;
        }

        if (!StringUtils.isBlank(resumptionToken)) {
            this.resumptionToken = resumptionToken;
        }
    }

    public static OAIRequest parse(String verb, String identifier, String metadataPrefix, String from, String until,
            String set, String resumptionToken, Granularity granularity) {

        Verb nVerb = Verb.fromString(verb);

        return new OAIRequest(nVerb, identifier, metadataPrefix, from, until, set, resumptionToken);
    }

    public Element toXmlElement(String baseUrl) {
        Element request = new Element("request");

        if (verb != null) {
            request.setAttribute("verb", verb.toString());
        }

        if (identifier != null) {
            request.setAttribute("identifier", identifier);
        }

        if (metadataPrefix != null) {
            request.setAttribute("metadataPrefix", metadataPrefix);
        }

        if (from != null) {
            request.setAttribute("from", from);
        }

        if (until != null) {
            request.setAttribute("until", until);
        }

        if (set != null) {
            request.setAttribute("set", set);
        }

        if (resumptionToken != null) {
            request.setAttribute("resumptionToken", resumptionToken);
        }

        request.setText(baseUrl);

        return request;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMetadataPrefix() {
        return metadataPrefix;
    }

    public void setMetadataPrefix(String metadataPrefix) {
        this.metadataPrefix = metadataPrefix;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getResumptionToken() {
        return resumptionToken;
    }

    public void setResumptionToken(String resumptionToken) {
        this.resumptionToken = resumptionToken;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

}
