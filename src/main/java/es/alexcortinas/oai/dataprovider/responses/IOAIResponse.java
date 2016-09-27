package es.alexcortinas.oai.dataprovider.responses;

public interface IOAIResponse {
    public String getXmlString();

    public org.w3c.dom.Document getXmlDocument();

    public org.jdom2.Document getJdomDocument();
}
