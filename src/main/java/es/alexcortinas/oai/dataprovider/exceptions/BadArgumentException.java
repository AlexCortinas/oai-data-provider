package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class BadArgumentException extends OAIException {

    public BadArgumentException() {
        super(ErrorCode.BAD_ARGUMENT);
    }

    public BadArgumentException(String message) {
        super(ErrorCode.BAD_ARGUMENT, message);
    }

}
