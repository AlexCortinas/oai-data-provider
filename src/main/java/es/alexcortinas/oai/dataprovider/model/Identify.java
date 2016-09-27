package es.alexcortinas.oai.dataprovider.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jdom2.Element;

import es.alexcortinas.oai.dataprovider.model.constants.Compression;
import es.alexcortinas.oai.dataprovider.model.constants.DeletedRecord;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;
import es.alexcortinas.oai.dataprovider.model.identifydescriptions.IIdentifyDescription;
import es.alexcortinas.oai.dataprovider.util.DateUtils;
import es.alexcortinas.oai.dataprovider.util.XmlUtils;

public class Identify {
    // Required elements
    private String repositoryName;
    private String baseURL;
    private final String protocolVersion = "2.0";
    private Calendar earliestDatestamp;
    private DeletedRecord deletedRecord;
    private Granularity granularity;

    // One adminEmail is required
    private List<String> adminEmails;

    // Optional
    private List<Compression> compressions;
    private List<IIdentifyDescription> descriptions;

    private Identify() {
        this.deletedRecord = DeletedRecord.NO;
        this.granularity = Granularity.DAYS;
        this.earliestDatestamp = Calendar.getInstance();
        this.compressions = new ArrayList<Compression>();
        this.compressions.add(Compression.IDENTITY);
    }

    public Identify(String repositoryName, String baseURL, Calendar earliestDatestamp, DeletedRecord deletedRecord,
            Granularity granularity, List<String> adminEmails, List<Compression> compressions,
            List<IIdentifyDescription> descriptions) {
        this();
        this.repositoryName = repositoryName;
        this.baseURL = baseURL;
        if (adminEmails != null) {
            this.adminEmails = adminEmails;
        }
        if (earliestDatestamp != null) {
            this.earliestDatestamp = earliestDatestamp;
        }
        if (deletedRecord != null) {
            this.deletedRecord = deletedRecord;
        }
        if (granularity != null) {
            this.granularity = granularity;
        }
        if (compressions != null) {
            this.compressions = compressions;
        }
        if (descriptions != null) {
            this.descriptions = descriptions;
        }
    }

    public Element toJdomElement() {
        Element xml = new Element("Identify", XmlUtils.getOAINamespace());

        XmlUtils.addTextElement(xml, "repositoryName", repositoryName);
        XmlUtils.addTextElement(xml, "baseURL", baseURL);
        XmlUtils.addTextElement(xml, "protocolVersion", protocolVersion);
        XmlUtils.addTextElement(xml, "earliestDatestamp", DateUtils.calendarToString(earliestDatestamp, granularity));
        XmlUtils.addTextElement(xml, "deletedRecord", deletedRecord.toString());
        XmlUtils.addTextElement(xml, "granularity", granularity.toString());

        if (adminEmails != null) {
            for (String em : adminEmails) {
                XmlUtils.addTextElement(xml, "adminEmail", em);
            }
        }

        if (compressions != null) {
            for (Compression c : compressions) {
                if (c != Compression.IDENTITY) {
                    XmlUtils.addTextElement(xml, "compression", c.toString());
                }
            }
        }

        if (descriptions != null) {
            Element desc = new Element("description", XmlUtils.getOAINamespace());
            for (IIdentifyDescription d : descriptions) {

                desc.addContent(d.toJdomElement(getGranularity()));
                xml.addContent(desc);
            }
        }

        return xml;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public Calendar getEarliestDatestamp() {
        return earliestDatestamp;
    }

    public void setEarliestDatestamp(Calendar earliestDatestamp) {
        this.earliestDatestamp = earliestDatestamp;
    }

    public DeletedRecord getDeletedRecord() {
        return deletedRecord;
    }

    public void setDeletedRecord(DeletedRecord deletedRecord) {
        this.deletedRecord = deletedRecord;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public List<String> getAdminEmails() {
        return adminEmails;
    }

    public void setAdminEmails(List<String> adminEmails) {
        this.adminEmails = adminEmails;
    }

    public List<Compression> getCompressions() {
        return compressions;
    }

    public void setCompressions(List<Compression> compressions) {
        this.compressions = compressions;
    }

    public List<IIdentifyDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<IIdentifyDescription> descriptions) {
        this.descriptions = descriptions;
    }

}
