package es.alexcortinas.oai.dataprovider.validators;

import java.text.ParseException;
import java.util.Calendar;

import es.alexcortinas.oai.dataprovider.exceptions.BadArgumentException;
import es.alexcortinas.oai.dataprovider.exceptions.BadVerbException;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.operationhandlers.Verb;
import es.alexcortinas.oai.dataprovider.util.DateUtils;
import es.alexcortinas.oai.dataprovider.util.StringUtils;

public class RequestParametersValidator {

	public void checkAllowedParameterName(String name)
			throws BadArgumentException {

		if (name.equals("verb") || name.equals("identifier")
				|| name.equals("metadataPrefix") || name.equals("from")
				|| name.equals("until") || name.equals("set")
				|| name.equals("resumptionToken")) {
			return;
		}

		throw new BadArgumentException();
	}

	public void checkParametersAreUnique(String[] verb, String[] identifier,
			String[] metadataPrefix, String[] from, String[] until,
			String[] set, String[] resumptionToken) throws BadVerbException,
			BadArgumentException {

		if (verb != null && verb.length > 1) {
			throw new BadVerbException();
		}

		if (identifier != null && identifier.length > 1
				|| metadataPrefix != null && metadataPrefix.length > 1
				|| from != null && from.length > 1 || until != null
				&& until.length > 1 || set != null && set.length > 1
				|| resumptionToken != null && resumptionToken.length > 1) {
			throw new BadArgumentException();
		}
	}

	public void validate(String verb, String identifier, String metadataPrefix,
			String from, String until, String set, String resumptionToken,
			Granularity granularity) throws BadVerbException,
			BadArgumentException {

		Verb v = Verb.fromString(verb);
		if (v == null) {
			throw new BadVerbException();
		}

		switch (Verb.fromString(verb)) {
		case IDENTIFY:
			validateIdentify(verb, identifier, metadataPrefix, from, until,
					set, resumptionToken, granularity);
			break;
		case GET_RECORD:
			validateGetRecord(verb, identifier, metadataPrefix, from, until,
					set, resumptionToken, granularity);
			break;
		case LIST_METADATA_FORMATS:
			validateListMetadataFormats(verb, identifier, metadataPrefix, from,
					until, set, resumptionToken, granularity);
			break;
		case LIST_IDENTIFIERS:
			validateListIdentifiers(verb, identifier, metadataPrefix, from,
					until, set, resumptionToken, granularity);
			break;
		case LIST_RECORDS:
			validateListRecords(verb, identifier, metadataPrefix, from, until,
					set, resumptionToken, granularity);
		default:
			break;
		}

		// Check granularity!
		Calendar nFrom = null;
		Calendar nUntil = null;

		if (!StringUtils.isBlank(from)) {
			try {
				nFrom = DateUtils.stringToCalendar(from, granularity);
			} catch (ParseException e) {
				throw new BadArgumentException(
						"Not valid datetime, format: YYYY-MM-DD or YYYY-MM-DDThh:mm:ssZ");
			}
		}

		if (!StringUtils.isBlank(until)) {
			try {
				nUntil = DateUtils.stringToCalendar(until, granularity);
			} catch (ParseException e) {
				throw new BadArgumentException(
						"Not valid datetime, format: YYYY-MM-DD or YYYY-MM-DDThh:mm:ssZ");
			}
		}

		if (nFrom != null && nUntil != null && nFrom.after(nUntil)) {
			throw new BadArgumentException(
					"From date cannot be after until date");
		}
	}

	public void validateIdentify(String verb, String identifier,
			String metadataPrefix, String from, String until, String set,
			String resumptionToken, Granularity granularity)
			throws BadArgumentException {

		if (!StringUtils.isBlank(identifier)
				|| !StringUtils.isBlank(metadataPrefix)
				|| !StringUtils.isBlank(from) || !StringUtils.isBlank(until)
				|| !StringUtils.isBlank(set)
				|| !StringUtils.isBlank(resumptionToken)) {
			throw new BadArgumentException();
		}

	}

	public void validateGetRecord(String verb, String identifier,
			String metadataPrefix, String from, String until, String set,
			String resumptionToken, Granularity granularity)
			throws BadArgumentException {

		if (StringUtils.isBlank(identifier)
				|| StringUtils.isBlank(metadataPrefix)) {
			throw new BadArgumentException();
		}

		if (!StringUtils.isBlank(from) || !StringUtils.isBlank(until)
				|| !StringUtils.isBlank(set)
				|| !StringUtils.isBlank(resumptionToken)) {
			throw new BadArgumentException();
		}

	}

	public void validateListMetadataFormats(String verb, String identifier,
			String metadataPrefix, String from, String until, String set,
			String resumptionToken, Granularity granularity)
			throws BadArgumentException {

		if (!StringUtils.isBlank(metadataPrefix) || !StringUtils.isBlank(from)
				|| !StringUtils.isBlank(until) || !StringUtils.isBlank(set)
				|| !StringUtils.isBlank(resumptionToken)) {
			throw new BadArgumentException();
		}

	}

	public void validateListIdentifiers(String verb, String identifier,
			String metadataPrefix, String from, String until, String set,
			String resumptionToken, Granularity granularity)
			throws BadArgumentException {

		if (!StringUtils.isBlank(identifier)) {
			throw new BadArgumentException();
		}

		if (StringUtils.isBlank(resumptionToken)) {
			if (StringUtils.isBlank(metadataPrefix)) {
				throw new BadArgumentException();
			}
		} else {
			if (!StringUtils.isBlank(metadataPrefix)
					|| !StringUtils.isBlank(from)
					|| !StringUtils.isBlank(until) || !StringUtils.isBlank(set)) {
				throw new BadArgumentException();
			}
		}
	}

	public void validateListRecords(String verb, String identifier,
			String metadataPrefix, String from, String until, String set,
			String resumptionToken, Granularity granularity)
			throws BadArgumentException {

		if (!StringUtils.isBlank(identifier)) {
			throw new BadArgumentException();
		}

		if (StringUtils.isBlank(resumptionToken)) {
			if (StringUtils.isBlank(metadataPrefix)) {
				throw new BadArgumentException();
			}
		} else {
			if (!StringUtils.isBlank(metadataPrefix)
					|| !StringUtils.isBlank(from)
					|| !StringUtils.isBlank(until) || !StringUtils.isBlank(set)) {
				throw new BadArgumentException();
			}
		}
	}

	public void validateListSets(String verb, String identifier,
			String metadataPrefix, String from, String until, String set,
			String resumptionToken, Granularity granularity)
			throws BadArgumentException {

		if (!StringUtils.isBlank(identifier)
				|| !StringUtils.isBlank(metadataPrefix)
				|| !StringUtils.isBlank(from) || !StringUtils.isBlank(until)
				|| !StringUtils.isBlank(set)) {
			throw new BadArgumentException();
		}
	}
}
