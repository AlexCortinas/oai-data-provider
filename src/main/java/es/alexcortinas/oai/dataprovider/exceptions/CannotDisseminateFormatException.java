package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class CannotDisseminateFormatException extends OAIException {

    public CannotDisseminateFormatException() {
        super(ErrorCode.CANNOT_DISSEMINATE_FORMAT);
    }

}
