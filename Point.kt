import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt


//Represents a simple 2D point to store coordinates.

data class Point(
    val x : Double,
    val y : Double
)

open class Rectangle(Top : Point, Bottom : Point) : Shapes {
    private var A = Point(Top.x, Top.y)
    private var B = Point(Bottom.x, Top.y)
    private var C = Point(Bottom.x, Bottom.y)
    private var D = Point(Top.x, Bottom.y)



    constructor(vertex: Point, width : Double, height : Double)
            : this(vertex, Point(vertex.x + width, vertex.y + height))


    fun getVertices() : List<Point> {
        return listOf(A, B, C ,D)
    }


    override fun calcArea(): Double {
        return length(A, B) * length(B, C)
    }


    override fun calcPerimeter(): Double {
        return length(A, B) + length(B, C) + length(C, D) + length(D, A)
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rectangle

        if (A != other.A) return false
        if (B != other.B) return false
        if (C != other.C) return false
        if (D != other.D) return false


        return true
    }


    override fun hashCode(): Int {
        var result = A.hashCode()
        result = 31 * result + B.hashCode()
        result = 31 * result + C.hashCode()
        result = 31 * result + D.hashCode()
        return result
    }


    override fun toString(): String {
        return "Rectangle(A=$A, B=$B, C=$C, D=$D)"
    }

}

class Square(vertex : Point, length : Double)
    : Rectangle(vertex, length, length)

class Circle(radius : Double) : Shapes{
    private val r = radius

    override fun calcArea() : Double {
        return PI * r.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 2 * PI * r
    }


    override fun toString(): String {
        return "Circle(R=$r)"
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Circle

        if (r != other.r) return false

        return true
    }


    override fun hashCode(): Int {
        return r.hashCode()
    }
}

class Triangle(private var A: Point, private var B : Point, private var C : Point) : Shapes {


    init {
        val a = length(A, B)
        val b = length(B, C)
        val c = length(C, A)


        if (a + b < c || a + c < b || b + c < a)
            throw ArithmeticException("ERROR INCORRECT COORDINATES")
    }

    fun getVertices() : List<Point> {
        return listOf(A, B, C)
    }

    override fun calcArea(): Double {
        // Heron's formula:
        // Area = SquareRoot(s * (s - a) * (s - b) * (s - c))
        // where s = (a + b + c) / 2, or 1/2 of the perimeter of the triangle
        val s = calcPerimeter() / 2.0
        val a = length(A, B)
        val b = length(B, C)
        val c = length(C, A)


        return sqrt(s * (s - a) * (s - b) * (s - c))
    }


    override fun calcPerimeter(): Double {
        return length(A, B) + length(B, C) + length(C, A)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Triangle

        if (A != other.A) return false
        if (B != other.B) return false
        if (C != other.C) return false

        return true
    }
    override fun hashCode(): Int {
        var result = A.hashCode()
        result = 31 * result + B.hashCode()
        result = 31 * result + C.hashCode()
        return result
    }


    override fun toString(): String {
        return "Triangle(A=$A, B=$B, C=$C)"
    }
}
