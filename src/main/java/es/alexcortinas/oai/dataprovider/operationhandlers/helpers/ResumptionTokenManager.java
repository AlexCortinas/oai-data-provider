package es.alexcortinas.oai.dataprovider.operationhandlers.helpers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.alexcortinas.oai.dataprovider.DomainConstants;
import es.alexcortinas.oai.dataprovider.operationhandlers.Verb;
import es.alexcortinas.oai.dataprovider.util.RandomString;

public class ResumptionTokenManager {
    // Singleton stuff
    private static ResumptionTokenManager instance;

    public static ResumptionTokenManager getInstance() {
        if (instance == null) {
            instance = new ResumptionTokenManager();
        }
        return instance;
    }

    // Constructor
    private ResumptionTokenManager() {

        tokensByVerb = new HashMap<Verb, Map<String, SavedResumptionToken>>();
        for (Verb v : Verb.values()) {
            tokensByVerb.put(v, new HashMap<String, SavedResumptionToken>());
        }
    }

    // Class
    private final Map<Verb, Map<String, SavedResumptionToken>> tokensByVerb;
    private final RandomString randomizer = new RandomString(DomainConstants.resumptionTokenLength);

    // Methods
    public SavedResumptionToken generateResumptionToken(Verb verb, Integer total, Integer offset, String metadataPrefix,
            Calendar from, Calendar until, String set) {

        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.SECOND, DomainConstants.tokenDurationSeconds);

        SavedResumptionToken token = new SavedResumptionToken(randomizer.nextString(), verb, total, offset, expiration,
                metadataPrefix, from, until, set);

        this.tokensByVerb.get(verb).put(token.getToken(), token);

        return token;
    }

    public SavedResumptionToken findResumptionToken(Verb verb, String token) {

        SavedResumptionToken aux = this.tokensByVerb.get(verb).get(token);

        if (aux == null || (aux.getExpiration() != null && aux.getExpiration().before(Calendar.getInstance()))) {
            deleteResumptionToken(verb, token);
            return null;
        }

        return aux;
    }

    public SavedResumptionToken updateResumptionToken(SavedResumptionToken token, Integer offset) {

        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.SECOND, DomainConstants.tokenDurationSeconds);

        deleteResumptionToken(token.getVerb(), token.getToken());

        token.setToken(randomizer.nextString());
        token.setExpiration(expiration);
        token.setOffset(offset);

        this.tokensByVerb.get(token.getVerb()).put(token.getToken(), token);

        return token;
    }

    public void deleteResumptionToken(Verb verb, String token) {

        this.tokensByVerb.get(verb).remove(token);
    }

}
