package es.alexcortinas.oai.dataprovider.model.recordmetadatas;

import es.alexcortinas.oai.dataprovider.util.StringUtils;

public enum EUType {
    TEXT, IMAGE, SOUND, VIDEO, _3D;

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