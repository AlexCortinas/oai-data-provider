package es.alexcortinas.oai.dataprovider;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import es.alexcortinas.oai.dataprovider.exceptions.BadArgumentException;
import es.alexcortinas.oai.dataprovider.exceptions.BadResumptionTokenException;
import es.alexcortinas.oai.dataprovider.exceptions.BadVerbException;
import es.alexcortinas.oai.dataprovider.exceptions.CannotDisseminateFormatException;
import es.alexcortinas.oai.dataprovider.exceptions.IdDoesNotExistException;
import es.alexcortinas.oai.dataprovider.exceptions.NoMetadataFormatsException;
import es.alexcortinas.oai.dataprovider.exceptions.NoRecordsMatchException;
import es.alexcortinas.oai.dataprovider.exceptions.NoSetHierarchyException;
import es.alexcortinas.oai.dataprovider.exceptions.OAIRuntimeException;
import es.alexcortinas.oai.dataprovider.operationhandlers.GetRecordHandler;
import es.alexcortinas.oai.dataprovider.operationhandlers.IdentifyHandler;
import es.alexcortinas.oai.dataprovider.operationhandlers.ListIdentifiersHandler;
import es.alexcortinas.oai.dataprovider.operationhandlers.ListMetadataFormatsHandler;
import es.alexcortinas.oai.dataprovider.operationhandlers.ListRecordsHandler;
import es.alexcortinas.oai.dataprovider.operationhandlers.ListSetsHandler;
import es.alexcortinas.oai.dataprovider.responses.IOAIResponse;
import es.alexcortinas.oai.dataprovider.util.Utils;
import es.alexcortinas.oai.dataprovider.validators.RequestParametersValidator;

public class OAIHandler {

    /**
     * This method handles the HttpServletRequest directly, so it controls all
     * the possible errors of the OAI petition.
     * 
     * @param request
     * @return
     * @throws BadArgumentException
     * @throws BadVerbException
     * @throws IdDoesNotExistException
     * @throws NoSetHierarchyException
     * @throws BadResumptionTokenException
     * @throws CannotDisseminateFormatException
     * @throws NoRecordsMatchException
     * @throws NoMetadataFormatsException
     */
    public static IOAIResponse handleOAIPetition(IOAIDelegate delegate, HttpServletRequest request)
            throws BadArgumentException, BadVerbException, IdDoesNotExistException, NoRecordsMatchException,
            CannotDisseminateFormatException, BadResumptionTokenException, NoSetHierarchyException,
            NoMetadataFormatsException {
        @SuppressWarnings("unchecked")
        Enumeration<String> parameterNames = request.getParameterNames();

        RequestParametersValidator validator = new RequestParametersValidator();
        while (parameterNames.hasMoreElements()) {
            validator.checkAllowedParameterName(parameterNames.nextElement());
        }

        // String baseURL = new String(request.getRequestURL());
        String[] verb = request.getParameterValues("verb");
        String[] identifier = request.getParameterValues("identifier");
        String[] metadataPrefix = request.getParameterValues("metadataPrefix");
        String[] from = request.getParameterValues("from");
        String[] until = request.getParameterValues("until");
        String[] set = request.getParameterValues("set");
        String[] resumptionToken = request.getParameterValues("resumptionToken");

        return OAIHandler.handleOAIPetition(delegate, verb, identifier, metadataPrefix, from, until, set,
                resumptionToken);
    }

