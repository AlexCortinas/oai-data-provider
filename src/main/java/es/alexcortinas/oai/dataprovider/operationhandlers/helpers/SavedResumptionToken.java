package es.alexcortinas.oai.dataprovider.operationhandlers.helpers;

import java.util.Calendar;

import es.alexcortinas.oai.dataprovider.operationhandlers.Verb;

public class SavedResumptionToken {
    private String token;
    private Integer total;
    private Integer offset;
    private Calendar expiration;

    private Verb verb;
    private String metadataPrefix;
    private Calendar from;
    private Calendar until;
    private String set;

    public SavedResumptionToken(String token, Verb verb) {
        super();
        this.token = token;
        this.verb = verb;
    }

    public SavedResumptionToken(String token, Verb verb, Integer total, Integer offset, Calendar expiration,
            String metadataPrefix, Calendar from, Calendar until, String set) {
        super();
        this.token = token;
        this.verb = verb;
        this.total = total;
        this.offset = offset;
        this.expiration = expiration;
        this.metadataPrefix = metadataPrefix;
        this.from = from;
        this.setUntil(until);
        this.set = set;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((verb == null) ? 0 : verb.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SavedResumptionToken other = (SavedResumptionToken) obj;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        if (verb != other.verb)
            return false;
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public Calendar getFrom() {
        return from;
    }

    public void setFrom(Calendar from) {
        this.from = from;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getMetadataPrefix() {
        return metadataPrefix;
    }

    public void setMetadataPrefix(String metadataPrefix) {
        this.metadataPrefix = metadataPrefix;
    }

    public Calendar getExpiration() {
        return expiration;
    }

    public void setExpiration(Calendar expiration) {
        this.expiration = expiration;
    }

    public Calendar getUntil() {
        return until;
    }

    public void setUntil(Calendar until) {
        this.until = until;
    }

}
