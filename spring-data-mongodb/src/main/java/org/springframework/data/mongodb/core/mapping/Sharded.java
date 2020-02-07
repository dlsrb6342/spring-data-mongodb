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
package org.springframework.data.mongodb.core.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.data.annotation.Persistent;

/**
 * The {@link Sharded} annotation provides meta information about the actual distribution of data across multiple
 * machines. The {@link #shardKey()} is used to distribute documents across shards. <br />
 * Please visit the <a href="https://docs.mongodb.com/manual/sharding/">MongoDB Documentation</a> for more information
 * about requirements and limitations of sharding. <br />
 * Spring Data will automatically add the shard key to filter queries used for
 * {@link com.mongodb.client.MongoCollection#replaceOne(org.bson.conversions.Bson, Object)} operations triggered by
 * {@code save} operations on {@link org.springframework.data.mongodb.core.MongoOperations} and
 * {@link org.springframework.data.mongodb.core.ReactiveMongoOperations} as well as {@code update/upsert} operation
 * replacing/upserting a single existing document as long as the given
 * {@link org.springframework.data.mongodb.core.query.UpdateDefinition} holds a full copy of the entity. <br />
 * All other operations that require the presence of the {@literal shard key} in the filter query need to provide the
 * information via the {@link org.springframework.data.mongodb.core.query.Query} parameter when invoking the method.
 *
 * @author Christoph Strobl
 * @since 3.0
 */
@Persistent
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Sharded {

	/**
	 * Alias for {@link #shardKey()}.
	 *
	 * @return {@literal _id} by default.
	 * @see #shardKey()
	 */
	@AliasFor("shardKey")
	String[] value() default { "_id" };

	/**
	 * The shard key determines the distribution of the collection’s documents among the cluster’s shards. The shard key
	 * is either a single or multiple indexed properties that exist in every document in the collection. <br />
	 * By default the {@literal id} property is used for sharding. <br />
	 * <strong>NOTE</strong> Required indexes will not be created automatically. Use
	 * {@link org.springframework.data.mongodb.core.index.Indexed} or
	 * {@link org.springframework.data.mongodb.core.index.CompoundIndex} along with enabled
	 * {@link org.springframework.data.mongodb.config.MongoConfigurationSupport#autoIndexCreation() auto index creation}
	 * or set up them up via
	 * {@link org.springframework.data.mongodb.core.index.IndexOperations#ensureIndex(org.springframework.data.mongodb.core.index.IndexDefinition)}.
	 *
	 * @return {@literal _id} by default.
	 */
	@AliasFor("value")
	String[] shardKey() default { "_id" };
}
