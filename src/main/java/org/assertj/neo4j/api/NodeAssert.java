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

import static org.assertj.neo4j.error.ShouldHaveLabel.shouldHaveLabel;
import static org.assertj.neo4j.error.ShouldNotHaveLabel.shouldNotHaveLabel;

import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;

/**
 * Assertions for Neo4J {@link org.neo4j.graphdb.Node}
 *
 * @author Florent Biville
 */
public class NodeAssert extends PropertyContainerAssert<NodeAssert,Node> {

    protected NodeAssert( final Node actual ) {
        super( actual, NodeAssert.class );
    }

    @Override
    public Node getActual() {
        return this.actual;
    }

    /**
     * Verifies that the actual {@link org.neo4j.graphdb.Node} has the given label name<br/>
     * <p>
     * Example:
     * 
     * <pre>
     * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
     * Node node = graph.createNode();
     * node.addLabel( Label.label( &quot;DOUGHNUT_LOVER&quot; ) );
     * 
     * assertThat( node ).hasLabel( &quot;DOUGHNUT_LOVER&quot; );
     * </pre>
     * 
     * If the <code>labelValue</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
     * <p>
     * 
     * @param labelValue the label name to look for in the actual {@link org.neo4j.graphdb.Node}
     * @return this {@link NodeAssert} for assertions chaining
     * 
     * @throws IllegalArgumentException if <code>labelValue</code> is {@code null}.
     * @throws AssertionError if the actual {@link org.neo4j.graphdb.Node} does not contain the given label
     */
    public NodeAssert hasLabel( final String labelValue ) {
        Objects.instance().assertNotNull( this.info, this.actual );

        if ( labelValue == null ) {
            throw new IllegalArgumentException( "The label value to look for should not be null" );
        }
        if ( !hasFoundLabel( labelValue ) ) {
            throw Failures.instance().failure( this.info, shouldHaveLabel( this.actual, labelValue ) );
        }
        return this;
    }

    /**
     * Verifies that the actual {@link org.neo4j.graphdb.Node} does NOT have the given label name<br/>
     * <p>
     * Example:
     * 
     * <pre>
     * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
     * Node node = graph.createNode();
     * node.addLabel( Label.label( &quot;DOUGHNUT_LOVER&quot; ) );
     * 
     * assertThat( node ).doesNotHaveLabel( &quot;FRUIT_LOVER&quot; );
     * </pre>
     * 
     * If the <code>labelValue</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
     * <p>
     * 
     * @param labelValue the label name to look for in the actual {@link org.neo4j.graphdb.Node}
     * @return this {@link NodeAssert} for assertions chaining
     * 
     * @throws IllegalArgumentException if <code>labelValue</code> is {@code null}.
     * @throws AssertionError if the actual {@link org.neo4j.graphdb.Node} does contain the given label
     */
    public NodeAssert doesNotHaveLabel( final String labelValue ) {
        Objects.instance().assertNotNull( this.info, this.actual );

        if ( labelValue == null ) {
            throw new IllegalArgumentException( "The label value to look for should not be null" );
        }
        if ( hasFoundLabel( labelValue ) ) {
            throw Failures.instance().failure( this.info, shouldNotHaveLabel( this.actual, labelValue ) );
        }
        return this;
    }

    /**
     * Verifies that the actual {@link org.neo4j.graphdb.Node} has the given label<br/>
     * <p>
     * Example:
     * 
     * <pre>
     * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
     * Node node = graph.createNode();
     * Label doughnutLover = Label.label( &quot;DOUGHNUT_LOVER&quot; );
     * node.addLabel( doughnutLover );
     * 
     * assertThat( node ).hasLabel( doughnutLover );
     * </pre>
     * 
     * If the <code>label</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
     * <p>
     * 
     * @param label the label to look for in the actual {@link org.neo4j.graphdb.Node}
     * @return this {@link NodeAssert} for assertions chaining
     * 
     * @throws IllegalArgumentException if <code>label</code> is {@code null}.
     * @throws AssertionError if the actual {@link org.neo4j.graphdb.Node} does not contain the given label
     */
    public NodeAssert hasLabel( final Label label ) {
        Objects.instance().assertNotNull( this.info, this.actual );

        if ( label == null ) {
            throw new IllegalArgumentException( "The label to look for should not be null" );
        }
        if ( !this.actual.hasLabel( label ) ) {
            throw Failures.instance().failure( this.info, shouldHaveLabel( this.actual, label.name() ) );
        }
        return this;
    }

    /**
     * Verifies that the actual {@link org.neo4j.graphdb.Node} does NOT have the given label<br/>
     * <p>
     * Example:
     * 
     * <pre>
     * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
     * Node node = graph.createNode();
     * node.addLabel( Label.label( &quot;DOUGHNUT_LOVER&quot; ) );
     * 
     * assertThat( node ).doesNotHaveLabel( Label.label( &quot;FRUIT_LOVER&quot; ) );
     * </pre>
     * 
     * If the <code>label</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
     * <p>
     * 
     * @param label the label to look for in the actual {@link org.neo4j.graphdb.Node}
     * @return this {@link NodeAssert} for assertions chaining
     * 
     * @throws IllegalArgumentException if <code>label</code> is {@code null}.
     * @throws AssertionError if the actual {@link org.neo4j.graphdb.Node} does contain the given label
     */
    public NodeAssert doesNotHaveLabel( final Label label ) {
        Objects.instance().assertNotNull( this.info, this.actual );

        if ( label == null ) {
            throw new IllegalArgumentException( "The label to look for should not be null" );
        }
        if ( this.actual.hasLabel( label ) ) {
            throw Failures.instance().failure( this.info, shouldNotHaveLabel( this.actual, label.name() ) );
        }
        return this;
    }

    private boolean hasFoundLabel( final String labelValue ) {
        for ( final Label label : this.actual.getLabels() ) {
            if ( labelValue.equals( label.name() ) ) {
                return true;
            }
        }
        return false;
    }
}
