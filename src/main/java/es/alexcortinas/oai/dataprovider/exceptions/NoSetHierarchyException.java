package es.alexcortinas.oai.dataprovider.exceptions;

import es.alexcortinas.oai.dataprovider.model.constants.ErrorCode;

@SuppressWarnings("serial")
public class NoSetHierarchyException extends OAIException {

    public NoSetHierarchyException() {
        super(ErrorCode.NO_SET_HIERARCHY);
    }

}
