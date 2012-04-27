package org.slc.sli.ingestion.smooks;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.milyn.Smooks;
import org.milyn.delivery.Visitor;
import org.xml.sax.SAXException;

import org.slc.sli.ingestion.FileType;
import org.slc.sli.ingestion.NeutralRecord;
import org.slc.sli.ingestion.NeutralRecordFileWriter;
import org.slc.sli.ingestion.ResourceWriter;
import org.slc.sli.ingestion.dal.NeutralRecordMongoAccess;
import org.slc.sli.ingestion.landingzone.IngestionFileEntry;
import org.slc.sli.ingestion.validation.ErrorReport;

/**
 * Factory class for Smooks
 *
 * @author dduran
 *
 */
public class SliSmooksFactory {

    private Map<FileType, SliSmooksConfig> sliSmooksConfigMap;
    private String beanId;
    private NeutralRecordMongoAccess nrMongoStagingWriter;
    private Map<String, Smooks> mapSmooks = new HashMap<String, Smooks>();

    public Smooks createInstance(IngestionFileEntry ingestionFileEntry, NeutralRecordFileWriter fileWriter,
            ErrorReport errorReport) throws IOException, SAXException {

        FileType fileType = ingestionFileEntry.getFileType();
        SliSmooksConfig sliSmooksConfig = sliSmooksConfigMap.get(fileType);
        if (sliSmooksConfig != null) {

            return createSmooksFromConfig(sliSmooksConfig, fileWriter, errorReport, ingestionFileEntry.getBatchJobId(),
                    ingestionFileEntry);

        } else {
            errorReport.fatal("File type not supported : " + fileType, SliSmooksFactory.class);
            throw new IllegalArgumentException("File type not supported : " + fileType);
        }
    }

    private Smooks createSmooksFromConfig(SliSmooksConfig sliSmooksConfig, NeutralRecordFileWriter fileWriter,
            ErrorReport errorReport, String batchJobId, IngestionFileEntry fe) throws IOException, SAXException {

        Smooks smooks = fetchSmooks(sliSmooksConfig);

        // based on target selectors for this file type, add visitors
        List<String> targetSelectorList = sliSmooksConfig.getTargetSelectors();
        if (targetSelectorList != null) {

            // just one visitor instance that can be added with multiple target selectors
            Visitor smooksEdFiVisitor = SmooksEdFiVisitor.createInstance(beanId, batchJobId, fileWriter, errorReport,
                    fe);

            nrMongoStagingWriter.registerBatchId(batchJobId);
            nrMongoStagingWriter.ensureIndex();

            ((SmooksEdFiVisitor) smooksEdFiVisitor).setNrMongoStagingWriter(nrMongoStagingWriter);
            for (String targetSelector : targetSelectorList) {
                smooks.addVisitor(smooksEdFiVisitor, targetSelector);
            }
        }
        return smooks;
    }

    /**
     * Returns a Smooks object for the given config, either from cache or by constructing a new
     * instance. Constructing smooks objects are slow, hence our use of a cache.
     *
     * @param sliSmooksConfig
     * @return
     * @throws IOException
     * @throws SAXException
     */
    private Smooks fetchSmooks(SliSmooksConfig sliSmooksConfig) throws IOException, SAXException {

        String configName = sliSmooksConfig.getConfigFileName();
        Smooks inCache = mapSmooks.get(configName);
        if (inCache == null) {
            Smooks smooks = new Smooks(configName);
            mapSmooks.put(configName, smooks);
            return smooks;
        } else {
            return inCache;
        }
    }

    public void setSliSmooksConfigMap(Map<FileType, SliSmooksConfig> sliSmooksConfigMap) {
        this.sliSmooksConfigMap = sliSmooksConfigMap;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public void setNrMongoStagingWriter(ResourceWriter<NeutralRecord> nrMongoStagingWriter) {
        this.nrMongoStagingWriter = (NeutralRecordMongoAccess) nrMongoStagingWriter;
    }
}
