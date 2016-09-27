package es.alexcortinas.oai.dataprovider.model.constants;

import es.alexcortinas.oai.dataprovider.util.StringUtils;

public enum LanguageCode {
    ARA, ARG, AST, AYM, CAT, DUT, ENG, EPO, EUS, FRE, GER, GLG, GRE, HEB, ITA, NAH, LAT, POR, QUE, SPA, TUP, TUR;

    public static LanguageCode fromString(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        for (LanguageCode l : LanguageCode.values()) {
            if (l.name().toLowerCase().equals(str.toLowerCase())) {
                return l;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
