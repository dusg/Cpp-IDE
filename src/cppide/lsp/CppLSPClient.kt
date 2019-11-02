package cppide.lsp

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.util.ui.UIUtil
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageServer
import org.wso2.lsp4intellij.utils.ApplicationUtils
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.FutureTask
import javax.swing.Icon

class CppInitializeParamsBuilder : InitializeParams {
    private val myCompletionItem: CompletionItemCapabilities
        get() {
            val completionItem = CompletionItemCapabilities()
            completionItem.snippetSupport = true
            completionItem.commitCharactersSupport = false
            completionItem.deprecatedSupport = true
            completionItem.preselectSupport = true
            return completionItem
        }
    private val myFoldingRange: FoldingRangeCapabilities
        get() {
            val foldingRange = FoldingRangeCapabilities()
            foldingRange.dynamicRegistration = false
            foldingRange.lineFoldingOnly = false
            return foldingRange
        }
    private val myCodeAction: CodeActionCapabilities
        get() {
            return CodeActionCapabilities(CodeActionLiteralSupportCapabilities(CodeActionKindCapabilities(mutableListOf(
                    CodeActionKind.QuickFix, CodeActionKind.Refactor, CodeActionKind.RefactorExtract, CodeActionKind.RefactorInline,
                    CodeActionKind.RefactorRewrite, CodeActionKind.Source
            ))), false)
        }
    private val myCompletion: CompletionCapabilities
        get() {
            val completionCapabilities = CompletionCapabilities()
            completionCapabilities.completionItem = myCompletionItem
            completionCapabilities.completionItemKind = CompletionItemKindCapabilities(mutableListOf(
                    CompletionItemKind.Text,
                    CompletionItemKind.Method,
                    CompletionItemKind.Function,
                    CompletionItemKind.Constructor,
                    CompletionItemKind.Field,
                    CompletionItemKind.Variable,
                    CompletionItemKind.Class,
                    CompletionItemKind.Interface,
                    CompletionItemKind.Value,
                    CompletionItemKind.Enum,
                    CompletionItemKind.Keyword,
                    CompletionItemKind.Snippet,
                    CompletionItemKind.Color,
                    CompletionItemKind.File,
                    CompletionItemKind.Reference,
                    CompletionItemKind.EnumMember,
                    CompletionItemKind.Constant,
                    CompletionItemKind.Struct,
                    CompletionItemKind.TypeParameter

            ))
            completionCapabilities.contextSupport = false
            return completionCapabilities
        }
    private val myTextDocumentCapabilities: TextDocumentClientCapabilities
        get() {
            val textDocumentParams = TextDocumentClientCapabilities()
            textDocumentParams.synchronization = SynchronizationCapabilities(true, true, true)
            textDocumentParams.completion = myCompletion
            textDocumentParams.hover = HoverCapabilities(false)
            textDocumentParams.signatureHelp = SignatureHelpCapabilities(false)
            textDocumentParams.references = ReferencesCapabilities(false)
            textDocumentParams.documentHighlight = DocumentHighlightCapabilities(false)
            textDocumentParams.documentSymbol = DocumentSymbolCapabilities(false)
            textDocumentParams.formatting = FormattingCapabilities(false)
            textDocumentParams.rangeFormatting = RangeFormattingCapabilities(false)
            textDocumentParams.declaration = DeclarationCapabilities(false, true)
            textDocumentParams.definition = DefinitionCapabilities(false, true)
            textDocumentParams.typeDefinition = TypeDefinitionCapabilities(false, true)
            textDocumentParams.implementation = ImplementationCapabilities(false, true)
            textDocumentParams.codeAction = myCodeAction
            textDocumentParams.documentLink = DocumentLinkCapabilities(false)
            //todo            textDocumentParams.colorProvider
            textDocumentParams.rename = RenameCapabilities(true, false)
            textDocumentParams.publishDiagnostics = PublishDiagnosticsCapabilities(true)
            textDocumentParams.foldingRange = myFoldingRange
            return textDocumentParams
        }
    private val myWorkspaceCapabilities: WorkspaceClientCapabilities
        get() {
            val workspaceClientCapabilities = WorkspaceClientCapabilities()
            workspaceClientCapabilities.applyEdit = true
            workspaceClientCapabilities.workspaceEdit = WorkspaceEditCapabilities()
            workspaceClientCapabilities.didChangeConfiguration = DidChangeConfigurationCapabilities()
            workspaceClientCapabilities.didChangeWatchedFiles = DidChangeWatchedFilesCapabilities()
            workspaceClientCapabilities.symbol = SymbolCapabilities()
            workspaceClientCapabilities.executeCommand = ExecuteCommandCapabilities()
            workspaceClientCapabilities.workspaceFolders = false
            workspaceClientCapabilities.configuration = false
            return workspaceClientCapabilities
        }
    private val myWorkspaceFolders: MutableList<WorkspaceFolder>
        get() {
            return mutableListOf()
        }
    private val myClientCapabilities: ClientCapabilities
        get() {
            val clientCapabilities = ClientCapabilities()
            clientCapabilities.workspace = myWorkspaceCapabilities
            clientCapabilities.textDocument = myTextDocumentCapabilities
            clientCapabilities.experimental = null
            return clientCapabilities
        }

