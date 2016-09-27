package es.alexcortinas.oai.dataprovider.operationhandlers;

import java.util.List;

import es.alexcortinas.oai.dataprovider.exceptions.NoRecordsMatchException;
import es.alexcortinas.oai.dataprovider.operationhandlers.helpers.ResumptionTokenManager;

public abstract class OAIOperationHandler implements IOAIOperationHandler {
    private final ResumptionTokenManager tokenManager = ResumptionTokenManager.getInstance();

    protected void validateListNotEmpty(List<?> list) throws NoRecordsMatchException {
        if (list.size() == 0) {
            throw new NoRecordsMatchException();
        }
    }

    protected boolean tokenIsNeeded(List<?> list, Integer maxListLength) {

        if (maxListLength == 0) {
            return false;
        }

        if (list.size() <= maxListLength) {
            return false;
        }

        for (int i = list.size(); i > maxListLength; i--) {
            list.remove(i - 1);
        }

        return true;
    }

    protected ResumptionTokenManager getTokenManager() {
        return tokenManager;
    }

}
