package cppide.lsp

import com.intellij.compiler.options.CompilerOptionsFilter
import com.intellij.openapi.project.Project
import com.sun.jna.Platform
import cppide.Settings
import java.io.File
import java.io.InputStream
import java.io.OutputStream

class Clangd : LSPServerBase() {
    override fun start(project: Project): Process {
        val clangdPath = Settings.I.getClangdPath()
        val processBuilder = ProcessBuilder(clangdPath)
        val env = processBuilder.environment()
        var pathKey = "PATH"
        if (Platform.isWindows()) {
            pathKey = "Path"
        }
        env[pathKey] = String.format("${env[pathKey]};${File(clangdPath).parent}")
        processBuilder.directory(File(project.basePath))

        process = processBuilder.start()
        return process
    }
}

class Ccls : LSPServerBase() {
    override fun start(project: Project): Process {
        val cclsPath = Settings.I.getCclsPath()
        val processBuilder = ProcessBuilder(cclsPath)
        val env = processBuilder.environment()
        var pathKey = "PATH"
        if (Platform.isWindows()) {
            pathKey = "Path"
        }
        env[pathKey] = String.format("${env[pathKey]};${File(cclsPath).parent}")
        processBuilder.directory(File(project.basePath))
        process = processBuilder.start()
        return process
    }

}