package cppide.plugin

import com.intellij.openapi.components.ProjectComponent
import cppide.lsp.LSPMgr

class CppProjectComponent : ProjectComponent{
    val LSP = LSPMgr()
    /**
     * Invoked when the project corresponding to this component instance is closed.
     *
     *
     * Note that components may be created for even unopened projects and this method can be never
     * invoked for a particular component instance (for example for default project).
     */
    override fun projectClosed() {
        super.projectClosed()
    }

    /**
     * Invoked when the project corresponding to this component instance is opened.
     *
     *
     * Note that components may be created for even unopened projects and this method can be never
     * invoked for a particular component instance (for example for default project).
     */
    override fun projectOpened() {
        super.projectOpened()
    }
}