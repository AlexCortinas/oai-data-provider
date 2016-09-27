package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class NoMetadataFormatsException extends OAIException {

    public NoMetadataFormatsException() {
        super(ErrorCode.NO_METADATA_FORMATS);
    }

}
