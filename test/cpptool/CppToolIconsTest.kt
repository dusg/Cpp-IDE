package cpptool

import cpptool.icons.CppToolIcons
import org.testng.Assert
import org.testng.annotations.Test

class CppToolIconsTest {
    @Test
    fun testLoad() {
        val icon =  CppToolIcons.CPP_FILE

        Assert.assertNotNull(icon)
    }
}
