package es.alexcortinas.oai.dataprovider.operationhandlers;

import es.alexcortinas.oai.dataprovider.IOAIDelegate;
import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.exceptions.IdDoesNotExistException;
import es.alexcortinas.oai.dataprovider.model.Record;
import es.alexcortinas.oai.dataprovider.responses.GetRecordResponse;
import es.alexcortinas.oai.dataprovider.responses.IOAIResponse;

public class GetRecordHandler extends OAIOperationHandler implements IOAIOperationHandler {

    public IOAIResponse handle(IOAIDelegate delegate, OAIRequest request) throws IdDoesNotExistException {
        Record record = delegate.getRecord(request.getIdentifier(), request.getMetadataPrefix());

        if (record == null) {
            throw new IdDoesNotExistException();
        }

        GetRecordResponse response = new GetRecordResponse(request, record, delegate.getBaseURL(),
                delegate.getFinestGranularity());

        return response;
    }

}
