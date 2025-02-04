package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import es.alexcortinas.oai.dataprovider.model.recordmetadatas.EUType;
import es.alexcortinas.oai.dataprovider.util.StringUtils;

public enum EDMType {
	TEXT, VIDEO, SOUND, IMAGE, _3D;

	@Override
	public String toString() {
		return this.name().replace("_", "");
	}

	public static EUType fromString(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}

		for (EUType t : EUType.values()) {
			if (t.name().toLowerCase().equals(str.toLowerCase())) {
				return t;
			}
		}

		return null;
	}

}
