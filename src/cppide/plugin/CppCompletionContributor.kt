package cppide.plugin

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import cppide.CppLanguage
import cppide.psi.CppElementType
import cppide.psi.CppTokenTypes

class CppCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
        result.addElement(LookupElementBuilder.create("Hello...."))
    }
}

class CppCompletionContributor : CompletionContributor {

    constructor() {
        extend(
                CompletionType.BASIC,
                PlatformPatterns.not(PlatformPatterns.alwaysFalse()),
                CppCompletionProvider()
        )
    }

    override fun invokeAutoPopup(position: PsiElement, typeChar: Char): Boolean {
        return true
    }
}