package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class BadVerbException extends OAIException {

    public BadVerbException() {
        super(ErrorCode.BAD_VERB);
    }

}