    /**
     * This method assumes that the errors due to unknown parameteres are
     * handled before using it.
     * 
     * @param verb
     * @param identifier
     * @param metadataPrefix
     * @param from
     * @param until
     * @param set
     * @param resumptionToken
     * @return
     * @throws BadArgumentException
     * @throws BadVerbException
     * @throws IdDoesNotExistException
     * @throws NoSetHierarchyException
     * @throws BadResumptionTokenException
     * @throws CannotDisseminateFormatException
     * @throws NoRecordsMatchException
     * @throws NoMetadataFormatsException
     */
    public static IOAIResponse handleOAIPetition(IOAIDelegate delegate, String[] verb, String[] identifier,
            String[] metadataPrefix, String[] from, String[] until, String[] set, String[] resumptionToken)
            throws BadVerbException, BadArgumentException, IdDoesNotExistException, NoRecordsMatchException,
            CannotDisseminateFormatException, BadResumptionTokenException, NoSetHierarchyException,
            NoMetadataFormatsException {

        RequestParametersValidator validator = new RequestParametersValidator();
        validator.checkParametersAreUnique(verb, identifier, metadataPrefix, from, until, set, resumptionToken);

        return OAIHandler.handleOAIPetition(delegate, Utils.getArrayItem(verb, 0), Utils.getArrayItem(identifier, 0),
                Utils.getArrayItem(metadataPrefix, 0), Utils.getArrayItem(from, 0), Utils.getArrayItem(until, 0),
                Utils.getArrayItem(set, 0), Utils.getArrayItem(resumptionToken, 0));
    }

    /**
     * This method assumes that the errors due to multiple parameteres in the
     * request or unknown parameteres are handled before using it.
     * 
     * @param verb
     * @param identifier
     * @param metadataPrefix
     * @param from
     * @param until
     * @param set
     * @param resumptionToken
     * @return
     * @throws BadVerbException
     * @throws IdDoesNotExistException
     * @throws NoSetHierarchyException
     * @throws BadResumptionTokenException
     * @throws CannotDisseminateFormatException
     * @throws NoRecordsMatchException
     * @throws BadArgumentException
     * @throws NoMetadataFormatsException
     */
    public static IOAIResponse handleOAIPetition(IOAIDelegate delegate, String verb, String identifier,
            String metadataPrefix, String from, String until, String set, String resumptionToken)
            throws BadVerbException, IdDoesNotExistException, NoRecordsMatchException, CannotDisseminateFormatException,
            BadResumptionTokenException, NoSetHierarchyException, BadArgumentException, NoMetadataFormatsException {

        RequestParametersValidator validator = new RequestParametersValidator();
        validator.validate(verb, identifier, metadataPrefix, from, until, set, resumptionToken,
                delegate.getFinestGranularity());

        OAIRequest request = OAIRequest.parse(verb, identifier, metadataPrefix, from, until, set, resumptionToken,
                delegate.getFinestGranularity());

        return doHandle(delegate, request);
    }

    private static IOAIResponse doHandle(IOAIDelegate delegate, OAIRequest request)
            throws IdDoesNotExistException, NoRecordsMatchException, CannotDisseminateFormatException,
            BadResumptionTokenException, NoSetHierarchyException, NoMetadataFormatsException {

        if (request.getMetadataPrefix() != null) {

            Boolean check = delegate.existsMetadataFormat(request.getMetadataPrefix(), null);
            if (!check) {
                throw new CannotDisseminateFormatException();
            }
            // If the request comes with metadataPrefix, then it should be
            // available
            // in the repository (for any record in case identifier is null, or
            // for
            // the indicated record)
            // Boolean check = delegate.existsMetadataFormat(
            // request.getMetadataPrefix(), request.getIdentifier());
            // if (check == null) {
            // throw new IdDoesNotExistException();
            // } else if (!check) {
            // throw new CannotDisseminateFormatException();
            // }
        }

        // If the request comes with set, the setHierarchy should be available
        // in the repository
        if (request.getSet() != null) {
            if (!delegate.getSupportsSets()) {
                throw new NoSetHierarchyException();
            }
        }

        switch (request.getVerb()) {
        case IDENTIFY:
            return new IdentifyHandler().handle(delegate, request);
        case GET_RECORD:
            return new GetRecordHandler().handle(delegate, request);
        case LIST_IDENTIFIERS:
            return new ListIdentifiersHandler().handle(delegate, request);
        case LIST_METADATA_FORMATS:
            return new ListMetadataFormatsHandler().handle(delegate, request);
        case LIST_RECORDS:
            return new ListRecordsHandler().handle(delegate, request);
        case LIST_SETS:
            return new ListSetsHandler().handle(delegate, request);
        }

        throw new OAIRuntimeException("OAIHandler.doHandle: wrong verb.");
    }
}
