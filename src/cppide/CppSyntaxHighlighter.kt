package cppide

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.StringEscapesTokenTypes
import com.intellij.psi.tree.IElementType
import cppide.psi.CppLexerAdapter
import cppide.psi.CppTokenTypes
import java.awt.Color
import java.awt.Font

class CppSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val CPP_KEYWORD = TextAttributesKey.createTextAttributesKey(
                "CPP.KEYWORD",
                DefaultLanguageHighlighterColors.KEYWORD
        )
        val C_KEYWORD = TextAttributesKey.createTextAttributesKey(
                "C.KEYWORD",
                DefaultLanguageHighlighterColors.KEYWORD
        )
        val PRE_KEYWORD = TextAttributesKey.createTextAttributesKey(
                "PRE.KEYWORD",
                DefaultLanguageHighlighterColors.KEYWORD
        )
        val CPP_LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
                "CPP.LINE_COMMENT",
                DefaultLanguageHighlighterColors.LINE_COMMENT
        )
        val CPP_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
                "CPP.BLOCK_COMMENT",
                DefaultLanguageHighlighterColors.BLOCK_COMMENT
        )
        val CPP_STRING = TextAttributesKey.createTextAttributesKey(
                "CPP.STRING",
                DefaultLanguageHighlighterColors.STRING
        )
        val CPP_NUMBER = TextAttributesKey.createTextAttributesKey(
                "CPP.NUMBER",
                DefaultLanguageHighlighterColors.NUMBER
        )
        val CPP_OPERATION_SIGN = TextAttributesKey.createTextAttributesKey(
                "CPP.OPERATION_SIGN",
                DefaultLanguageHighlighterColors.OPERATION_SIGN
        )
        val CPP_PARENTHS = TextAttributesKey.createTextAttributesKey(
                "CPP.PARENTHS",
                DefaultLanguageHighlighterColors.PARENTHESES
        )
        val CPP_BRACKETS = TextAttributesKey.createTextAttributesKey(
                "CPP.BRACKETS",
                DefaultLanguageHighlighterColors.BRACKETS
        )
        val CPP_BRACES = TextAttributesKey.createTextAttributesKey(
                "CPP.BRACES",
                DefaultLanguageHighlighterColors.BRACES
        )
        val CPP_COMMA = TextAttributesKey.createTextAttributesKey(
                "CPP.COMMA",
                DefaultLanguageHighlighterColors.COMMA
        )
        val CPP_DOT = TextAttributesKey.createTextAttributesKey(
                "CPP.DOT",
                DefaultLanguageHighlighterColors.DOT
        )
        val CPP_SEMICOLON = TextAttributesKey.createTextAttributesKey(
                "CPP.SEMICOLON",
                DefaultLanguageHighlighterColors.SEMICOLON
        )

        val CPP_UNUSED = createUnusedAttributes()

        val CPP_NAMESPACE = createNamespaceAttributes()

        val CPP_FUNCTION = createFunctionAttributes()

        val CPP_STATIC_FUNCTION = createStaticFunctionAttributes()

        val CPP_STATIC = createStaticAttributes()

        val CPP_FIELD = createFieldAttributes()

        val CPP_METHOD = createMethodAttributes()

        val CPP_PARAMETER = createParameterAttributes()

        val CPP_TYPE = createTypeAttributes()

        val CPP_MACROS = createMacrosAttributes()

        val CPP_LABEL = createLabelAttributes()

        val CPP_PP_ARG = createPPArgAttributes()

        val CPP_PP_SKIPPED = createPPSkippedAttributes()

        val CPP_CONSTANT = createConstantAttributes()

        private val CPP_VALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey(
                "CPP.VALID_STRING_ESCAPE",
                DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE
        )
        private val CPP_INVALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey(
                "CPP.INVALID_STRING_ESCAPE",
                DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE
        )
        private val CPP_BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
                "CPP.BADCHARACTER",
                HighlighterColors.BAD_CHARACTER
        )
        private val EMPTY_KEYS = arrayOfNulls<TextAttributesKey>(0)

        private fun createPPArgAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.PP_ARG")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color(0x80, 0, 0)
            return textAttributesKey
        }

        private fun createPPSkippedAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.PP_SKIPPED")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color(0x80, 0x80, 0x80)
            attrs.fontType = Font.ITALIC
            return textAttributesKey
        }

        private fun createLabelAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.LABEL")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color.magenta
            return textAttributesKey
        }

        private fun createConstantAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.CONSTANT")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color.pink.darker().darker()
            attrs.fontType = Font.ITALIC
            return textAttributesKey
        }

        private fun createTypeAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.TYPE")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color.green.darker().darker()
            return textAttributesKey
        }

        private fun createMacrosAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.MACROS")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color(0xa5, 0x2a, 0x2a)
            return textAttributesKey
        }

        private fun createFunctionAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.FUNCTION")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color.blue
            return textAttributesKey
        }

        private fun createStaticFunctionAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.STATIC_FUNCTION", createFunctionAttributes())
            val attrs = textAttributesKey.defaultAttributes
            attrs.fontType = Font.BOLD or Font.ITALIC
            return textAttributesKey
        }

        private fun createMethodAttributes(): TextAttributesKey {
            val textAttributesKey =
                    TextAttributesKey.createTextAttributesKey("CPP.METHOD", createFunctionAttributes())
            val attrs = textAttributesKey.defaultAttributes
            attrs.fontType = Font.ITALIC
            return textAttributesKey
        }

        private fun createStaticAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.STATIC")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color.black.darker().darker()
            attrs.fontType = Font.BOLD or Font.ITALIC
            return textAttributesKey
        }

        private fun createFieldAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.FIELD")
            val attrs = textAttributesKey.defaultAttributes
            attrs.fontType = Font.ITALIC
            return textAttributesKey
        }

        private fun createParameterAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.PARAMETER")
            val attrs = textAttributesKey.defaultAttributes
            attrs.effectType = EffectType.LINE_UNDERSCORE
            attrs.effectColor = Color.black
            return textAttributesKey
        }

        private fun createNamespaceAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.NAMESPACE")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color(0x90, 0, 0x90)
            attrs.fontType = Font.BOLD
            return textAttributesKey
        }

        private fun createUnusedAttributes(): TextAttributesKey {
            val textAttributesKey = TextAttributesKey.createTextAttributesKey("CPP.UNUSED")
            val attrs = textAttributesKey.defaultAttributes
            attrs.foregroundColor = Color.darkGray
            attrs.effectType = EffectType.STRIKEOUT

            return textAttributesKey
        }

        private var keys1: MutableMap<IElementType, TextAttributesKey> = mutableMapOf()

        init {
            fillMap(keys1, CppTokenTypes.CPP_KEYWORDS, CPP_KEYWORD)
            fillMap(keys1, CppTokenTypes.C_KEYWORDS, C_KEYWORD)
            keys1[CppTokenTypes.PRE_KEYWORD] = PRE_KEYWORD
            fillMap(keys1, CppTokenTypes.C_KEYWORDS, C_KEYWORD)
            fillMap(keys1, CppTokenTypes.OPERATIONS, CPP_OPERATION_SIGN)

            keys1[CppTokenTypes.C_STYLE_COMMENT] = CPP_BLOCK_COMMENT
            keys1[CppTokenTypes.END_OF_LINE_COMMENT] = CPP_LINE_COMMENT

            keys1[CppTokenTypes.NUMERIC_LITERAL] = CPP_NUMBER
            keys1[CppTokenTypes.STRING_LITERAL] = CPP_STRING
            keys1[CppTokenTypes.SINGLE_QUOTE_STRING_LITERAL] = CPP_STRING

            keys1[CppTokenTypes.LPAR] = CPP_PARENTHS
            keys1[CppTokenTypes.RPAR] = CPP_PARENTHS

            keys1[CppTokenTypes.LBRACE] = CPP_BRACES
            keys1[CppTokenTypes.RBRACE] = CPP_BRACES

            keys1[CppTokenTypes.LBRACKET] = CPP_BRACKETS
            keys1[CppTokenTypes.RBRACKET] = CPP_BRACKETS

            keys1[CppTokenTypes.COMMA] = CPP_COMMA
            keys1[CppTokenTypes.DOT] = CPP_DOT
            keys1[CppTokenTypes.ARROW] = CPP_DOT
            keys1[CppTokenTypes.SEMICOLON] = CPP_SEMICOLON

            keys1[StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN] = CPP_VALID_STRING_ESCAPE
            keys1[StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN] = CPP_INVALID_STRING_ESCAPE
            keys1[StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN] = CPP_INVALID_STRING_ESCAPE

            keys1[CppTokenTypes.BAD_CHARACTER] = CPP_BAD_CHARACTER
        }
    }

    /**
     * Returns the list of text attribute keys used for highlighting the specified token type. The attributes of all attribute keys
     * returned for the token type are successively merged to obtain the color and attributes of the token.
     *
     * @param tokenType The token type for which the highlighting is requested.
     * @return The array of text attribute keys.
     */
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (keys1.contains(tokenType)) {
            return arrayOf(keys1[tokenType] as TextAttributesKey)
        }
        return emptyArray()
    }

    /**
     * Returns the lexer used for highlighting the file. The lexer is invoked incrementally when the file is changed, so it must be
     * capable of saving/restoring state and resuming lexing from the middle of the file.
     *
     * @return The lexer implementation.
     */
    override fun getHighlightingLexer(): Lexer {
        return CppLexerAdapter()
    }
}

class CppSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    /**
     * Override this method to provide syntax highlighting (coloring) capabilities for your language implementation.
     * By syntax highlighting we mean highlighting of keywords, comments, braces etc. where lexing the file content is enough
     * to identify proper highlighting attributes.
     *
     *
     * Default implementation doesn't highlight anything.
     *
     * @param project     might be necessary to gather various project settings from.
     * @param virtualFile might be necessary to collect file specific settings
     * @return `SyntaxHighlighter` interface implementation for this particular language.
     */
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
        return CppSyntaxHighlighter()
    }

}