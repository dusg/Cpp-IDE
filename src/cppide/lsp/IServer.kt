package cppide.lsp

import com.intellij.openapi.project.Project
import java.io.InputStream
import java.io.OutputStream

interface IServer {
    val outStream: OutputStream
    val inStream: InputStream
    fun start(project: Project): Process
}

abstract class LSPServerBase : IServer {
    protected lateinit var process: Process
    override val outStream: OutputStream
        get() = process.outputStream
    override val inStream: InputStream
        get() = process.inputStream
}