package forms

import numberifyText
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

object EncryptFormHandler {
    fun displayForm(submitFunction: (input: String, encryptionKey: Long) -> Unit) {
        val form = EncryptForm()
        val frame = JFrame("Test")
        frame.contentPane = form.panel
        frame.size = Dimension(700, 500)
        frame.defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
        frame.isVisible = true
        form.textArea.columns = 100
        form.textArea.lineWrap = true
        // on submit event
        form.submit.addActionListener {
            if (it != null) {
                submitFunction.invoke(form.textArea.text, numberifyText(form.encryptionKeyField.text))
            }
        }
        // show the main form when this one is closed
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                frame.dispose()
                MainFormHandler.displayForm()
            }
        })
    }
}
