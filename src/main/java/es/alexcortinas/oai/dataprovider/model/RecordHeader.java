package es.alexcortinas.oai.dataprovider.model;

import java.util.Calendar;
import java.util.List;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.util.DateUtils;
import es.alexcortinas.oai.dataprovider.util.IJdomConvertible;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class RecordHeader implements IJdomConvertible {
    private String identifier;
    private Calendar datestamp;
    private List<String> setSpecs;
    private Boolean deleted;

    public RecordHeader(String identifier, Calendar datestamp, List<String> setSpecs, Boolean deleted) {
        super();
        this.identifier = identifier;
        this.datestamp = datestamp;
        this.setSpecs = setSpecs;
        this.deleted = deleted;
    }

    public Element toJdomElement(Granularity granularity) {
        Element xml = new Element("header", XmlUtils.getOAINamespace());

        XmlUtils.addTextElement(xml, "identifier", identifier);
        XmlUtils.addTextElement(xml, "datestamp", DateUtils.calendarToString(datestamp, granularity));

        if (setSpecs != null) {
            for (String s : setSpecs) {
                XmlUtils.addTextElement(xml, "setSpec", s);
            }
        }

        if (deleted != null && deleted) {
            XmlUtils.addTextElement(xml, "status", "deleted");
        }

        return xml;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Calendar getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Calendar datestamp) {
        this.datestamp = datestamp;
    }

    public List<String> getSetSpecs() {
        return setSpecs;
    }

    public void setSetSpecs(List<String> setSpecs) {
        this.setSpecs = setSpecs;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
