package cppide.lsp

import com.intellij.openapi.project.Project
import org.eclipse.lsp4j.jsonrpc.Launcher
import org.eclipse.lsp4j.services.LanguageServer

class LSPMgr {
    private var myLspMap: MutableMap<Project, CppLSPClient> = mutableMapOf()

    companion object {
        val Instance = LSPMgr()
    }

    fun startServer(proj: Project) {
        if (myLspMap.containsKey(proj)) {
            return
        }

        val serverProc = getServerProc()
        serverProc.start(proj)
        val lspClient = CppLSPClient(proj)
        val launcher = Launcher.createLauncher(
            lspClient,
            LanguageServer::class.java, serverProc.inStream, serverProc.outStream
        )
        lspClient.languageServer = launcher.remoteProxy
        lspClient.serverFuture = launcher.startListening()
        lspClient.serverProcess = serverProc
        lspClient.init()
        myLspMap[proj] = lspClient
    }

    private fun getServerProc(): IServer {
        return Ccls()
    }
}