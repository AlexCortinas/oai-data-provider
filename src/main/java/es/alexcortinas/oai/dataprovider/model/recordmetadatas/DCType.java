package es.alexcortinas.oai.dataprovider.model.recordmetadatas;

import es.alexcortinas.oai.dataprovider.util.Utils;

public enum DCType {
    COLLECTION, DATASET, EVENT, IMAGE, INTERACTIVE_RESOURCE, MOVING_IMAGE, PHYSICAL_OBJECT, SERVICE, SOFTWARE, SOUND, STILL_IMAGE, TEXT;

    @Override
    public String toString() {
        return Utils.enumToCapitalizedString(this, false);
    }
}