package es.alexcortinas.oai.dataprovider.util;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;

public interface IJdomConvertible {
    public Element toJdomElement(Granularity granularity);
}
