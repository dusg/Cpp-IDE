package cppide.lsp

import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.services.LanguageServer
import org.wso2.lsp4intellij.client.ClientContext
import org.wso2.lsp4intellij.client.DefaultLanguageClient
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

class Client(context: ClientContext?) : DefaultLanguageClient(context) {

    private val clientCapabilities: ClientCapabilities
        get() {
            TODO()
        }
    private val initArgs: InitializeParams
        get() {
            val args = InitializeParams()
            args.processId = ProcessHandle.current().pid().toInt()
            args.rootUri = context.project.basePath
            args.capabilities = clientCapabilities
            args.trace = "off"
            args.workspaceFolders = this.workspaceFolders().get()
            return args
        }
    lateinit var serverFuture: Future<Void>
    lateinit var languageServer: LanguageServer

    override fun workspaceFolders(): CompletableFuture<MutableList<WorkspaceFolder>> {
        TODO()
        return super.workspaceFolders()
    }

    /**
     * Diagnostics notifications are sent from the server to the client to
     * signal results of validation runs.
     */
    override fun publishDiagnostics(diagnostics: PublishDiagnosticsParams?) {
        super.publishDiagnostics(diagnostics)
    }

    /**
     * The show message request is sent from a server to a client to ask the
     * client to display a particular message in the user interface. In addition
     * to the show message notification the request allows to pass actions and
     * to wait for an answer from the client.
     */
    override fun showMessageRequest(requestParams: ShowMessageRequestParams?): CompletableFuture<MessageActionItem> {
        return super.showMessageRequest(requestParams)
    }

    /**
     * The telemetry notification is sent from the server to the client to ask
     * the client to log a telemetry event.
     */
    override fun telemetryEvent(`object`: Any?) {
        super.telemetryEvent(`object`)
    }

    /**
     * The log message notification is send from the server to the client to ask
     * the client to log a particular message.
     */
    override fun logMessage(message: MessageParams?) {
        super.logMessage(message)
    }

    /**
     * The show message notification is sent from a server to a client to ask
     * the client to display a particular message in the user interface.
     */
    override fun showMessage(messageParams: MessageParams?) {
        super.showMessage(messageParams)
    }

    fun init() {
        languageServer.initialize(initArgs)
    }
}