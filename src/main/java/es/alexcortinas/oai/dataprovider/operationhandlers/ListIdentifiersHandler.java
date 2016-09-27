package es.alexcortinas.oai.dataprovider.operationhandlers;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import es.alexcortinas.oai.dataprovider.IOAIDelegate;
import es.alexcortinas.oai.dataprovider.OAIRequest;
import es.alexcortinas.oai.dataprovider.exceptions.BadResumptionTokenException;
import es.alexcortinas.oai.dataprovider.exceptions.NoRecordsMatchException;
import es.alexcortinas.oai.dataprovider.model.ListIdentifiers;
import es.alexcortinas.oai.dataprovider.model.RecordHeader;
import es.alexcortinas.oai.dataprovider.model.ResumptionToken;
import es.alexcortinas.oai.dataprovider.operationhandlers.helpers.SavedResumptionToken;
import es.alexcortinas.oai.dataprovider.responses.IOAIResponse;
import es.alexcortinas.oai.dataprovider.responses.ListIdentifiersResponse;
import es.alexcortinas.oai.dataprovider.util.DateUtils;

public class ListIdentifiersHandler extends OAIOperationHandler implements
		IOAIOperationHandler {
	private Integer maxElementsPerPage;

	public IOAIResponse handle(IOAIDelegate delegate, OAIRequest request)
			throws NoRecordsMatchException, BadResumptionTokenException {
		maxElementsPerPage = delegate.getMaxElementsByPageReturned();

		if (request.getResumptionToken() == null) {
			Calendar from = null;
			Calendar until = null;
			try {
				from = DateUtils.stringToCalendar(request.getFrom(),
						delegate.getFinestGranularity());
				until = DateUtils.stringToCalendar(request.getUntil(),
						delegate.getFinestGranularity());
			} catch (ParseException e) {
			}

			return new ListIdentifiersResponse(request, handleRegularRequest(
					delegate, request.getMetadataPrefix(), from, until,
					request.getSet()), delegate.getBaseURL(),
					delegate.getFinestGranularity());
		}

		return new ListIdentifiersResponse(request,
				handleResumptionTokenRequest(delegate,
						request.getResumptionToken()), delegate.getBaseURL(),
				delegate.getFinestGranularity());
	}

	private ListIdentifiers handleRegularRequest(IOAIDelegate delegate,
			String metadataPrefix, Calendar from, Calendar until, String set)
			throws NoRecordsMatchException {

		List<RecordHeader> list;

		if (maxElementsPerPage == 0) {
			list = delegate.listIdentifiers(metadataPrefix, from, until, set,
					0, 0);
			return new ListIdentifiers(list, null);
		}

		list = delegate.listIdentifiers(metadataPrefix, from, until, set, 0,
				maxElementsPerPage + 1);
		validateListNotEmpty(list);

		ResumptionToken resumptionToken = null;
		if (tokenIsNeeded(list, maxElementsPerPage)) {
			Integer total = delegate.countRecords(metadataPrefix, from, until,
					set);

			SavedResumptionToken newToken = getTokenManager()
					.generateResumptionToken(Verb.LIST_IDENTIFIERS, total, 0,
							metadataPrefix, from, until, set);

			resumptionToken = new ResumptionToken(newToken);
		}

		return new ListIdentifiers(list, resumptionToken);
	}

	private ListIdentifiers handleResumptionTokenRequest(IOAIDelegate delegate,
			String tokenCode) throws BadResumptionTokenException {

		SavedResumptionToken token = null;
		if ((token = getTokenManager().findResumptionToken(
				Verb.LIST_IDENTIFIERS, tokenCode)) == null) {
			throw new BadResumptionTokenException();
		}

		Integer total = delegate.countRecords(token.getMetadataPrefix(),
				token.getFrom(), token.getUntil(), token.getSet());

		if (!total.equals(token.getTotal())) {
			getTokenManager().deleteResumptionToken(Verb.LIST_IDENTIFIERS,
					tokenCode);
			throw new BadResumptionTokenException();
		}

		List<RecordHeader> list = delegate.listIdentifiers(
				token.getMetadataPrefix(), token.getFrom(), token.getUntil(),
				token.getSet(), token.getOffset() + maxElementsPerPage,
				maxElementsPerPage + 1);

		ResumptionToken resumptionToken = null;
		if (tokenIsNeeded(list, maxElementsPerPage)) {
			token = getTokenManager().updateResumptionToken(token,
					list.size() + token.getOffset());

			resumptionToken = new ResumptionToken(token);
		} else {
			resumptionToken = new ResumptionToken();
		}

		return new ListIdentifiers(list, resumptionToken);
	}
}
