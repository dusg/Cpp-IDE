package cppide

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class CppColorSettingsPage :ColorSettingsPage {
    companion object {
        private val ourTags : MutableMap<String, TextAttributesKey> = mutableMapOf()
        private val ATTRS: Array<AttributesDescriptor> = arrayOf(
                AttributesDescriptor(CppBundle.message("cpp.keyword"), CppSyntaxHighlighter.CPP_KEYWORD),
                AttributesDescriptor(CppBundle.message("c.keyword"), CppSyntaxHighlighter.C_KEYWORD),
                AttributesDescriptor(CppBundle.message("pre.keyword"), CppSyntaxHighlighter.PRE_KEYWORD),
                AttributesDescriptor(CppBundle.message("cpp.blockcomment"), CppSyntaxHighlighter.CPP_BLOCK_COMMENT),
                AttributesDescriptor(CppBundle.message("cpp.linecomment"), CppSyntaxHighlighter.CPP_LINE_COMMENT),
                AttributesDescriptor(CppBundle.message("cpp.string"), CppSyntaxHighlighter.CPP_STRING),
                AttributesDescriptor(CppBundle.message("cpp.number"), CppSyntaxHighlighter.CPP_NUMBER),
                AttributesDescriptor(CppBundle.message("cpp.operations"), CppSyntaxHighlighter.CPP_OPERATION_SIGN),
                AttributesDescriptor(CppBundle.message("cpp.parens"), CppSyntaxHighlighter.CPP_PARENTHS),
                AttributesDescriptor(CppBundle.message("cpp.braces"), CppSyntaxHighlighter.CPP_BRACES),
                AttributesDescriptor(CppBundle.message("cpp.brackets"), CppSyntaxHighlighter.CPP_BRACKETS),
                AttributesDescriptor(CppBundle.message("cpp.dot"), CppSyntaxHighlighter.CPP_DOT),
                AttributesDescriptor(CppBundle.message("cpp.comma"), CppSyntaxHighlighter.CPP_COMMA),
                AttributesDescriptor(CppBundle.message("cpp.semicolon"), CppSyntaxHighlighter.CPP_SEMICOLON),

                AttributesDescriptor(CppBundle.message("cpp.type"), CppSyntaxHighlighter.CPP_TYPE),
                AttributesDescriptor(CppBundle.message("cpp.macros"), CppSyntaxHighlighter.CPP_MACROS),
                AttributesDescriptor(CppBundle.message("cpp.namespace"), CppSyntaxHighlighter.CPP_NAMESPACE),

                AttributesDescriptor(CppBundle.message("cpp.functions"), CppSyntaxHighlighter.CPP_FUNCTION),
                AttributesDescriptor(CppBundle.message("cpp.staticfunctions"), CppSyntaxHighlighter.CPP_STATIC_FUNCTION),
                AttributesDescriptor(CppBundle.message("cpp.static"), CppSyntaxHighlighter.CPP_STATIC),
                AttributesDescriptor(CppBundle.message("cpp.field"), CppSyntaxHighlighter.CPP_FIELD),
                AttributesDescriptor(CppBundle.message("cpp.method"), CppSyntaxHighlighter.CPP_METHOD),
                AttributesDescriptor(CppBundle.message("cpp.parameter"), CppSyntaxHighlighter.CPP_PARAMETER),

                AttributesDescriptor(CppBundle.message("cpp.constant"), CppSyntaxHighlighter.CPP_CONSTANT),
                AttributesDescriptor(CppBundle.message("cpp.pp_arg"), CppSyntaxHighlighter.CPP_PP_ARG),
                AttributesDescriptor(CppBundle.message("cpp.pp_skipped"), CppSyntaxHighlighter.CPP_PP_SKIPPED),
                AttributesDescriptor(CppBundle.message("cpp.label"), CppSyntaxHighlighter.CPP_LABEL),
                AttributesDescriptor(CppBundle.message("cpp.unused"), CppSyntaxHighlighter.CPP_UNUSED)
                )

        init {
            ourTags["namespace"] = CppSyntaxHighlighter.CPP_NAMESPACE
            ourTags["type"] = CppSyntaxHighlighter.CPP_TYPE
            ourTags["macros"] = CppSyntaxHighlighter.CPP_MACROS
            ourTags["function"] = CppSyntaxHighlighter.CPP_FUNCTION
            ourTags["static_method"] = CppSyntaxHighlighter.CPP_STATIC_FUNCTION
            ourTags["static"] = CppSyntaxHighlighter.CPP_STATIC
            ourTags["field"] = CppSyntaxHighlighter.CPP_FIELD
            ourTags["method"] = CppSyntaxHighlighter.CPP_METHOD
            ourTags["parameter"] = CppSyntaxHighlighter.CPP_PARAMETER
            ourTags["constant"] = CppSyntaxHighlighter.CPP_CONSTANT
            ourTags["label"] = CppSyntaxHighlighter.CPP_LABEL
            ourTags["pp_skipped"] = CppSyntaxHighlighter.CPP_PP_SKIPPED
            ourTags["macros_param"] = CppSyntaxHighlighter.CPP_PP_ARG
            ourTags["unused"] = CppSyntaxHighlighter.CPP_UNUSED

        }
    }
    /**
     * Returns the syntax highlighter which is used to highlight the text shown in the preview
     * pane of the page.
     *
     * @return the syntax highlighter instance.
     */
    override fun getHighlighter(): SyntaxHighlighter {
        return CppSyntaxHighlighter()
    }

    /**
     * Returns the mapping from special tag names surrounding the regions to be highlighted
     * in the preview text (see [.getDemoText]) to text attribute keys used to
     * highlight the regions.
     *
     * @return the mapping from tag names to text attribute keys, or null if the demo text
     * does not contain any additional highlighting tags.
     */
    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey> {
        return ourTags
    }

    /**
     * Returns the icon for the page, shown in the dialog tab.
     *
     * @return the icon for the page, or null if the page does not have a custom icon.
     */
    override fun getIcon(): Icon? {
        return CppToolIcons.CPP_FILE
    }

    /**
     * Returns the list of descriptors specifying the [TextAttributesKey] instances
     * for which colors are specified in the page. For such attribute keys, the user can choose
     * all highlighting attributes (font type, background color, foreground color, error stripe color and
     * effects).
     *
     * @return the list of attribute descriptors.
     */
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return ATTRS
    }

    /**
     * Returns the list of descriptors specifying the [com.intellij.openapi.editor.colors.ColorKey]
     * instances for which colors are specified in the page. For such color keys, the user can
     * choose only the background or foreground color.
     *
     * @return the list of color descriptors.
     */
    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return emptyArray()
    }

    /**
     * Returns the title of the page, shown as text in the dialog tab.
     *
     * @return the title of the custom page.
     */
    override fun getDisplayName(): String {
        return "C/C++"
    }

    /**
     * Returns the text shown in the preview pane. If some elements need to be highlighted in
     * the preview text which are not highlighted by the syntax highlighter, they need to be
     * surrounded by XML-like tags, for example: `<class>MyClass</class>`.
     * The mapping between the names of the tags and the text attribute keys used for highlighting
     * is defined by the [.getAdditionalHighlightingTagToDescriptorMap] method.
     *
     * @return the text to show in the preview pane.
     */
    override fun getDemoText(): String {
        return """
            /*
 * Block comment
 */
#include <vector>

using namespace std;  // line comment
namespace foo {

  typedef struct Struct {
    int field;
  } Typedef;
  enum Enum {Foo = 1, Bar = 2};

  Typedef *globalVar;
  extern Typedef *externVar;

  template<typename T, int N>
  class Class {
    T n;
  public:
    /**
     * Semantic highlighting:
     * Generated spectrum to pick colors for local variables and parameters:
     *  Color#1 SC1.1 SC1.2 SC1.3 SC1.4 Color#2 SC2.1 SC2.2 SC2.3 SC2.4 Color#3
     *  Color#3 SC3.1 SC3.2 SC3.3 SC3.4 Color#4 SC4.1 SC4.2 SC4.3 SC4.4 Color#5
     */
    void function(int param1, int param2, int param3) {
      int localVar1, localVar2, localVar3;
      int *localVar = new int[1];
      this->n = N;
      localVar1 = param1 + param2 + localVar3;

    label:
      printf("Formatted string %d\n\g", localVar[0]);
      printf(R"**(Formatted raw-string %d\n)**", 1);
      std::cout << (1 << 2) << std::endl;

    #define FOO(A) A
    #ifdef DEBUG
      printf("debug");
    #endif
    }
  };
}
        """.trimIndent()
    }
}