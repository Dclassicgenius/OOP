import kotlin.math.pow
import kotlin.math.sqrt

interface Shapes {
    fun calcArea() : Double
    fun calcPerimeter() : Double

    //Calculates distance between 2 given points.
    fun length(begin : Point, end : Point) : Double {
        return sqrt((end.x - begin.x).pow(2) + (end.y - begin.y).pow(2))
    }
}