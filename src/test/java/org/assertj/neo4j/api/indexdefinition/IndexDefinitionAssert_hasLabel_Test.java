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
package org.assertj.neo4j.api.indexdefinition;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.neo4j.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.schema.IndexDefinition;

/**
 * Checks <code>{@link org.assertj.neo4j.api.IndexDefinitionAssert#hasLabel(Label)}</code> behavior.
 *
 * @author Agathe Vaisse
 * @author Gwenaelle Rispal
 */
public class IndexDefinitionAssert_hasLabel_Test {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private IndexDefinition indexDefinition = mock( IndexDefinition.class );

    @Test
    public void should_pass_if_index_definition_has_label() {
        given_index_definition_with_label( "Black Widow" );

        assertThat( this.indexDefinition ).hasLabel( Label.label( "Black Widow" ) );
    }

    @Test
    public void should_fail_if_index_definition_is_null() {
        this.expectedException.expect( AssertionError.class );
        this.expectedException.expectMessage( "Expecting actual not to be null" );

        assertThat( ( IndexDefinition ) null ).hasLabel( Label.label( "Thor" ) );
    }

    @Test
    public void should_fail_if_index_definition_label_is_null() {
        given_index_definition_with_label( "Hulk" );
        this.expectedException.expect( IllegalArgumentException.class );
        this.expectedException.expectMessage( "The label to look for should not be null" );

        assertThat( this.indexDefinition ).hasLabel( ( Label ) null );
    }

    @Test
    public void should_fail_if_index_definition_has_not_expected_label() {
        given_index_definition_with_label( "Captain America" );
        this.expectedException.expect( AssertionError.class );

        assertThat( this.indexDefinition ).hasLabel( Label.label( "Black Widow" ) );
    }

    private void given_index_definition_with_label( final String value ) {
        final Label label = Label.label( value );
        when( this.indexDefinition.getLabels() ).thenReturn( Collections.singleton( label ) );
    }
}
