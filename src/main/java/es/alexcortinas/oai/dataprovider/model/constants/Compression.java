package es.alexcortinas.oai.dataprovider.model.constants;

public enum Compression {
    GZIP, COMPRESS, DEFLATE, IDENTITY;

    @Override
    public String toString() {
        if (this == IDENTITY) {
            return null;
        }
        return name().toLowerCase();
    }
}
