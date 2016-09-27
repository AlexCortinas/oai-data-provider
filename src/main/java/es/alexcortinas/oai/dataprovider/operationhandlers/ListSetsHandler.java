package es.alexcortinas.oai.dataprovider.operationhandlers;

import java.util.List;

import es.alexcortinas.oai.dataprovider.IOAIDelegate;
import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.exceptions.BadResumptionTokenException;
import es.alexcortinas.oai.dataprovider.exceptions.NoSetHierarchyException;
import es.alexcortinas.oai.dataprovider.model.ListSets;
import es.alexcortinas.oai.dataprovider.model.ResumptionToken;
import es.alexcortinas.oai.dataprovider.model.Set;
import es.alexcortinas.oai.dataprovider.operationhandlers.helpers.SavedResumptionToken;
import es.alexcortinas.oai.dataprovider.responses.IOAIResponse;
import es.alexcortinas.oai.dataprovider.responses.ListSetsResponse;

public class ListSetsHandler extends OAIOperationHandler implements IOAIOperationHandler {
    private Integer maxElementsPerPage;

    public IOAIResponse handle(IOAIDelegate delegate, OAIRequest request)
            throws NoSetHierarchyException, BadResumptionTokenException {
        maxElementsPerPage = delegate.getMaxElementsByPageReturned();

        if (request.getResumptionToken() == null) {
            return new ListSetsResponse(request, handleRegularRequest(delegate), delegate.getBaseURL());
        }

        return new ListSetsResponse(request, handleResumptionTokenRequest(delegate, request.getResumptionToken()),
                delegate.getBaseURL());
    }

    private ListSets handleRegularRequest(IOAIDelegate delegate) throws NoSetHierarchyException {

        if (!delegate.getSupportsSets()) {
            throw new NoSetHierarchyException();
        }

        List<Set> list;

        if (maxElementsPerPage == 0) {
            list = delegate.listSets(0, 0);
            return new ListSets(list, null);
        }

        list = delegate.listSets(0, maxElementsPerPage + 1);

        ResumptionToken resumptionToken = null;
        if (tokenIsNeeded(list, maxElementsPerPage)) {
            Integer total = delegate.countSets();

            SavedResumptionToken newToken = getTokenManager().generateResumptionToken(Verb.LIST_SETS, total, 0, null,
                    null, null, null);

            resumptionToken = new ResumptionToken(newToken);
        }

        return new ListSets(list, resumptionToken);
    }

    private ListSets handleResumptionTokenRequest(IOAIDelegate delegate, String tokenCode)
            throws BadResumptionTokenException {

        SavedResumptionToken token = null;
        if ((token = getTokenManager().findResumptionToken(Verb.LIST_SETS, tokenCode)) == null) {
            throw new BadResumptionTokenException();
        }

        Integer total = delegate.countSets();

        if (!total.equals(token.getTotal())) {
            getTokenManager().deleteResumptionToken(Verb.LIST_SETS, tokenCode);
            throw new BadResumptionTokenException();
        }

        List<Set> list = delegate.listSets(token.getOffset() + maxElementsPerPage, maxElementsPerPage + 1);

        ResumptionToken resumptionToken = null;
        if (tokenIsNeeded(list, maxElementsPerPage)) {
            token = getTokenManager().updateResumptionToken(token, list.size() + token.getOffset());

            resumptionToken = new ResumptionToken(token);
        } else {
            resumptionToken = new ResumptionToken();
        }

        return new ListSets(list, resumptionToken);
    }
}
