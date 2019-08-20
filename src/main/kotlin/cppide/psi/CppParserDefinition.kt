package cppide.psi

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class CppParserDefinition : ParserDefinition{
    /**
     * Returns the parser for parsing files in the specified project.
     *
     * @param project the project to which the parser is connected.
     * @return the parser instance.
     */
    override fun createParser(project: Project?): PsiParser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Creates a PSI element for the specified virtual file.
     *
     * @param viewProvider virtual file.
     * @return the PSI file element.
     */
    override fun createFile(viewProvider: FileViewProvider?): PsiFile {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the set of element types which are treated as string literals. "Search in strings"
     * option in refactorings is applied to the contents of such tokens.
     *
     * @return the set of string literal element types.
     */
    override fun getStringLiteralElements(): TokenSet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the element type of the node describing a file in the specified language.
     *
     * @return the file node element type.
     */
    override fun getFileNodeType(): IFileElementType {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the lexer for lexing files in the specified project. This lexer does not need to support incremental relexing - it is always
     * called for the entire file.
     *
     * @param project the project to which the lexer is connected.
     * @return the lexer instance.
     */
    override fun createLexer(project: Project?): Lexer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Creates a PSI element for the specified AST node. The AST tree is a simple, semantic-free
     * tree of AST nodes which is built during the PsiBuilder parsing pass. The PSI tree is built
     * over the AST tree and includes elements of different types for different language constructs.
     *
     * !!!WARNING!!! PSI element types should be unambiguously determined by AST node element types.
     * You can not produce different PSI elements from AST nodes of the same types (e.g. based on AST node content).
     * Typically, your code should be as simple as that:
     * <pre>`if (node.getElementType == MY_ELEMENT_TYPE) {
     * return new MyPsiElement(node);
     * }
    `</pre> *
     *
     * @param node the node for which the PSI element should be returned.
     * @return the PSI element matching the element type of the AST node.
     */
    override fun createElement(node: ASTNode?): PsiElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the set of token types which are treated as comments by the PSI builder.
     * Tokens of those types are automatically skipped by PsiBuilder. Also, To Do patterns
     * are searched in the text of tokens of those types.
     * This token set shouldn't contain types of non-leaf comment inner elements.
     *
     * @return the set of comment token types.
     */
    override fun getCommentTokens(): TokenSet {
        return CppTokenTypes.COMMENTS
    }
}