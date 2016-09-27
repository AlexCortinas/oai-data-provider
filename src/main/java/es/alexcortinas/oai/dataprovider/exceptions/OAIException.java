package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;
import es.alexcortinas.oai.dataprovider.responses.ErrorResponse;

@SuppressWarnings("serial")
public abstract class OAIException extends Exception {
    private final ErrorCode errorCode;
    private final String errorMessage;

    protected OAIException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = null;
    }

    OAIException(ErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResponse getResponse(String baseURL) {
        return new ErrorResponse(this, baseURL);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
