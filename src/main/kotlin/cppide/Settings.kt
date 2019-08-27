package cppide

import com.sun.jna.Platform

class Settings {
    companion object {
        val I = Settings()
    }

    fun getClangdPath(): String {
        return if (Platform.isWindows()) {
            "C:\\Program Files\\LLVM\\bin\\clangd.exe"
        } else {
            // Mac
            ""
        }
    }
}