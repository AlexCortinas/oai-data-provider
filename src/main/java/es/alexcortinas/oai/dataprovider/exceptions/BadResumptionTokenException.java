package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class BadResumptionTokenException extends OAIException {

    public BadResumptionTokenException() {
        super(ErrorCode.BAD_RESUMPTION_TOKEN);
    }
}
