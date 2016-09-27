package es.alexcortinas.oai.dataprovider.model;

import java.util.List;

import org.jdom2.Element;

public class ListSets {
    private List<Set> sets;
    private ResumptionToken token;

    public ListSets(List<Set> sets, ResumptionToken token) {
        this.setToken(token);
        this.sets = sets;
    }

    public Element toJdomElement() {
        return null;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public ResumptionToken getToken() {
        return token;
    }

    public void setToken(ResumptionToken token) {
        this.token = token;
    }

}
