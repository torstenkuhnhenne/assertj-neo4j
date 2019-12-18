/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2013-2019 the original author or authors.
 */
package org.assertj.neo4j.api;

import static org.assertj.neo4j.api.InstanceOfAssertFactories.propertyContainer;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.Mockito;
import org.neo4j.graphdb.Entity;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.schema.ConstraintDefinition;
import org.neo4j.graphdb.schema.IndexDefinition;

/**
 * @author Stefano Cordio
 * @since 2.0.2
 */
public class InstanceOfAssertFactoriesTest {

    @Test
    public void property_container_factory_should_allow_property_container_assertions() {
        final Object value = mock( Entity.class );

        final PropertyContainerAssert<?,Entity> result = assertThat( value ).asInstanceOf( propertyContainer( Entity.class ) );

        result.doesNotHavePropertyKey( "key" );
    }

    @Test
    public void node_factory_should_allow_node_assertions() {
        final Object value = mock( Node.class );

        final NodeAssert result = assertThat( value ).asInstanceOf( InstanceOfAssertFactories.NODE );

        result.doesNotHaveLabel( Label.label( "label" ) );
    }

    @Test
    public void relationship_factory_should_allow_relationship_assertions() {
        final Object value = mock( Relationship.class, Mockito.RETURNS_MOCKS );

        final RelationshipAssert result = assertThat( value ).asInstanceOf( InstanceOfAssertFactories.RELATIONSHIP );

        result.doesNotHaveType( "type" );
    }

    @Test
    public void path_factory_should_allow_path_assertions() {
        final Object value = mock( Path.class );

        final PathAssert result = assertThat( value ).asInstanceOf( InstanceOfAssertFactories.PATH );

        result.hasLength( 0 );
    }

    @Test
    public void result_factory_should_allow_result_assertions() {
        final Object value = mock( Result.class );

        final ResultAssert result = assertThat( value ).asInstanceOf( InstanceOfAssertFactories.RESULT );

        result.isEmpty();
    }

    @SuppressWarnings("CastCanBeRemovedNarrowingVariableType")
    @Test
    public void constraint_definition_factory_should_allow_constraint_definition_assertions() {
        final Object value = mock( ConstraintDefinition.class, Mockito.RETURNS_DEEP_STUBS );
        given( ( ( ConstraintDefinition ) value ).getLabel().name() ).willReturn( "label" );

        final ConstraintDefinitionAssert result = assertThat( value ).asInstanceOf( InstanceOfAssertFactories.CONSTRAINT_DEFINITION );

        result.hasLabel( "label" );
    }

    @Test
    public void index_definition_factory_should_allow_index_definition_assertions() {
        final Object value = mock( IndexDefinition.class, Mockito.RETURNS_MOCKS );

        final IndexDefinitionAssert result = assertThat( value ).asInstanceOf( InstanceOfAssertFactories.INDEX_DEFINITION );

        result.doesNotHaveLabel( "label" );
    }

}
