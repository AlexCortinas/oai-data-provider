package es.alexcortinas.oai.dataprovider.operationhandlers;

import es.alexcortinas.oai.dataprovider.IOAIDelegate;
import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.model.Identify;
import es.alexcortinas.oai.dataprovider.responses.IOAIResponse;
import es.alexcortinas.oai.dataprovider.responses.IdentifyResponse;

public class IdentifyHandler extends OAIOperationHandler implements IOAIOperationHandler {

    public IOAIResponse handle(IOAIDelegate delegate, OAIRequest request) {
        Identify identify = delegate.identify();

        return new IdentifyResponse(request, identify);
    }
}
