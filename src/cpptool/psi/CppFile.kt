package cpptool.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import cpptool.CppFileType
import cpptool.CppLanguage

class CppFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, CppLanguage.INSTANCE) {
    /**
     * Returns the file type for the file.
     *
     * @return the file type instance.
     */
    override fun getFileType(): FileType {
        return CppFileType.INSTANCE
    }

    override fun toString(): String {
        return "Cpp File"
    }
}