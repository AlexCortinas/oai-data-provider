package es.alexcortinas.oai.dataprovider.model;

import java.util.Calendar;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.operationhandlers.helpers.SavedResumptionToken;
import es.alexcortinas.oai.dataprovider.util.DateUtils;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class ResumptionToken {
    // Texto
    private String token;

    // Atributos opcionales
    private Calendar expirationDate; // en granularidad segundos
    private Integer completeListSize;
    private Integer cursor;

    public ResumptionToken() {

    }

    public ResumptionToken(String token, Calendar expirationDate, Integer completeListSize, Integer cursor) {
        super();
        this.token = token;
        this.expirationDate = expirationDate;
        this.completeListSize = completeListSize;
        this.cursor = cursor;
    }

    public ResumptionToken(SavedResumptionToken newToken) {
        this(newToken.getToken(), newToken.getExpiration(), newToken.getTotal(), newToken.getOffset());
    }

    public Element toJdomElement(Granularity granularity) {

        Element xml = new Element("resumptionToken", XmlUtils.getOAINamespace());

        if (expirationDate != null) {
            xml.setAttribute("expirationDate", DateUtils.calendarToString(expirationDate, Granularity.SECONDS));
        }

        if (completeListSize != null) {
            xml.setAttribute("completeListSize", completeListSize.toString());
        }

        if (cursor != null) {
            xml.setAttribute("cursor", cursor.toString());
        }

        xml.setText(token);

        return xml;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCompleteListSize() {
        return completeListSize;
    }

    public void setCompleteListSize(Integer completeListSize) {
        this.completeListSize = completeListSize;
    }

    public Integer getCursor() {
        return cursor;
    }

    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
