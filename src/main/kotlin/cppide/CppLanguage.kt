package cppide

import com.intellij.lang.Language

class CppLanguage private constructor() : Language("C/C++") {
    companion object {
        val INSTANCE = CppLanguage()
    }
}