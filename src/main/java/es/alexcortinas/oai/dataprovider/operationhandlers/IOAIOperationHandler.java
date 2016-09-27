package es.alexcortinas.oai.dataprovider.operationhandlers;

import es.alexcortinas.oai.dataprovider.IOAIDelegate;
import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.exceptions.OAIException;
import es.alexcortinas.oai.dataprovider.responses.IOAIResponse;

public interface IOAIOperationHandler {
    public IOAIResponse handle(IOAIDelegate delegate, OAIRequest request) throws OAIException;
}
