package cppide.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.ui.Messages
import cppide.lsp.LSPMgr
import org.wso2.lsp4intellij.utils.ApplicationUtils

class CppProjectStartUp :StartupActivity{

    override fun runActivity(project: Project) {
        try {
            LSPMgr.getInstance(project).startServer()
        } catch (ex: Exception) {
            val title = "Language Server message"
            ApplicationUtils.invokeLater {
                Messages.showErrorDialog(project, ex.message, title)
//                Messages.showInfoMessage(project, ex.message, title)
//                Messages.showMessageDialog(project, ex.message, title, Messages.getErrorIcon())
            }
        }
    }


}
