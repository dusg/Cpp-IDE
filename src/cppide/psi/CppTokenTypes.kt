package cppide.psi

import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

class CppTokenTypes {
    companion object {
        @JvmField
        val BLOCK = CppElementType("BLOCK")
        @JvmField
        val PARENS: IElementType = CppElementType("PARENS")
        @JvmField
        val STATEMENT: IElementType = CppElementType("STATEMENT")
        @JvmField
        val IDENTIFIER: IElementType = CppElementType("IDENTIFIER")
        @JvmField
        val WHITE_SPACE = TokenType.WHITE_SPACE

        @JvmField
        val BAD_CHARACTER = TokenType.BAD_CHARACTER

        @JvmField
        val END_OF_LINE_COMMENT: IElementType = CppElementType("END_OF_LINE_COMMENT")
        @JvmField
        val C_STYLE_COMMENT: IElementType = CppElementType("C_STYLE_COMMENT")

        // Keywords:
        @JvmField
        val BREAK_KEYWORD: IElementType = CppElementType("BREAK_KEYWORD")
        @JvmField
        val STATIC_KEYWORD: IElementType = CppElementType("STATIC_KEYWORD")
        @JvmField
        val VIRTUAL_KEYWORD: IElementType = CppElementType("VIRTUAL_KEYWORD")
        @JvmField
        val EXTERN_KEYWORD: IElementType = CppElementType("EXTERN_KEYWORD")
        @JvmField
        val TEMPLATE_KEYWORD: IElementType = CppElementType("TEMPLATE_KEYWORD")
        @JvmField
        val TYPENAME_KEYWORD: IElementType = CppElementType("TYPENAME_KEYWORD")
        @JvmField
        val EXPLICIT_KEYWORD: IElementType = CppElementType("EXPLICIT_KEYWORD")
        @JvmField
        val MUTABLE_KEYWORD: IElementType = CppElementType("MUTABLE_KEYWORD")

        @JvmField
        val INLINE_KEYWORD: IElementType = CppElementType("INLINE_KEYWORD")
        @JvmField
        val PRE_KEYWORD: IElementType = CppElementType("PRE_KEYWORD")
        @JvmField
        val CASE_KEYWORD: IElementType = CppElementType("CASE_KEYWORD")
        @JvmField
        val CATCH_KEYWORD: IElementType = CppElementType("CATCH_KEYWORD")
        @JvmField
        val CHAR_KEYWORD: IElementType = CppElementType("CHAR_KEYWORD")
        @JvmField
        val OPERATOR_KEYWORD: IElementType = CppElementType("OPERATOR_KEYWORD")

        @JvmField
        val CONST_KEYWORD: IElementType = CppElementType("CONST_KEYWORD")
        @JvmField
        val TYPEDEF_KEYWORD: IElementType = CppElementType("TYPEDEF_KEYWORD")
        @JvmField
        val QUAL: IElementType = CppElementType("::")
        @JvmField
        val CONTINUE_KEYWORD: IElementType = CppElementType("CONTINUE_KEYWORD")
        @JvmField
        val DELETE_KEYWORD: IElementType = CppElementType("DELETE_KEYWORD")
        @JvmField
        val DEFAULT_KEYWORD: IElementType = CppElementType("DEFAULT_KEYWORD")
        @JvmField
        val DO_KEYWORD: IElementType = CppElementType("DO_KEYWORD")
        @JvmField
        val ELSE_KEYWORD: IElementType = CppElementType("ELSE_KEYWORD")

        @JvmField
        val FOR_KEYWORD: IElementType = CppElementType("FOR_KEYWORD")
        @JvmField
        val IF_KEYWORD: IElementType = CppElementType("IF_KEYWORD")

        @JvmField
        val NEW_KEYWORD: IElementType = CppElementType("NEW_KEYWORD")
        @JvmField
        val RETURN_KEYWORD: IElementType = CppElementType("RETURN_KEYWORD")
        @JvmField
        val SWITCH_KEYWORD: IElementType = CppElementType("SWITCH_KEYWORD")
        @JvmField
        val THIS_KEYWORD: IElementType = CppElementType("THIS_KEYWORD")
        @JvmField
        val THROW_KEYWORD: IElementType = CppElementType("THROW_KEYWORD")
        @JvmField
        val TRY_KEYWORD: IElementType = CppElementType("TRY_KEYWORD")

        @JvmField
        val VOID_KEYWORD: IElementType = CppElementType("VOID_KEYWORD")
        @JvmField
        val WHILE_KEYWORD: IElementType = CppElementType("WHILE_KEYWORD")
        @JvmField
        val CONSTEXPR_KEYWORD: IElementType = CppElementType("CONSTEXPR_KEYWORD")
        @JvmField
        val NULLPTR_KEYWORD: IElementType = CppElementType("NULLPTR_KEYWORD")
        @JvmField
        val STATIC_ASSERT_KEYWORD: IElementType = CppElementType("STATIC_ASSERT_KEYWORD")

        // Hardcoded literals
        @JvmField
        val TRUE_KEYWORD: IElementType = CppElementType("TRUE_KEYWORD")
        @JvmField
        val FALSE_KEYWORD: IElementType = CppElementType("FALSE_KEYWORD")

        // Literals
        @JvmField
        val NUMERIC_LITERAL: IElementType = CppElementType("NUMERIC_LITERAL")
        @JvmField
        val STRING_LITERAL: IElementType = CppElementType("STRING_LITERAL")
        @JvmField
        val SINGLE_QUOTE_STRING_LITERAL: IElementType = CppElementType("SINGLE_QUOTE_STRING_LITERAL")

        // Types
        @JvmField
        val INT_KEYWORD: IElementType = CppElementType("INT_KEYWORD")
        @JvmField
        val LONG_KEYWORD: IElementType = CppElementType("LONG_KEYWORD")
        @JvmField
        val UNSIGNED_KEYWORD: IElementType = CppElementType("UNSIGNED_KEYWORD")

        @JvmField
        val WCHART_KEYWORD: IElementType = CppElementType("WCHAR_T_KEYWORD")
        @JvmField
        val BOOL_KEYWORD: IElementType = CppElementType("BOOL_KEYWORD")
        @JvmField
        val CHAR16T_KEYWORD: IElementType = CppElementType("CHAR16T_KEYWORD")
        @JvmField
        val CHAR32T_KEYWORD: IElementType = CppElementType("CHAR32T_KEYWORD")
        @JvmField
        val COMPLEX_KEYWORD: IElementType = CppElementType("COMPLEX_KEYWORD")
        @JvmField
        val IMAGINARY_KEYWORD: IElementType = CppElementType("IMAGINARY_KEYWORD")
        @JvmField
        val ATOMIC_KEYWORD: IElementType = CppElementType("ATOMIC_KEYWORD")
        @JvmField
        val GENERIC_KEYWORD: IElementType = CppElementType("GENERIC_KEYWORD")

        // Spec
        @JvmField
        val ALIGNOF_KEYWORD: IElementType = CppElementType("ALIGNOF_KEYWORD")
        @JvmField
        val ALIGNAS_KEYWORD: IElementType = CppElementType("ALIGNAS_KEYWORD")
        @JvmField
        val THREAD_LOCAL_KEYWORD: IElementType = CppElementType("THREAD_LOCAL_KEYWORD")
        @JvmField
        val DECLTYPE_KEYWORD: IElementType = CppElementType("DECLTYPE_KEYWORD")
        @JvmField
        val NOEXCEPT_KEYWORD: IElementType = CppElementType("NOEXCEPT_KEYWORD")
        @JvmField
        val NORETURN_KEYWORD: IElementType = CppElementType("NORETURN_KEYWORD")
        @JvmField
        val RESTRICT_KEYWORD: IElementType = CppElementType("RESTRICT_KEYWORD")

        // Punctuators
        @JvmField
        val LBRACE: IElementType = CppElementType("LBRACE")// {
        @JvmField
        val RBRACE: IElementType = CppElementType("RBRACE")// }
        @JvmField
        val LPAR: IElementType = CppElementType("LPAR")// (
        @JvmField
        val RPAR: IElementType = CppElementType("RPAR")// )
        @JvmField
        val LBRACKET: IElementType = CppElementType("LBRACKET")// [
        @JvmField
        val DOUBLE_BRACKET: IElementType = CppElementType("LBRACKET")// [
        @JvmField
        val RBRACKET: IElementType = CppElementType("RBRACKET")// ]
        @JvmField
        val DOT: IElementType = CppElementType("DOT")// .
        @JvmField
        val MEMBER_DOT: IElementType = CppElementType("MEMBER_DOT")// .*
        @JvmField
        val SEMICOLON: IElementType = CppElementType("SEMICOLON")// ;
        @JvmField
        val COMMA: IElementType = CppElementType("COMMA")// ,

        @JvmField
        val LT: IElementType = CppElementType("LT")// <
        @JvmField
        val GT: IElementType = CppElementType("GT")// >
        @JvmField
        val LE: IElementType = CppElementType("LE")// <=
        @JvmField
        val GE: IElementType = CppElementType("GE")// >=
        @JvmField
        val EQEQ: IElementType = CppElementType("EQEQ")// ==
        @JvmField
        val NE: IElementType = CppElementType("NE")// !=

        @JvmField
        val PLUS: IElementType = CppElementType("PLUS")// +
        @JvmField
        val MINUS: IElementType = CppElementType("MINUS")// -
        @JvmField
        val MULT: IElementType = CppElementType("MULT")// *
        @JvmField
        val PERC: IElementType = CppElementType("PERC")// %
        @JvmField
        val PLUSPLUS: IElementType = CppElementType("PLUSPLUS")// ++
        @JvmField
        val MINUSMINUS: IElementType = CppElementType("MINUSMINUS")// --
        @JvmField
        val LTLT: IElementType = CppElementType("LTLT")// <<
        @JvmField
        val GTGT: IElementType = CppElementType("GTGT")// >>

        @JvmField
        val AND: IElementType = CppElementType("AND")// &
        @JvmField
        val OR: IElementType = CppElementType("OR")// |
        @JvmField
        val XOR: IElementType = CppElementType("XOR")// ^
        @JvmField
        val EXCL: IElementType = CppElementType("EXCL")// !
        @JvmField
        val TILDE: IElementType = CppElementType("TILDE")// ~
        @JvmField
        val ANDAND: IElementType = CppElementType("ANDAND")// &&
        @JvmField
        val OROR: IElementType = CppElementType("OROR")// ||
        @JvmField
        val QUEST: IElementType = CppElementType("QUEST")// ?
        @JvmField
        val COLON: IElementType = CppElementType("COLON")// :
        @JvmField
        val EQ: IElementType = CppElementType("EQ")// =
        @JvmField
        val PLUSEQ: IElementType = CppElementType("PLUSEQ")// +=
        @JvmField
        val MINUSEQ: IElementType = CppElementType("MINUSEQ")// -=
        @JvmField
        val MULTEQ: IElementType = CppElementType("MULTEQ")// *=
        @JvmField
        val PERCEQ: IElementType = CppElementType("PERCEQ")// %=
        @JvmField
        val LTLTEQ: IElementType = CppElementType("LTLTEQ")// <<=
        @JvmField
        val GTGTEQ: IElementType = CppElementType("GTGTEQ")// >>=
        @JvmField
        val ANDEQ: IElementType = CppElementType("ANDEQ")// &=
        @JvmField
        val OREQ: IElementType = CppElementType("OREQ")// |=
        @JvmField
        val XOREQ: IElementType = CppElementType("XOREQ")// ^=
        @JvmField
        val DIV: IElementType = CppElementType("DIV") // /
        @JvmField
        val DIVEQ: IElementType = CppElementType("DIVEQ") // /=

        @JvmField
        val ARROW: IElementType = CppElementType("ARROW") // ->
        @JvmField
        val MEMBER_ARROW: IElementType = CppElementType("MEMBER_ARROW") // ->*
        @JvmField
        val PUBLIC_KEYWORD: IElementType = CppElementType("PUBLIC_KEYWORD") // ->
        @JvmField
        val PROTECTED_KEYWORD: IElementType = CppElementType("PROTECTED_KEYWORD") // ->
        @JvmField
        val PRIVATE_KEYWORD: IElementType = CppElementType("PRIVATE_KEYWORD") // ->
        @JvmField
        val FRIEND_KEYWORD: IElementType = CppElementType("FRIEND_KEYWORD") // ->
        @JvmField
        val STRUCT_KEYWORD: IElementType = CppElementType("STRUCT_KEYWORD") // ->
        @JvmField
        val CLASS_KEYWORD: IElementType = CppElementType("CLASS_KEYWORD") // ->
        @JvmField
        val ENUM_KEYWORD: IElementType = CppElementType("ENUM_KEYWORD") // ->
        @JvmField
        val USING_KEYWORD: IElementType = CppElementType("USING_KEYWORD") // ->
        @JvmField
        val NAMESPACE_KEYWORD: IElementType = CppElementType("NAMESPACE_KEYWORD") // ->
        @JvmField
        val SIZEOF_KEYWORD: IElementType = CppElementType("NAMESPACE_KEYWORD") // ->
        @JvmField
        val EXPORT_KEYWORD: IElementType = CppElementType("EXPORT_KEYWORD") // ->

        @JvmField
        val REINTERPRET_CAST_KEYWORD: IElementType = CppElementType("reinterpret_cast_keyword")
        @JvmField
        val CONST_CAST_KEYWORD: IElementType = CppElementType("const_cast_keyword")
        @JvmField
        val STATIC_CAST_KEYWORD: IElementType = CppElementType("static_cast_keyword")
        @JvmField
        val DYNAMIC_CAST_KEYWORD: IElementType = CppElementType("dynamic_cast_keyword")

        @JvmField
        val ASM_KEYWORD: IElementType = CppElementType("ASM_KEYWORD")
        @JvmField
        val TYPEID_KEYWORD: IElementType = CppElementType("TYPEID_KEYWORD")

        @JvmField
        val REGISTER_KEYWORD: IElementType = CppElementType("register_keyword")
        @JvmField
        val AUTO_KEYWORD: IElementType = CppElementType("auto_keyword")
        @JvmField
        val DOUBLE_KEYWORD: IElementType = CppElementType("double_keyword")
        @JvmField
        val SHORT_KEYWORD: IElementType = CppElementType("short_keyword")
        @JvmField
        val FLOAT_KEYWORD: IElementType = CppElementType("float_keyword")
        @JvmField
        val VOLATILE_KEYWORD: IElementType = CppElementType("volatile_keyword")
        @JvmField
        val GOTO_KEYWORD: IElementType = CppElementType("goto_keyword")
        @JvmField
        val SIGNED_KEYWORD: IElementType = CppElementType("signed_keyword")
        @JvmField
        val UNION_KEYWORD: IElementType = CppElementType("union_keyword")
        @JvmField
        val ESCAPING_SLASH: IElementType = CppElementType("escaping_slash")

        @JvmField
        val C_KEYWORDS = TokenSet.create(
                BREAK_KEYWORD, SIZEOF_KEYWORD, CASE_KEYWORD, CONST_KEYWORD, CONTINUE_KEYWORD, DEFAULT_KEYWORD,
                DO_KEYWORD, ELSE_KEYWORD, FOR_KEYWORD, IF_KEYWORD, RETURN_KEYWORD, SWITCH_KEYWORD,
                VOID_KEYWORD, WHILE_KEYWORD, STATIC_KEYWORD, EXTERN_KEYWORD,
                CHAR_KEYWORD, INT_KEYWORD, LONG_KEYWORD, UNSIGNED_KEYWORD, UNION_KEYWORD,
                STRUCT_KEYWORD, ENUM_KEYWORD, TYPEDEF_KEYWORD, REGISTER_KEYWORD, AUTO_KEYWORD,
                DOUBLE_KEYWORD, FLOAT_KEYWORD, SHORT_KEYWORD, SIGNED_KEYWORD, VOLATILE_KEYWORD, GOTO_KEYWORD, RESTRICT_KEYWORD,
                INLINE_KEYWORD, ALIGNAS_KEYWORD, ALIGNOF_KEYWORD, BOOL_KEYWORD, COMPLEX_KEYWORD, IMAGINARY_KEYWORD, ATOMIC_KEYWORD,
                GENERIC_KEYWORD, NORETURN_KEYWORD, STATIC_ASSERT_KEYWORD, THREAD_LOCAL_KEYWORD
        )

        @JvmField
        val CPP_KEYWORDS = TokenSet.create(
                CATCH_KEYWORD, DELETE_KEYWORD, NEW_KEYWORD, THIS_KEYWORD,
                THROW_KEYWORD, TRY_KEYWORD, TRUE_KEYWORD, FALSE_KEYWORD,
                VIRTUAL_KEYWORD, TEMPLATE_KEYWORD, TYPENAME_KEYWORD, EXPLICIT_KEYWORD, MUTABLE_KEYWORD,
                INLINE_KEYWORD, EXPORT_KEYWORD, WCHART_KEYWORD,
                PUBLIC_KEYWORD, PROTECTED_KEYWORD, PRIVATE_KEYWORD, FRIEND_KEYWORD, CLASS_KEYWORD,
                USING_KEYWORD, NAMESPACE_KEYWORD, BOOL_KEYWORD, OPERATOR_KEYWORD, CONST_CAST_KEYWORD, STATIC_CAST_KEYWORD,
                DYNAMIC_CAST_KEYWORD, REINTERPRET_CAST_KEYWORD, ASM_KEYWORD, TYPEID_KEYWORD, ALIGNOF_KEYWORD, ALIGNAS_KEYWORD,
                CHAR16T_KEYWORD, CHAR32T_KEYWORD, CONSTEXPR_KEYWORD, DECLTYPE_KEYWORD, NOEXCEPT_KEYWORD, STATIC_ASSERT_KEYWORD,
                THREAD_LOCAL_KEYWORD, NULLPTR_KEYWORD
        )
        @JvmField
        val KEYWORDS = TokenSet.orSet(C_KEYWORDS, CPP_KEYWORDS)

        @JvmField
        val WHITE_SPACES = TokenSet.create(WHITE_SPACE)
        @JvmField
        val COMMENTS = TokenSet.create(C_STYLE_COMMENT, END_OF_LINE_COMMENT)
        @JvmField
        val LITERALS = TokenSet.create(STRING_LITERAL, SINGLE_QUOTE_STRING_LITERAL)

        @JvmField
        val OPERATIONS = TokenSet.create(
                LT, GT, LE, GE, EQEQ, NE, PLUS, MINUS, MULT, PERC, PLUSPLUS, MINUSMINUS, LTLT, GTGT, AND, OR,
                XOR, EXCL, TILDE, ANDAND, OROR, QUEST, COLON, EQ, PLUSEQ, MINUSEQ, MULTEQ, PERCEQ, LTLTEQ, GTGTEQ, ANDEQ,
                OREQ, XOREQ, DIV, DIVEQ, COMMA, QUAL
        )

        @JvmField
        val OVERRIDABLE_OPERATIONS = TokenSet.create(
                LT, GT, LE, GE, EQEQ, NE, PLUS, MINUS, MULT, PERC, PLUSPLUS, MINUSMINUS, LTLT, GTGT, AND, OR,
                XOR, EXCL, ANDAND, OROR, EQ, PLUSEQ, MINUSEQ, MULTEQ, PERCEQ, LTLTEQ, GTGTEQ, ANDEQ,
                OREQ, XOREQ, DIV, DIVEQ, MEMBER_ARROW, MEMBER_DOT, LBRACKET, RBRACKET, LPAR, RPAR, ARROW, OPERATOR_KEYWORD,
                TILDE
        )
    }
}