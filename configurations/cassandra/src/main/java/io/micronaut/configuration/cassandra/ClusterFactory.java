/*
 * Copyright 2017-2018 original authors
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

package io.micronaut.configuration.cassandra;

import com.datastax.driver.core.Cluster;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;

/**
 * Creates cassandra cluster for each configuration bean.
 *
 * @author Nirav Assar
 * @since 1.0
 */
@Factory
public class ClusterFactory {

    /**
     * Creates the {@link Cluster} bean for the given configuration.
     *
     * @param cassandraConfiguration The {@link CassandraConfiguration} object
     * @return A {@link Cluster} bean
     */
    @EachBean(CassandraConfiguration.class)
    @Bean(preDestroy = "close")
    public Cluster cassandraCluster(CassandraConfiguration cassandraConfiguration) {

        return cassandraConfiguration.builder.build();
    }
}
