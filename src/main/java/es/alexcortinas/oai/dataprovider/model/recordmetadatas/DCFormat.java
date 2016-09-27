package es.alexcortinas.oai.dataprovider.model.recordmetadatas;

import es.alexcortinas.oai.dataprovider.util.Utils;

public enum DCFormat {
    APPLICATION, AUDIO, EXAMPLE, IMAGE, MESSAGE, MODEL, MULTIPART, TEXT, VIDEO;

    @Override
    public String toString() {
        return Utils.enumToCapitalizedString(this, false);
    }

}