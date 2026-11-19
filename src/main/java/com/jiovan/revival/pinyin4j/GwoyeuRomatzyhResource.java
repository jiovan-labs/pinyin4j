/*
 * Copyright (c) 2026 Jiovan Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jiovan.revival.pinyin4j;


import java.io.IOException;
import java.util.Objects;
import com.hp.hpl.sparta.Document;
import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.Parser;
import lombok.extern.slf4j.Slf4j;


/**
 * A class contains resource that translates from Hanyu Pinyin to Gwoyeu Romatzyh
 *
 * @author Li Min (xmlerlimin@gmail.com)
 */
@Slf4j
@SuppressWarnings("unused")
class GwoyeuRomatzyhResource {
    /**
     * A DOM model contains Hanyu Pinyin to Gwoyeu Romatzyh mapping
     */
    private Document pinyinToGwoyeuMappingDoc;

    /**
     * Private constructor as part of the singleton pattern.
     */
    private GwoyeuRomatzyhResource() {
        initializeResource();
    }

    /**
     * Singleton factory method
     *
     * @return the one and only MySingleton.
     */
    @SuppressWarnings("SameReturnValue")
    static GwoyeuRomatzyhResource getInstance() {
        return GwoyeuRomatzyhSystemResourceHolder.theInstance;
    }

    /**
     * @return the pinyinToGwoyeuMappingDoc.
     */
    Document getPinyinToGwoyeuMappingDoc() {
        return pinyinToGwoyeuMappingDoc;
    }

    /**
     * @param pinyinToGwoyeuMappingDoc The pinyinToGwoyeuMappingDoc to set
     */
    private void setPinyinToGwoyeuMappingDoc(Document pinyinToGwoyeuMappingDoc) {
        this.pinyinToGwoyeuMappingDoc = pinyinToGwoyeuMappingDoc;
    }

    /**
     * Initialize a DOM contains Hanyu Pinyin to Gwoyeu mapping
     */
    private void initializeResource() {
        try {
            final String mappingFileName = "/META-INF/data/pinyin4j/pinyin_gwoyeu_mapping.xml";
            final String systemId = "";
            // Parse file to DOM Document
            setPinyinToGwoyeuMappingDoc(Parser.parse(systemId, Objects.requireNonNull(GwoyeuRomatzyhResource.class.getResourceAsStream(mappingFileName))));
        } catch (IOException | ParseException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Singleton implementation helper.
     */
    private static class GwoyeuRomatzyhSystemResourceHolder {
        static final GwoyeuRomatzyhResource theInstance = new GwoyeuRomatzyhResource();
    }
}
