/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import io.gitwalk.Library
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class LibraryTest {
    @Test
    fun someLibraryMethodReturnsTrue() {
        val classUnderTest = Library()
        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'")
    }
}
