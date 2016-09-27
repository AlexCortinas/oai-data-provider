package es.alexcortinas.oai.dataprovider.model.constants;

import es.alexcortinas.oai.dataprovider.util.Utils;

public enum ErrorCode {
    BAD_ARGUMENT, BAD_RESUMPTION_TOKEN, BAD_VERB, CANNOT_DISSEMINATE_FORMAT, ID_DOES_NOT_EXIST, NO_RECORDS_MATCH, NO_METADATA_FORMATS, NO_SET_HIERARCHY;

    @Override
    public String toString() {
        return Utils.enumToCapitalizedString(this, false);
    }
}