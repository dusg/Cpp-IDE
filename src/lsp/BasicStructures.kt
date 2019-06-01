package lsp

// URI
// URI’s are transferred as strings. The URI’s format is defined in http://tools.ietf.org/html/rfc3986
//    foo://example.com:8042/over/there?name=ferret#nose
//    \_/   \______________/\_________/ \_________/ \__/
//     |           |            |            |        |
//  scheme     authority       path        query   fragment
//     |   _____________________|__
//    / \ /                        \
//    urn:example:animal:ferret:nose
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
internal interface Command {
    /**
     * Title of the command, like `save`.
     */
    var title: String
    /**
     * The identifier of the actual command handler.
     */
    var command: String
    /**
     * Arguments that the command handler should be
     * invoked with.
     */
    var arguments: Array<Any>?
}

interface TextEdit {
    /**
     * The range of the text document to be manipulated. To insert
     * text into a document create a range where start === end.
     */
    var range: Range

    /**
     * The string to be inserted. For delete operations use an
     * empty string.
     */
    var newText: String
}

interface FileOperation {
}

interface TextDocumentEdit : FileOperation {
    /**
     * The text document to change.
     */
    var textDocument: VersionedTextDocumentIdentifier

    /**
     * The edits to be applied.
     */
    var edits: Array<TextEdit>
}

/***************************************************************************
 * File Resource changes
 * ********************************************************************
 ***************************************************************************/
/**
 * Options to create a file.
 */
interface CreateFileOptions :FileOperation{
    /**
     * Overwrite existing file. Overwrite wins over `ignoreIfExists`
     */
    var overwrite: Boolean?
    /**
     * Ignore if exists.
     */
    var ignoreIfExists: Boolean?
}
/**
 * Create file operation
 */
interface CreateFile :FileOperation{
    /**
     * A create
     */
    val kind: String
        get() = "create"
    /**
     * The resource to create.
     */
    var uri: String;
    /**
     * Additional options
     */
    var options: CreateFileOptions?
}

/**
 * Rename file options
 */
interface RenameFileOptions :FileOperation{
    /**
     * Overwrite target if existing. Overwrite wins over `ignoreIfExists`
     */
    var overwrite: Boolean?
    /**
     * Ignores if target exists.
     */
    var ignoreIfExists: Boolean?
}

/**
 * Rename file operation
 */
interface RenameFile :FileOperation{
    /**
     * A rename
     */
    val kind: String
        get() = "rename"
    /**
     * The old (existing) location.
     */
    var oldUri: String;
    /**
     * The new location.
     */
    var newUri: String;
    /**
     * Rename options.
     */
    var options: RenameFileOptions?
}

/**
 * Delete file options
 */
interface DeleteFileOptions :FileOperation{
    /**
     * Delete the content recursively if a folder is denoted.
     */
    var recursive: Boolean?
    /**
     * Ignore the operation if the file doesn't exist.
     */
    var ignoreIfNotExists: Boolean?
}

/**
 * Delete file operation
 */
interface DeleteFile :FileOperation{
    /**
     * A delete
     */
    val kind: String
        get() = "delete"
    /**
     * The file to delete.
     */
    var uri: String;
    /**
     * Delete options.
     */
    var options: DeleteFileOptions?
}

interface WorkspaceEdit {
    /**
     * Holds changes to existing resources.
     */
    interface Changes {
        var uri : DocumentUri
        var edits : Array<TextEdit>
    }
    var changes: Changes?

    /**
     * Depending on the client capability `workspace.workspaceEdit.resourceOperations` document changes
     * are either an array of `TextDocumentEdit`s to express changes to n different text documents
     * where each text document edit addresses a specific version of a text document. Or it can contain
     * above `TextDocumentEdit`s mixed with create, rename and delete file / folder operations.
     *
     * Whether a client supports versioned document edits is expressed via
     * `workspace.workspaceEdit.documentChanges` client capability.
     *
     * If a client neither supports `documentChanges` nor `workspace.workspaceEdit.resourceOperations` then
     * only plain `TextEdit`s using the `changes` property are supported.
     */
    var documentChanges: Array<FileOperation>?
}

interface TextDocumentIdentifier {
    /**
     * The text document's URI.
     */
    var uri: DocumentUri;
}
interface VersionedTextDocumentIdentifier : TextDocumentIdentifier {
    /**
     * The version number of this document. If a versioned text document identifier
     * is sent from the server to the client and the file is not open in the editor
     * (the server has not received an open notification before) the server can send
     * `null` to indicate that the version is known and the content on disk is the
     * truth (as speced with document content ownership).
     *
     * The version number of a document will increase after each change, including
     * undo/redo. The number doesn't need to be consecutive.
     */
    var version: Number?
}
interface TextDocumentItem {
    /**
     * The text document's URI.
     */
    var uri: DocumentUri;

    /**
     * The text document's language identifier.
     */
    var languageId: String;

    /**
     * The version number of this document (it will increase after each
     * change, including undo/redo).
     */
    var version: Number;

    /**
     * The content of the opened text document.
     */
    var text: String;
}

interface TextDocumentPositionParams {
    /**
     * The text document.
     */
    var textDocument: TextDocumentIdentifier;

    /**
     * The position inside the text document.
     */
    var position: Position;
}

interface DocumentFilter {
    /**
     * A language id, like `typescript`.
     */
    var language: String?

    /**
     * A Uri [scheme](#Uri.scheme), like `file` or `untitled`.
     */
    var scheme: String?

/**
 * A glob pattern, like `*.{ts,js}`.
 *
 * Glob patterns can have the following syntax:
 * - `*` to match one or more characters in a path segment
 * - `?` to match on one character in a path segment
 * - `**` to match any number of path segments, including none
 * - `{}` to group conditions (e.g. `**​/ *.{ts,js}` matches all TypeScript and JavaScript files)
 * - `[]` to declare a range of characters to match in a path segment (e.g., `example.[0-9]` to match on `example.0`, `example.1`, …)
 * - `[!...]` to negate a range of characters to match in a path segment (e.g., `example.[!0-9]` to match on `example.a`, `example.b`, but not `example.0`)
*/
var pattern: String?
}

typealias DocumentSelector = Array<DocumentFilter>

enum class MarkupKind(val kind: String) {
    PlainText("plaintext"),
    Markdown("markdown");

    override fun toString(): String{
        return kind
    }
}

interface MarkupContent {
    /**
     * The type of the Markup
     */
    var kind: MarkupKind;

    /**
     * The content itself
     */
    var value: String;
}