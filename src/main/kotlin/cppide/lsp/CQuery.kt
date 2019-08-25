package cppide.lsp

import com.intellij.openapi.project.Project
import java.io.File
import java.io.InputStream
import java.io.OutputStream

class CQuery {
    val outStream: OutputStream
        get() {
            return _process.outputStream
        }
    val inStream: InputStream
        get() {
            return _process.inputStream
        }
    private lateinit var _process: Process

    fun start(project: Project): Process {
        val processBuilder = ProcessBuilder("cquery")
        processBuilder.directory(File(project.basePath))
        _process = processBuilder.start()
        return _process!!
    }
}