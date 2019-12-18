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
package org.assertj.neo4j.api.relationship;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.neo4j.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Checks <code>{@link org.assertj.neo4j.api.RelationshipAssert#doesNotEndWithNode(Node)}</code> behavior.
 *
 * @author Florent Biville
 */
public class RelationshipAssert_doesNotEndWithNode_Test {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private Relationship relationship = mock( Relationship.class );

    public void should_fail_if_relationship_ends_with_node() {
        final Node node = mock( Node.class );
        given_relationship_ends_with_node( node );
        this.expectedException.expect( AssertionError.class );
        this.expectedException.expectMessage( String.format( "Expecting:%n" ) );
        this.expectedException.expectMessage( String.format( "%nto not end with node:%n" ) );

        assertThat( this.relationship ).doesNotEndWithNode( node );
    }

    @Test
    public void should_fail_if_relationship_is_null() {
        this.expectedException.expect( AssertionError.class );
        this.expectedException.expectMessage( "Expecting actual not to be null" );

        assertThat( ( Relationship ) null ).doesNotEndWithNode( mock( Node.class ) );
    }

    @Test
    public void should_fail_if_relationship_end_node_is_null() {
        this.expectedException.expect( IllegalStateException.class );
        this.expectedException.expectMessage( "The actual end node should not be null" );

        assertThat( this.relationship ).doesNotEndWithNode( mock( Node.class ) );
    }

    @Test
    public void should_fail_if_given_end_node_is_null() {
        given_relationship_ends_with_node( mock( Node.class ) );

        this.expectedException.expect( IllegalArgumentException.class );
        this.expectedException.expectMessage( "The end node to look for should not be null" );

        assertThat( this.relationship ).doesNotEndWithNode( null );
    }

    @Test
    public void should_pass_if_relationship_does_not_end_with_given_node() {
        given_relationship_ends_with_node( mock( Node.class ) );

        assertThat( this.relationship ).doesNotEndWithNode( mock( Node.class ) );
    }

    private void given_relationship_ends_with_node( final Node node ) {
        when( this.relationship.getEndNode() ).thenReturn( node );
    }

}