    constructor() {
    }

    fun create(project: Project): InitializeParams {
        val initializeParams = InitializeParams()
        initializeParams.processId = ProcessHandle.current().pid().toInt()
        initializeParams.rootUri = project.basePath
        initializeParams.capabilities = myClientCapabilities
        initializeParams.trace = "off"
        initializeParams.workspaceFolders = myWorkspaceFolders
        return initializeParams
    }

}

class CppLSPClient : LanguageClient {
    lateinit var serverFuture: Future<Void>
    lateinit var languageServer: LanguageServer
    var serverProcess: IServer? = null
    private var myProject: Project

    private val LOG = Logger.getInstance(CppLSPClient::class.java)

    constructor(project: Project) {
        this.myProject = project
    }

    /**
     * The workspace/configuration request is sent from the server to the client to fetch
     * configuration settings from the client. The request can fetch n configuration settings
     * in one roundtrip. The order of the returned configuration settings correspond to the
     * order of the passed ConfigurationItems (e.g. the first item in the response is the
     * result for the first configuration item in the params).
     */
    override fun configuration(configurationParams: ConfigurationParams?): CompletableFuture<MutableList<Any>> {
        return super.configuration(configurationParams)
    }


    override fun workspaceFolders(): CompletableFuture<MutableList<WorkspaceFolder>> {
        return CompletableFuture.completedFuture(mutableListOf())
    }

    /**
     * Diagnostics notifications are sent from the server to the client to
     * signal results of validation runs.
     */
    override fun publishDiagnostics(diagnostics: PublishDiagnosticsParams?) {
        //todo
    }

    /**
     * The show message request is sent from a server to a client to ask the
     * client to display a particular message in the user interface. In addition
     * to the show message notification the request allows to pass actions and
     * to wait for an answer from the client.
     */
    override fun showMessageRequest(requestParams: ShowMessageRequestParams): CompletableFuture<MessageActionItem> {
        val actions = requestParams.actions
        val title = "Language Server message"
        val message = requestParams.message
        val msgType = requestParams.type
        val icon: Icon?
        when (msgType) {
            MessageType.Error -> icon = UIUtil.getErrorIcon()
            MessageType.Warning -> icon = UIUtil.getWarningIcon()
            MessageType.Info -> icon = UIUtil.getInformationIcon()
            MessageType.Log -> icon = UIUtil.getInformationIcon()
            else -> {
                icon = null
                LOG.warn("No message type for $message")
            }
        }

        val titles = ArrayList<String>()
        for (item in actions) {
            titles.add(item.title)
        }
        val task = FutureTask { Messages.showDialog(message, title, titles.toTypedArray(), 0, icon) }
        ApplicationManager.getApplication().invokeAndWait(task)

        var exitCode = 0
        try {
            exitCode = task.get()
        } catch (e: InterruptedException) {
            LOG.warn(e.message)
        } catch (e: ExecutionException) {
            LOG.warn(e.message)
        }


        return CompletableFuture.completedFuture(MessageActionItem(actions.get(exitCode).getTitle()))

    }

    /**
     * The telemetry notification is sent from the server to the client to ask
     * the client to log a telemetry event.
     */
    override fun telemetryEvent(`object`: Any?) {
        //todo
    }

    /**
     * The log message notification is send from the server to the client to ask
     * the client to log a particular message.
     */
    override fun logMessage(message: MessageParams?) {
        //todo
    }

    /**
     * The show message notification is sent from a server to a client to ask
     * the client to display a particular message in the user interface.
     */
    override fun showMessage(messageParams: MessageParams) {
        val title = "Language Server message"
        val message = messageParams.message
        ApplicationUtils.invokeLater {
            val msgType = messageParams.type
            when (msgType) {
                MessageType.Error -> Messages.showErrorDialog(message, title)
                MessageType.Warning -> Messages.showWarningDialog(message, title)
                MessageType.Info -> Messages.showInfoMessage(message, title)
                MessageType.Log -> Messages.showInfoMessage(message, title)
                else -> LOG.warn("No message type for $message")
            }
        }
    }

    fun init() {
        languageServer.initialize(CppInitializeParamsBuilder().create(myProject))
    }
}