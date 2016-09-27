package es.alexcortinas.oai.dataprovider.operationhandlers;

import es.alexcortinas.oai.dataprovider.util.StringUtils;

public enum Verb {
    GET_RECORD, IDENTIFY, LIST_IDENTIFIERS, LIST_METADATA_FORMATS, LIST_RECORDS, LIST_SETS;

    public static Verb fromString(String verbAsString) {

        if (StringUtils.isBlank(verbAsString)) {
            return null;
        }

        for (Verb v : Verb.values()) {
            if (verbAsString.equals(v.toString())) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return StringUtils.capitalizeFully(name(), new char[] { '_' }).replaceAll("_", "");
    }
}
