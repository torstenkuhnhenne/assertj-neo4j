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
package org.assertj.neo4j.api.node;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.neo4j.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

/**
 * Checks <code>{@link org.assertj.neo4j.api.NodeAssert#hasLabel(String)}</code> behavior.
 *
 * @author Florent Biville
 */
public class NodeAssert_hasLabel_represented_as_string_Test {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Node node = mock( Node.class );

    @Test
    public void should_pass_if_node_has_label() {
        given_node_with_label( "MAMMAL" );

        assertThat( this.node ).hasLabel( "MAMMAL" );
    }

    @Test
    public void should_fail_if_node_is_null() {
        this.expectedException.expect( AssertionError.class );
        this.expectedException.expectMessage( "Expecting actual not to be null" );

        assertThat( ( Node ) null ).hasLabel( "LABEL" );
    }

    @Test
    public void should_fail_if_label_value_is_null() {
        this.expectedException.expect( IllegalArgumentException.class );

        assertThat( this.node ).hasLabel( ( String ) null );
    }

    @Test
    public void should_fail_if_node_does_NOT_have_label() {
        this.expectedException.expect( AssertionError.class );

        given_node_with_label( "REPTILE" );

        assertThat( this.node ).hasLabel( "MAMMAL" );
    }

    @SuppressWarnings("unchecked")
    private void given_node_with_label( final String label ) {
        final ResourceIterable<Label> labels = mock( ResourceIterable.class );
        final ResourceIterator<Label> iterator = mock( ResourceIterator.class );
        when( iterator.next() ).thenReturn( Label.label( label ) );
        when( iterator.hasNext() ).thenReturn( true, false );
        when( labels.iterator() ).thenReturn( iterator );
        when( this.node.getLabels() ).thenReturn( labels );
        final GraphDatabaseService graph = mock( GraphDatabaseService.class );
        when( graph.beginTx() ).thenReturn( mock( Transaction.class ) );
    }

}
