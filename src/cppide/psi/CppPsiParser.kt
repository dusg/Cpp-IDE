package cppide.psi

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.impl.source.tree.FileElement
import com.intellij.psi.tree.IElementType
import java.util.*

internal class BlockInfo(val block: PsiBuilder.Marker, val blockType: BlockType) {
    var parensList: LinkedList<PsiBuilder.Marker>? = null

    internal enum class BlockType {
        BLOCK, STATEMENT, FUNC
    }

    fun done() {
        if (parensList != null) {
            while (parensList!!.size > 0) doneParens()
        }
        block.done(
                if (blockType == BlockType.BLOCK)
                    CppTokenTypes.BLOCK
                else if (blockType == BlockType.FUNC)
                    CppTokenTypes.BLOCK
                else
                    CppTokenTypes.STATEMENT)
    }

    fun addParens(marker: PsiBuilder.Marker) {
        if (parensList == null) parensList = LinkedList()
        parensList!!.add(marker)
    }

    fun doneParens() {
        if (parensList != null && parensList!!.size > 0) parensList!!.removeLast().done(CppTokenTypes.PARENS)
    }

    fun hasParens(): Boolean {
        return parensList != null && parensList!!.size > 0
    }
}

private fun requiresComposite(tokenType: IElementType): Boolean {
    return tokenType === CppTokenTypes.IDENTIFIER ||
            tokenType === CppTokenTypes.STRING_LITERAL ||
            CppTokenTypes.OVERRIDABLE_OPERATIONS.contains(tokenType)
}

class CppPsiParser : PsiParser{
    override fun parse(root: IElementType, psiBuilder: PsiBuilder): ASTNode {
        val rootMarker = psiBuilder.mark()
        val blocks = LinkedList<BlockInfo>()
        var openBlock = true

        while (psiBuilder.getTokenType() != null) {
            if (openBlock) {
                val type = psiBuilder.getTokenType()
                if (type !== CppTokenTypes.RBRACE && type !== CppTokenTypes.SEMICOLON && type !== CppTokenTypes.COMMA &&
                        type !== CppTokenTypes.LBRACE && (type !== CppTokenTypes.PRE_KEYWORD || psiBuilder.getTokenText() != "\\")) {
                    openBlock = false
                    blocks.add(BlockInfo(psiBuilder.mark(), if (blocks.size > 0) BlockInfo.BlockType.STATEMENT else BlockInfo.BlockType.FUNC))
                }
            }

            val tokenType = psiBuilder.getTokenType()

            if (tokenType === CppTokenTypes.LBRACE) {
                blocks.add(BlockInfo(psiBuilder.mark(), BlockInfo.BlockType.BLOCK))
            } else if (tokenType === CppTokenTypes.LPAR || tokenType === CppTokenTypes.LBRACKET) {
                if (blocks.size > 0) blocks.getLast().addParens(psiBuilder.mark())
            }

            val tokenMarker = if (requiresComposite(tokenType!!)) psiBuilder.mark() else null
            psiBuilder.advanceLexer()
            if (tokenMarker != null) tokenMarker!!.done(tokenType!!)

            if (tokenType === CppTokenTypes.LBRACE) {
                openBlock = true
            } else if (tokenType === CppTokenTypes.RPAR || tokenType === CppTokenTypes.RBRACKET) {
                if (blocks.size > 0) blocks.getLast().doneParens()
            } else if (tokenType === CppTokenTypes.RBRACE && blocks.size > 0) {
                blocks.removeLast().done()
                if (blocks.size > 0) blocks.removeLast().done()
                openBlock = true
            } else if (tokenType === CppTokenTypes.SEMICOLON && blocks.size > 0 && !blocks.getLast().hasParens() && !openBlock) {
                blocks.removeLast().done()
                openBlock = true
            }
        }

        while (blocks.size > 0) {
            blocks.removeLast().done()
        }
        rootMarker.done(root)
        return psiBuilder.getTreeBuilt()    }
}

class CppFileNode(text: CharSequence) : FileElement(CppFileElementType(), text) {

}