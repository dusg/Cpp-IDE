package cpptool.psi

import com.intellij.lexer.FlexAdapter

class CppLexerAdapter(highlightMode: Boolean = true,
                      cmode: Boolean = false,
                      c99Mode: Boolean = true,
                      c11Mode: Boolean = true,
                      cpp11Mode: Boolean= true)
    : FlexAdapter(_CppLexer(highlightMode, cmode, c99Mode, c11Mode, cpp11Mode)) {
}