package forms

import TextEncryptor
import javax.swing.JFrame

object MainFormHandler {
    fun displayForm() {
        val form = MainForm()
        val frame = JFrame("MainForm")
        frame.contentPane = form.panel
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
        // add an event listener to the encrypt button to show the encrypt form
        form.encryptButton.addActionListener {
            // hide this frame and show the other one
            frame.isVisible = false
            EncryptFormHandler.displayForm { text, key -> TextEncryptor(text, key).encrypt() }
        }
        
        // add an event handler to the decrypt button to show the decrypt form
        form.decryptButton.addActionListener {
            frame.isVisible = false
            DecryptFormHandler.displayForm()
        }
    }
}