import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun BufferedImage.writeToFile(destination: String) {
    val imageExtensions = listOf("png", "jpg", "bmp")
    val file = File(destination)
    if (imageExtensions.contains(file.extension.toLowerCase())) {
        ImageIO.write(this, file.extension.toUpperCase(), file)
    } else {
        throw Exception("Cannot overwrite non-image file!")
    }
}

fun BufferedImage.getRGBArray(): Array<Int> {
    val maxX = this.width
    val maxY = this.height
    val pixels = Array(maxX * maxY) { 0 }
    var pixelIndex = 0
    // use nested loop to get all the pixel data; start with y instead of x since we start top left and go to bottom right
    for (y in 0 until maxY) {
        for (x in 0 until maxX) {
            pixels[pixelIndex++] = this.getRGB(x, y) and 0xFFFFFF
        }
    }
    return pixels
}