package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class IdDoesNotExistException extends OAIException {

    public IdDoesNotExistException() {
        super(ErrorCode.ID_DOES_NOT_EXIST);
    }

}
