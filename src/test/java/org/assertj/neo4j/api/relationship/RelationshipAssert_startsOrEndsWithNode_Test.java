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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

/**
 * Checks <code>{@link org.assertj.neo4j.api.RelationshipAssert#startsOrEndsWithNode(org.neo4j.graphdb.Node)}</code> behavior.
 *
 * @author Florent Biville
 */
public class RelationshipAssert_startsOrEndsWithNode_Test {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private Relationship relationship = mock( Relationship.class );

    @Before
    public void prepare() throws Exception {
        when( this.relationship.getId() ).thenReturn( 42L );
        when( this.relationship.getType() ).thenReturn( RelationshipType.withName( "SOME_TYPE" ) );
    }

    @Test
    public void should_pass_if_relationship_starts_with_node() {
        final Node node = mock( Node.class );
        given_relationship_starts_with_node( node );
        given_relationship_ends_with_node( mock( Node.class ) );

        assertThat( this.relationship ).startsOrEndsWithNode( node );
    }

    @Test
    public void should_pass_if_relationship_ends_with_node() {
        final Node node = mock( Node.class );
        given_relationship_starts_with_node( mock( Node.class ) );
        given_relationship_ends_with_node( node );

        assertThat( this.relationship ).startsOrEndsWithNode( node );
    }

    @Test
    public void should_fail_if_relationship_is_null() {
        this.expectedException.expect( AssertionError.class );
        this.expectedException.expectMessage( "Expecting actual not to be null" );

        assertThat( ( Relationship ) null ).startsOrEndsWithNode( mock( Node.class ) );
    }

    @Test
    public void should_fail_if_relationship_start_node_is_null() {
        given_relationship_ends_with_node( mock( Node.class ) );

        this.expectedException.expect( IllegalStateException.class );
        this.expectedException.expectMessage( "The actual start node should not be null" );

        assertThat( this.relationship ).startsOrEndsWithNode( mock( Node.class ) );
    }

    @Test
    public void should_fail_if_relationship_end_node_is_null() {
        given_relationship_starts_with_node( mock( Node.class ) );

        this.expectedException.expect( IllegalStateException.class );
        this.expectedException.expectMessage( "The actual end node should not be null" );

        assertThat( this.relationship ).startsOrEndsWithNode( mock( Node.class ) );
    }

    @Test
    public void should_fail_if_given_start_node_is_null() {
        given_relationship_starts_with_node( mock( Node.class ) );
        given_relationship_ends_with_node( mock( Node.class ) );

        this.expectedException.expect( IllegalArgumentException.class );
        this.expectedException.expectMessage( "The node to look for should not be null" );

        assertThat( this.relationship ).startsOrEndsWithNode( null );
    }

    public void should_fail_if_relationship_does_NOT_start_or_end_with_given_node() {
        given_relationship_starts_with_node( mock( Node.class ) );
        given_relationship_ends_with_node( mock( Node.class ) );

        this.expectedException.expect( AssertionError.class );
        this.expectedException.expectMessage(
                String.format( "Expecting relationship with ID: 42 and type: SOME_TYPE%nto either start or end with node:" ) );

        assertThat( this.relationship ).startsOrEndsWithNode( mock( Node.class ) );
    }

    private void given_relationship_starts_with_node( final Node node ) {
        when( this.relationship.getStartNode() ).thenReturn( node );
    }

    private void given_relationship_ends_with_node( final Node node ) {
        when( this.relationship.getEndNode() ).thenReturn( node );
    }

}
