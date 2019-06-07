package lsp.structs

object ResourceOperationKind {

    /**
     * Supports creating new files and folders.
     */
    val Create = "create"

    /**
     * Supports renaming existing files and folders.
     */
    val Rename = "rename"

    /**
     * Supports deleting existing files and folders.
     */
    val Delete = "delete"
}

object FailureHandlingKind {

    /**
     * Applying the workspace change is simply aborted if one of the changes
     * provided fails. All operations executed before the failing operation stay
     * executed.
     */
    val Abort = "abort"

    /**
     * All operations are executed transactional. That means they either all succeed
     * or no changes at all are applied to the workspace.
     */
    val Transactional = "transactional"

    /**
     * If the workspace edit contains only textual file changes they are executed
     * transactional. If resource changes (create, rename or delete file) are part
     * of the change the failure handling strategy is abort.
     */
    val TextOnlyTransactional = "textOnlyTransactional"

    /**
     * The client tries to undo the operations already executed. But there is no
     * guaruntee that this is succeeding.
     */
    val Undo = "undo"
}