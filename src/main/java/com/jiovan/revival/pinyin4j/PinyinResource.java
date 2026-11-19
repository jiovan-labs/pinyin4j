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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nonnull;


/**
 * Load pinyin resources
 *
 * @author stuxuhai (dczxxuhai@gmail.com)
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class PinyinResource {
    @SuppressWarnings("SameParameterValue")
    public static Reader newClassPathReader(String classpath) {
        if (classpath == null || classpath.isEmpty()) {
            return null;
        }
        InputStream input = PinyinResource.class.getResourceAsStream(classpath);
        return (input == null) ? null : new InputStreamReader(input, StandardCharsets.UTF_8);
    }

    public static Reader newFileReader(String path) throws IOException {
        if (path == null || path.isEmpty()) {
            return null;
        }
        return new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8);
    }

    @Nonnull
    public static Map<String, String> getResource(Reader reader) {
        Map<String, String> map = new ConcurrentHashMap<>();
        try {
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("=");
                map.put(tokens[0], tokens[1]);
            }
            br.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return map;
    }

    @Nonnull
    public static Map<String, String> getChineseResource() {
        return getResource(newClassPathReader("/META-INF/data/jpinyin/chinese.dict"));
    }
}
