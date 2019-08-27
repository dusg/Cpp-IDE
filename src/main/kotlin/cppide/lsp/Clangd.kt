package cppide.lsp

import com.intellij.openapi.project.Project
import com.sun.jna.Platform
import cppide.Settings
import java.io.File
import java.io.InputStream
import java.io.OutputStream

class Clangd :IServer{
    private lateinit var _process: Process
    override val outStream: OutputStream
        get() = _process.outputStream
    override val inStream: InputStream
        get() = _process.inputStream

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
        _process = processBuilder.start()
        return _process
    }
}