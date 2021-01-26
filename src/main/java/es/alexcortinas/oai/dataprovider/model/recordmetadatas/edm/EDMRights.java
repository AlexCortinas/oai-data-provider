package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

public enum EDMRights {
	PDM, NoC_NC, NoC_OKLR, CC0, BY, BY_SA, BY_ND, BY_NC, BY_NC_SA, BY_NC_ND, InC, InC_EDU, InC_EU_OW, CNE;

	@Override
	public String toString() {
		switch (this) {
		case PDM:
			return "http://creativecommons.org/publicdomain/mark/1.0/";
		case NoC_NC:
			return "http://rightsstatements.org/vocab/NoC-NC/1.0/";
		case NoC_OKLR:
			return "http://rightsstatements.org/vocab/NoC-OKLR/1.0/";
		case CC0:
			return "http://creativecommons.org/publicdomain/zero/1.0/";
		case BY:
			return "http://creativecommons.org/licenses/by/4.0/";
		case BY_SA:
			return "http://creativecommons.org/licenses/by-sa/4.0/";
		case BY_ND:
			return "http://creativecommons.org/licenses/by-nd/4.0/";
		case BY_NC:
			return "http://creativecommons.org/licenses/by-nc/4.0/";
		case BY_NC_SA:
			return "http://creativecommons.org/licenses/by-nc-sa/4.0/";
		case BY_NC_ND:
			return "http://creativecommons.org/licenses/by-nc-nd/4.0/";
		case InC:
			return "http://rightsstatements.org/vocab/InC/1.0/";
		case InC_EDU:
			return "http://rightsstatements.org/vocab/InC-EDU/1.0/";
		case InC_EU_OW:
			return "http://rightsstatements.org/vocab/InC-OW-EU/1.0/";
		case CNE:
			return "http://rightsstatements.org/vocab/CNE/1.0/";
		}
		return null;
	}
}
