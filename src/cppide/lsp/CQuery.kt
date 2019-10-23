package cppide.lsp

import com.intellij.openapi.project.Project
import java.io.File
import java.io.InputStream
import java.io.OutputStream

class CQuery : IServer {
    override val outStream: OutputStream
        get() {
            return _process.outputStream
        }
    override val inStream: InputStream
        get() {
            return _process.inputStream
        }
    private lateinit var _process: Process

    override fun start(project: Project): Process {
        val processBuilder = ProcessBuilder("cquery")
        processBuilder.directory(File(project.basePath))
        _process = processBuilder.start()
        return _process
    }
}