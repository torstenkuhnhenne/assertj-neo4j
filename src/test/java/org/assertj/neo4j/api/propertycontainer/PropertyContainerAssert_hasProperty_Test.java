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
package org.assertj.neo4j.api.propertycontainer;

import static org.junit.rules.ExpectedException.none;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.neo4j.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.graphdb.Entity;

/**
 * Checks <code>{@link org.assertj.neo4j.api.PropertyContainerAssert#hasProperty(String,Object)}</code> behavior.
 *
 * @author Florent Biville
 */
public class PropertyContainerAssert_hasProperty_Test {

    @Rule
    public ExpectedException expectedException = none();

    private Entity propertyContainer = mock( Entity.class );

    @Test
    public void should_pass_when_property_container_has_property() {
        given_property_container_with_property( "name", "Emil Eifrem" );

        assertThat( this.propertyContainer ).hasProperty( "name", "Emil Eifrem" );
    }

    @Test
    public void should_fail_when_given_property_key_is_null() {
        this.expectedException.expect( IllegalArgumentException.class );
        this.expectedException.expectMessage( "The key to look for should not be null" );

        given_property_container_with_property( "name", "Emil Eifrem" );

        assertThat( this.propertyContainer ).hasProperty( null, "Emil Eifrem" );
    }

    @Test
    public void should_fail_when_given_property_value_is_null() {
        this.expectedException.expect( IllegalArgumentException.class );
        this.expectedException.expectMessage( "The value to look for should not be null" );

        given_property_container_with_property( "name", "Emil Eifrem" );

        assertThat( this.propertyContainer ).hasProperty( "name", null );
    }

    @Test
    public void should_fail_when_property_container_has_expected_key_but_not_same_value() {
        this.expectedException.expect( AssertionError.class );

        given_property_container_with_property( "name", "Emil Eifrem" );

        assertThat( this.propertyContainer ).hasProperty( "name", "Peter Neubauer" );
    }

    private void given_property_container_with_property( final String key, final String value ) {
        when( this.propertyContainer.hasProperty( key ) ).thenReturn( true );
        when( this.propertyContainer.getProperty( key, null ) ).thenReturn( value );
    }

}
