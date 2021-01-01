import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.sqrt

class ImageHandler(private val colors: Array<Int>) {
    // calculate the size the image needs to be to hold all the data
    private val imageWidth = sqrt((colors.size * 2).toDouble()).toInt()

    val image: BufferedImage = BufferedImage(imageWidth, imageWidth, BufferedImage.TYPE_INT_RGB)
    private val graphics = image.graphics

    fun writeColorsToImage() {
        var x = 0
        var y = 0
        // for each color, create a new color and write a pixel-sized rectangle to the image
        for (rgb in colors) {
            val color = Color(rgb)
            // check if x is greater than the image max width, and if it is reset it and increment y
            if (x == image.width) {
                x = 0
                y++
            }
            // write the pixel to the image
            graphics.color = color
            graphics.fillRect(x++, y, 1, 1)
        }
        if (x++ >= image.width) {
            x = 0
            y++
        }
        // write the image stop color so that our reader knows we're done
        graphics.color = Color(Constants.imageStopColor)
        graphics.fillRect(x, y, 1, 1)
    }
}