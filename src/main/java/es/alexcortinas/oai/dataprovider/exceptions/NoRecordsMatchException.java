package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class NoRecordsMatchException extends OAIException {

    public NoRecordsMatchException() {
        super(ErrorCode.NO_RECORDS_MATCH);
    }

}
