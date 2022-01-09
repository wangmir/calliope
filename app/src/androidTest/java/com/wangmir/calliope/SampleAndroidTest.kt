package com.wangmir.calliope

import android.content.Context
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.*
import androidx.test.platform.app.InstrumentationRegistry

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class SampleAndroidTest {

    companion object {

        val context: Context get() = InstrumentationRegistry.getInstrumentation().context

        @BeforeAll
        @JvmStatic
        fun init() {
            println("Finish Initializing")
        }

        @AfterAll
        @JvmStatic
        fun close() {
            println("Finish closing")
        }
    }

    @Test
    @Order(1)
    fun sampleTest() {
        println("Sample Test")
        val testVal = "name"
        assertThat(testVal).isEqualTo("name")
    }
}
