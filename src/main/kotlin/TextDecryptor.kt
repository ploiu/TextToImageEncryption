import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class TextDecryptor(encryptionKey: String, private val file: File) {
    private val numberifiedEncryptionKey = numberifyText(encryptionKey)
    private val colorHelper = RandomColorHelper(numberifiedEncryptionKey)

    fun decryptImage(): String {
        // generate the list of colors for each base64 character
        val colorToCharMapping = colorHelper.getUniqueColorsForEachCharacter().flip()
        // read the image from the file path
        val image = ImageIO.read(file)
        val colors = getImageColorArray(image)
        // for each color, use the mapping to convert the color to a character until we get the result
        val builder = StringBuilder()
        for (color in colors) {
            val char = colorToCharMapping[color and 0xFFFFFF]
            // make sure we don't accidentally insert null
            builder.append(char ?: "")
        }
        return convertFromBase64(builder.toString())
    }

    private fun getImageColorArray(image: BufferedImage): Array<Int> {
        // the max x and y coordinates for each pixel location
        val stopColor = Constants.imageStopColor
        val rawRgb = image.getRGBArray()
        // get the number of characters there actually are (keep going until we hit the stopColor)
        var charCount = 0
        for (color in rawRgb) {
            if (color and 0xFFFFFF != stopColor and 0xFFFFFF && color and 0xFFFFFF != 0x000000) {
                charCount++
            } else {
                break
            }
        }
        // now add all the colors from 0 to charCount
        val charColorArray = Array(charCount) { 0 }
        for (index in 0 until charCount) {
            charColorArray[index] = rawRgb[index] and 0xFFFFFF
        }
        return charColorArray
    }
}