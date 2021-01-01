import forms.FileViewerHandler

class TextEncryptor(private val text: String, encryptionKey: Long) {
    private val colorHelper = RandomColorHelper(encryptionKey)

    fun encrypt() {
        println("encrypting:\n $text")
        val charToColorMapping = colorHelper.getUniqueColorsForEachCharacter()
        val base64Text = convertToBase64(text)
        // now that we have a list of colors for our base64 stuff, we can convert the text to a list of color codes
        val colorCodes = generateColorListForImage(base64Text, charToColorMapping)
        val imageHandler = ImageHandler(colorCodes)
        imageHandler.writeColorsToImage()
        val filePath = FileViewerHandler.showSaveDialog()
        // now write the image to a file
        imageHandler.image.writeToFile(filePath)
    }

    /**
     * Generates a list of all the pixel colors for the passed base64 text and the passed colorMapping
     */
    private fun generateColorListForImage(text: String, colorMapping: Map<Char, Int>): Array<Int> {
        // create an int array with the same length as the number of chars in our text
        val colorArray = Array(text.length) { 0 }
        var index = 0
        for (char in text) {
            val result = colorMapping[char] ?: throw Exception("Char [$char] has not had a mapping created!")
            colorArray[index++] = result
        }
        return colorArray
    }

}