package es.alexcortinas.oai.dataprovider;

import java.util.Calendar;
import java.util.List;

import es.alexcortinas.oai.dataprovider.model.Identify;
import es.alexcortinas.oai.dataprovider.model.MetadataFormat;
import es.alexcortinas.oai.dataprovider.model.Record;
import es.alexcortinas.oai.dataprovider.model.RecordHeader;
import es.alexcortinas.oai.dataprovider.model.Set;
import es.alexcortinas.oai.dataprovider.model.constants.Granularity;

public interface IOAIDelegate {

    /**
     * If the repository supports sets, the set list must not be empty
     * 
     * @return
     */
    public boolean getSupportsSets();

    public String getBaseURL();

    public Granularity getFinestGranularity();

    public Identify identify();

    public Integer getMaxElementsByPageReturned();

    /**
     * Check if the metadata format is allowed in the repository. If identifier
     * is not null, check if the metadata format is allowed for the record with
     * it.
     * 
     * @param metadataFormat
     *            required
     * @param identifier
     *            optional
     * @return true if the metadata format is allowed in the repository or for
     *         the record, false in other case, and null if the record does not
     *         exists
     */
    public Boolean existsMetadataFormat(String metadataPrefix, String identifier);

    /**
     * Returns the record with the identifier and in the metadata choosed.
     * Returns null if the record does not exists or it does not exist with the
     * metadata format selected (it should be checked before with
     * existsMetadataFormat).
     * 
     * @param identifier
     * @param metadataPrefix
     */
    public Record getRecord(String identifier, String metadataPrefix);

    /**
     * Returns the number of records selected.
     * 
     * @param metadataPrefix
     * @param from
     * @param until
     * @param set
     * @return
     */
    public Integer countRecords(String metadataPrefix, Calendar from, Calendar until, String set);

    /**
     * Returns the headers of the records selected.
     * 
     * @param metadataPrefix
     * @param from
     * @param until
     * @param set
     * @param start
     *            Starting at 0
     * @param count
     *            If 0, expecting to get all the elements
     * @return
     */
    public List<RecordHeader> listIdentifiers(String metadataPrefix, Calendar from, Calendar until, String set,
            Integer start, Integer count);

    /**
     * Returns the records selected.
     * 
     * @param metadataPrefix
     * @param from
     * @param until
     * @param set
     * @param start
     *            Starting at 0
     * @param count
     *            If 0, expecting to get all the elements
     * @return
     */
    public List<Record> listRecords(String metadataPrefix, Calendar from, Calendar until, String set, Integer start,
            Integer count);

    /**
     * Returns the metadata formats of the repository or of the record, in case
     * identifier is not null. If the identifier is not null and it does not
     * exists in the repository, it should return null (not empty list - empty
     * list will be returned if there are no metadata formats for the record)
     * 
     * @param identifier
     * @return the list if there are formats, empty list if there are not, null
     *         if the identifier does not exists
     */
    public List<MetadataFormat> listMetadataFormats(String identifier);

    /**
     * Returns the number of sets of the repository.
     * 
     * @return
     */
    public Integer countSets();

    /**
     * Returns the selected sets of the repository.
     * 
     * @param start
     *            Starting at 0
     * @param count
     *            If 0, expecting to get all the elements
     * @return
     */
    public List<Set> listSets(Integer start, Integer count);

}
