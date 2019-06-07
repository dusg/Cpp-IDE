package lsp

import lsp.structs.DiagnosticSeverity
import org.testng.Assert
import org.testng.annotations.Test

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