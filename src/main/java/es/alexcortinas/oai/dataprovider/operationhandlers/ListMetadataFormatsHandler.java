package es.alexcortinas.oai.dataprovider.operationhandlers;

import java.util.List;

import es.alexcortinas.oai.dataprovider.IOAIDelegate;
import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.exceptions.IdDoesNotExistException;
import es.alexcortinas.oai.dataprovider.exceptions.NoMetadataFormatsException;
import es.alexcortinas.oai.dataprovider.model.ListMetadataFormats;
import es.alexcortinas.oai.dataprovider.model.MetadataFormat;
import es.alexcortinas.oai.dataprovider.responses.IOAIResponse;
import es.alexcortinas.oai.dataprovider.responses.ListMetadataFormatsResponse;

public class ListMetadataFormatsHandler extends OAIOperationHandler implements IOAIOperationHandler {

    public IOAIResponse handle(IOAIDelegate delegate, OAIRequest request)
            throws IdDoesNotExistException, NoMetadataFormatsException {
        if (request.getIdentifier() == null) {
            return new ListMetadataFormatsResponse(request, new ListMetadataFormats(delegate.listMetadataFormats(null)),
                    delegate.getBaseURL());
        }

        List<MetadataFormat> list = delegate.listMetadataFormats(request.getIdentifier());

        if (list == null) {
            throw new IdDoesNotExistException();
        }

        if (list.size() == 0) {
            throw new NoMetadataFormatsException();
        }

        return new ListMetadataFormatsResponse(request, new ListMetadataFormats(list), delegate.getBaseURL());
    }
}
