package cppide.psi

import com.intellij.psi.tree.IElementType
import cppide.CppLanguage

class CppElementType(s:String) : IElementType(s, CppLanguage.INSTANCE) {
}