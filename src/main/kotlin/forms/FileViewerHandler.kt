package forms

import java.io.File
import javax.swing.JFileChooser
import javax.swing.JPanel

object FileViewerHandler {
    fun showOpenDialog(): File {
        val fileChooser = JFileChooser()
        val panel = JPanel()
        // only allow png files
        fileChooser.fileFilter = object : javax.swing.filechooser.FileFilter() {
            override fun accept(f: File?) = f != null && f.extension.toLowerCase() == "png"
            override fun getDescription() = "test"
        }
        fileChooser.showOpenDialog(panel)
        return fileChooser.selectedFile
    }
    
    fun showSaveDialog(): String {
        val fileChooser = JFileChooser()
        val panel = JPanel()
        fileChooser.showSaveDialog(panel)
        val selectedFile = fileChooser.selectedFile
        return selectedFile.absolutePath
    }
}