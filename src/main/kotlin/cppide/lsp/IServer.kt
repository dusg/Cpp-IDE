package cppide.lsp

import com.intellij.openapi.project.Project
import java.io.InputStream
import java.io.OutputStream

interface IServer {
    val outStream: OutputStream
    val inStream: InputStream
    fun start(project: Project): Process
}