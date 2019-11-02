package cppide.lsp

import com.intellij.openapi.project.Project
import org.wso2.lsp4intellij.client.ClientContext
import org.wso2.lsp4intellij.client.languageserver.requestmanager.RequestManager
import org.wso2.lsp4intellij.editor.EditorEventManager

class CppClientContext : ClientContext{
    private var myProject: Project

    constructor(project: Project){
        myProject = project
    }

    /**
     * Returns the [RequestManager] associated with the Language Server Connection.
     */
    override fun getRequestManager(): RequestManager {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the [EditorEventManager] for the given document URI.
     */
    override fun getEditorEventManagerFor(documentUri: String): EditorEventManager? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the [Project] associated with the LanuageClient.
     */
    override fun getProject(): Project {
        return myProject
    }
}