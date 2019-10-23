package cppide.plugin

import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.progress.ProgressIndicator
import cppide.CppFileType
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition

class CpptoolPreloadingActivity : PreloadingActivity{
    constructor()
    /**
     * Perform the preloading
     * @param indicator a progress indicator for the background preloading process.
     * Canceled if the application has exited.
     * Long actions should periodically perform `indicator.checkCanceled()`.
     */
    override fun preload(indicator: ProgressIndicator) {
        val ext = CppFileType.INSTANCE.defaultExtension.replace(';', ',')
        val cmd = arrayOf("cquery")
        IntellijLanguageClient.addServerDefinition(RawCommandServerDefinition(ext, cmd))
    }
}