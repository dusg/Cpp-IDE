package cpptool

import com.intellij.openapi.fileTypes.LanguageFileType
import cpptool.icons.CppToolIcons
import javax.swing.Icon

class CppFileType private constructor() : LanguageFileType(CppLanguage.INSTANCE) {
    companion object {
        val INSTANCE = CppFileType()
    }
    /**
     * Returns the icon used for showing files of the type.
     *
     * @return The icon instance, or `null` if no icon should be shown.
     */
    override fun getIcon(): Icon? {
        return CppToolIcons.CPP_FILE
    }

    /**
     * Returns the name of the file type. The name must be unique among all file types registered in the system.
     *
     * @return The file type name.
     */
    override fun getName(): String {
        return "c/c++ file"
    }

    /**
     * Returns the default extension for files of the type.
     *
     * @return The extension, *not* including the leading '.'.
     */
    override fun getDefaultExtension(): String {
        return "cpp;cc;c;h;hpp"
    }

    /**
     * Returns the user-readable description of the file type.
     *
     * @return The file type description.
     */
    override fun getDescription(): String {
        return "C/C++ Language file"
    }
}