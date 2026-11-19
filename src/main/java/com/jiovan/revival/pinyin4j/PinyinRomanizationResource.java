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
 * Contains the resource supporting translations among different Chinese Romanization systems
 *
 * @author Li Min (xmlerlimin@gmail.com)
 */
@Slf4j
@SuppressWarnings("unused")
class PinyinRomanizationResource {
    /**
     * A DOM model contains variable pinyin presentations
     */
    private Document pinyinMappingDoc;

    /**
     * Private constructor as part of the singleton pattern.
     */
    private PinyinRomanizationResource() {
        initializeResource();
    }

    /**
     * Singleton factory method.
     *
     * @return the one and only MySingleton.
     */
    @SuppressWarnings("SameReturnValue")
    static PinyinRomanizationResource getInstance() {
        return PinyinRomanizationSystemResourceHolder.theInstance;
    }

    /**
     * @return Returns the pinyinMappingDoc.
     */
    Document getPinyinMappingDoc() {
        return pinyinMappingDoc;
    }

    /**
     * @param pinyinMappingDoc The pinyinMappingDoc to set.
     */
    private void setPinyinMappingDoc(Document pinyinMappingDoc) {
        this.pinyinMappingDoc = pinyinMappingDoc;
    }

    /**
     * Initialize a DOM contains variable PinYin representations
     */
    private void initializeResource() {
        try {
            final String mappingFileName = "/META-INF/data/pinyin4j/pinyin_mapping.xml";
            final String systemId = "";
            // Parse file to DOM Document
            setPinyinMappingDoc(Parser.parse(systemId, Objects.requireNonNull(PinyinRomanizationResource.class.getResourceAsStream(mappingFileName))));
        } catch (IOException | ParseException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Singleton implementation helper.
     */
    private static class PinyinRomanizationSystemResourceHolder {
        static final PinyinRomanizationResource theInstance = new PinyinRomanizationResource();
    }
}
