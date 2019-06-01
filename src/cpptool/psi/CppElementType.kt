package cpptool.psi

import com.intellij.psi.tree.IElementType
import cpptool.CppLanguage

class CppElementType(s:String) : IElementType(s, CppLanguage.INSTANCE) {
}