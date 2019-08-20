package cppide.psi

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import cppide.CppLanguage

class CppElementType(s:String) : IElementType(s, CppLanguage.INSTANCE) {
}

class CppFileElementType : IFileElementType {
    constructor() : super(CppLanguage.INSTANCE)
}