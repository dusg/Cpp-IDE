package test

import lsp.DiagnosticSeverity
import org.testng.Assert
import org.testng.annotations.Test

import org.testng.Assert.*

class DiagnosticSeverityTest {

    @Test
    fun testToNumber() {
        Assert.assertEquals(DiagnosticSeverity.Error.toNumber(), 1)
    }

    @Test
    fun testToString1() {
        Assert.assertEquals(DiagnosticSeverity.Error.toString(), "Error")
    }
}