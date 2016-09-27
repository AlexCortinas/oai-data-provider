package es.alexcortinas.oai.dataprovider.model.constants;

public enum Granularity {
    DAYS, SECONDS;

    public String toString() {
        switch (this) {
        case DAYS:
            return "YYYY-MM-DD";
        case SECONDS:
            return "YYYY-MM-DDThh:mm:ssZ";
        }
        return "YYYY-MM-DD";
    }
}
