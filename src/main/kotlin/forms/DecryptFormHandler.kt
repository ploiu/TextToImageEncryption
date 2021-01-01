package forms

import TextDecryptor
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JFrame

object DecryptFormHandler {
    private lateinit var selectedFile: File
    fun displayForm() {
        val form = DecryptForm()
        val frame = JFrame("DecryptForm")
        frame.contentPane = form.panel
        frame.size = Dimension(700, 500)
        frame.defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
        frame.isVisible = true

        // show the main form when this one is closed
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                frame.dispose()
                MainFormHandler.displayForm()
            }
        })

        // when the search for file button is clicked, get the file that the user selects
        form.selectFileButton.addActionListener {
            selectedFile = FileViewerHandler.showOpenDialog()
        }
        
        form.decryptButton.addActionListener {
            val decryptionKey = form.decryptionKeyField.text
            val decryptor = TextDecryptor(decryptionKey, selectedFile)
            form.outputPane.text = decryptor.decryptImage()
        }
    }
}