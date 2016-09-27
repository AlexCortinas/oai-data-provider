package es.alexcortinas.oai.dataprovider.model.constants;

public enum DeletedRecord {
    NO, TRANSIENT, PERSISTENT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
