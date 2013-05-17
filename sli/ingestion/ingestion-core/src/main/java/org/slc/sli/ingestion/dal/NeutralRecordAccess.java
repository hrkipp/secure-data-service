/*
 * Copyright 2012-2013 inBloom, Inc. and its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.slc.sli.ingestion.dal;


/**
 * Interface for access to NeutralRecords
 *
 * @author dduran
 *
 */
public interface NeutralRecordAccess {

    long collectionCountForJob(String collectionNameAsStaged);

    long countCreationTimeWithinRange(String collectionName, long min, long max);

    void cleanupJob(String batchJobId);

    /**
     * Ensure that the underyling data store has the appropriate indexes.
     */
    void ensureIndexes();

}
