import java.awt.Color
import java.util.*

class RandomColorHelper(encryptionKey: Long) {
    // use the encryption key so that it can be decrypted by the user later
    private val random = Random(encryptionKey)
    
    fun getUniqueColorsForEachCharacter(): Map<Char, Int> {
        val mapping = HashMap<Char, Int>()
        // the colors we've already used; blacklist the image stop color and the color black
        val usedColors = hashSetOf(Constants.imageStopColor and 0xFFFFFF, Color.BLACK.rgb and 0xFFFFFF)
        // get all the unique characters as a char list
        val characters = getBase64Characters()
        for (uniqueChar in characters) {
            // pick a random color for each character
            var colorForChar: Int
            do {
                colorForChar = pickRandomColor()
                if (!usedColors.contains(colorForChar)) {
                    usedColors.add(colorForChar)
                }
                // this shouldn't actually cause any noticeable delays with how many colors there are to pick from
            } while (!usedColors.contains(colorForChar))
            mapping[uniqueChar] = colorForChar
        }
        return mapping
    }

    private fun pickRandomColor() = random.nextInt(0xFFFFFF + 1) and 0xFFFFFF

}