package es.alexcortinas.oai.dataprovider.model.recordmetadatas;

public enum EURights {
    PDM, OOC_NC, CC0, BY, BY_SA, BY_ND, BY_NC, BY_NC_SA, BY_NC_ND, FREE_ACCESS, PAID_ACCESS, ORPHAN, UNKNOWN;

    @Override
    public String toString() {
        switch (this) {
        case PDM:
            return "http://creativecommons.org/publicdomain/mark/1.0/";
        case OOC_NC:
            return "http://www.europeana.eu/rights/out-of-copyright-non-commercial/";
        case CC0:
            return "http://creativecommons.org/publicdomain/zero/1.0/";
        case BY:
            return "http://creativecommons.org/licenses/by/4.0/";
        case BY_SA:
            return "http://creativecommons.org/licenses/by-sa/4.0/";
        case BY_ND:
            return "http://creativecommons.org/licenses/by-nd/4.0/";
        case BY_NC:
            return "http://creativecommons.org/licenses/by-nc/4.0/";
        case BY_NC_SA:
            return "http://creativecommons.org/licenses/by-nc-sa/4.0/";
        case BY_NC_ND:
            return "http://creativecommons.org/licenses/by-nc-nd/4.0/";
        case FREE_ACCESS:
            return "http://www.europeana.eu/rights/rr-f/";
        case PAID_ACCESS:
            return "http://www.europeana.eu/rights/rr-p/";
        case ORPHAN:
            return "http://www.europeana.eu/rights/orphan-work-eu/";
        case UNKNOWN:
            return "http://www.europeana.eu/rights/unknown/";
        }
        return null;
    }
}
