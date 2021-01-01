import java.util.*

/**
 * converts a string into a sum of all the characters and returns that sum
 */
fun numberifyText(text: String): Long {
    var numberVersion = 0L
    for (character in text.toCharArray()) {
        numberVersion += character.toLong()
    }
    return numberVersion
}

/**
 * Gets a list of all 65 characters that can exist inside a base64 string
 */
fun getBase64Characters(): Array<Char> {
    val uppercaseLetters = 'A'..'Z'
    val lowercaseLetters = 'a'..'z'
    val numbers = '0'..'9'
    val otherSymbols = arrayOf('+', '=', '/')
    // I have not found a simple way to convert a range to an array, so here we go
    val chars = Array(65) { 0.toChar() }
    // add all the characters from each of our ranges to the array
    var index: Int = 0
    for (char in uppercaseLetters) {
        chars[index++] = char
    }
    for (char in lowercaseLetters) {
        chars[index++] = char
    }
    for (char in numbers) {
        chars[index++] = char
    }
    for (char in otherSymbols) {
        chars[index++] = char
    }
    return chars
}

fun convertToBase64(text: String): String = Base64.getEncoder().encodeToString(text.toByteArray())

fun convertFromBase64(text: String) = String(Base64.getDecoder().decode(text))

fun <T, U> Map<T, U>.flip(): Map<U, T> {
    val map = HashMap<U, T>()
    for (entry in this.entries) {
        map[entry.value] = entry.key
    }
    return map
}