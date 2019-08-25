package cppide.plugin

import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import cppide.lsp.LSPMgr
import java.io.File

class CppProjectStartUp :StartupActivity{
    private var _process: Process? = null

    override fun runActivity(project: Project) {
        LSPMgr.getInstance(project).startServer()
//        startCQuery(project)
//        project.getComponent(CppProjectComponent::class.java)
    }


}
