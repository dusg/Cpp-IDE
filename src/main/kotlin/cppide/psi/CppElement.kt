package cppide.psi

import com.intellij.lang.ASTNode
import com.intellij.lang.Language
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.PsiElementBase

class CppElement : PsiElementBase {
    constructor()
    /**
     * Returns the text of the PSI element.
     *
     * @return the element text.
     */
    override fun getText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the text offset of the PSI element relative to its parent.
     *
     * @return the relative offset.
     */
    override fun getStartOffsetInParent(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the language of the PSI element.
     *
     * @return the language instance.
     */
    override fun getLanguage(): Language {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the text range in the document occupied by the PSI element.
     *
     * @return the text range.
     */
    override fun getTextRange(): TextRange {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Finds a leaf PSI element at the specified offset from the start of the text range of this node.
     *
     * @param offset the relative offset for which the PSI element is requested.
     * @return the element at the offset, or null if none is found.
     */
    override fun findElementAt(offset: Int): PsiElement? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the length of text of the PSI element.
     *
     * @return the text length.
     */
    override fun getTextLength(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the offset in the file to which the caret should be placed
     * when performing the navigation to the element. (For classes implementing
     * [PsiNamedElement], this should return the offset in the file of the
     * name identifier.)
     *
     * @return the offset of the PSI element.
     */
    override fun getTextOffset(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the text of the PSI element as a character array.
     *
     * @return the element text as a character array.
     */
    override fun textToCharArray(): CharArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the AST node corresponding to the element.
     *
     * @return the AST node instance.
     */
    override fun getNode(): ASTNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the parent of the PSI element.
     *
     * @return the parent of the element, or null if the element has no parent.
     */
    override fun getParent(): PsiElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the array of children for the PSI element.
     * Important: In some implementations children are only composite elements, i.e. not a leaf elements
     *
     * @return the array of child elements.
     */
    override fun getChildren(): Array<PsiElement> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}