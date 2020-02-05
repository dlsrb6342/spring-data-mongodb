/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bson.Document;

/**
 * @author Christoph Strobl
 * @since 3.0
 */
public class ShardKey {

	private static final ShardKey NONE = new ShardKey(Collections.emptyList());
	private final List<String> fields;

	private ShardKey(List<String> fields) {
		this.fields = fields;
	}

	public Document getDocument() {
		Document doc = new Document();
		for (String field : fields) {
			doc.append(field, 1);
		}
		return doc;
	}

	public int size() {
		return fields.size();
	}

	public Collection<String> getFields() {
		return fields;
	}

	public static ShardKey none() {
		return NONE;
	}

	public static ShardKey of(String... fields) {
		return new ShardKey(Arrays.asList(fields));
	}
}
