package lsp

typealias DocumentUri = String

val EOL = arrayOf("\n", "\r\n", "\r")

interface Position {
    /**
     * Line position in a document (zero-based).
     */
    var line: Number

    /**
     * Character offset on a line in a document (zero-based). Assuming that the line is
     * represented as a string, the `character` value represents the gap between the
     * `character` and `character + 1`.
     *
     * If the character value is greater than the line length it defaults back to the
     * line length.
     */
    var character: Number
}

interface Range {
    /**
     * The range's start position.
     */
    var start: Position

    /**
     * The range's end position.
     */
    var end: Position
}

interface Location {
    var uri: DocumentUri
    var range: Range
}

interface LocationLink {

    /**
     * Span of the origin of this link.
     *
     * Used as the underlined span for mouse interaction. Defaults to the word range at
     * the mouse position.
     */
    var originSelectionRange: Range?

    /**
     * The target resource identifier of this link.
     */
    var targetUri: String

    /**
     * The full target range of this link. If the target for example is a symbol then target range is the
     * range enclosing this symbol not including leading/trailing whitespace but everything else
     * like comments. This information is typically used to highlight the range in the editor.
     */
    var targetRange: Range

    /**
     * The range that should be selected and revealed when this link is being followed, e.g the name of a function.
     * Must be contained by the the `targetRange`. See also `DocumentSymbol#range`
     */
    var targetSelectionRange: Range
}

/**
 * Represents a related message and source code location for a diagnostic. This should be
 * used to point to code locations that cause or related to a diagnostics, e.g when duplicating
 * a symbol in a scope.
 */
interface DiagnosticRelatedInformation {
    /**
     * The location of this related diagnostic information.
     */
    var location: Location

    /**
     * The message of this related diagnostic information.
     */
    var message: String
}

interface Diagnostic {
    /**
     * The range at which the message applies.
     */
    var range: Range

    /**
     * The diagnostic's severity. Can be omitted. If omitted it is up to the
     * client to interpret diagnostics as error, warning, info or hint.
     */
    var severity: Number?

    /**
     * The diagnostic's code, which might appear in the user interface.
     */
    var code: String

    /**
     * A human-readable string describing the source of this
     * diagnostic, e.g. 'typescript' or 'super lint'.
     */
    var source: String?

    /**
     * The diagnostic's message.
     */
    var message: String

    /**
     * An array of related diagnostic information, e.g. when symbol-names within
     * a scope collide all definitions can be marked via this property.
     */
    var relatedInformation: Array<DiagnosticRelatedInformation>
}

enum class DiagnosticSeverity(val kind: Number, val desc: String) {
    /**
     * Reports an error.
     */
    Error(1, "Error"),
    /**
     * Reports a warning.
     */
    Warning(2, "Warning"),
    /**
     * Reports an information.
     */
    Information(3, "Information"),
    /**
     * Reports a hint.
     */
    Hint(4, "Hint");

    fun toNumber(): Number {
        return kind
    }

    override fun toString(): String {
        return desc
    }

}