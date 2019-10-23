package cppide.lsp

import com.intellij.openapi.project.Project
import cppide.plugin.CppProjectComponent
import org.eclipse.lsp4j.jsonrpc.Launcher
import org.eclipse.lsp4j.services.LanguageServer
import java.util.concurrent.Future

class LSPMgr {
    private lateinit var serverFuture: Future<Void>
    private lateinit var languageServer: LanguageServer
    private lateinit var _client: Client
    lateinit var project: Project
    private var _serverProcess: IServer? = null

    companion object {
        fun getInstance(project: Project): LSPMgr {
            val lsp = project.getComponent(CppProjectComponent::class.java).LSP
            lsp.project = project
            return lsp
        }
    }

    fun startServer() {
        startServer(project)
    }

    fun startServer(proj: Project) {
        if (_serverProcess != null) {
            return
        }

        val serverProc = getServerProc()
        _serverProcess = serverProc
        serverProc.start(proj)
        _client = Client(CppClientContext(proj))
        val launcher = Launcher.createLauncher(
            _client,
            LanguageServer::class.java, serverProc.inStream, serverProc.outStream
        )
        _client.languageServer = launcher.getRemoteProxy()
        _client.serverFuture = launcher.startListening()
        _client.init()
    }

    private fun getServerProc(): IServer {
        return Clangd()
    }
}