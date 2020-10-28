
fun main(args : Array<String>) {
    test()
}


fun test() {
    val a = Point(0.0, 5.0)
    val b = Point(10.0, 0.0)
    val c = Point(5.0, 10.0)


    val figures = listOf<Shapes>(
        Rectangle(a, b),
        Rectangle(a, 8.0, 12.0),
        Square(c, 3.0),
        Square(b,6.0),
        Circle(7.0),
        Circle(13.0),
        Triangle(a, b, c),
        Triangle(Point(0.0, 0.0), Point(-4.0, -10.0), Point(8.0, -6.0))
    )

    var totalArea = 0.0
    var largestArea : Shapes= figures[0]
    var largestPerimeter : Shapes = figures[0]
    var smallestArea : Shapes = figures[0]
    var smallestPerimeter : Shapes = figures[0]


    for (figure in figures) {
        totalArea += figure.calcArea()


        if (figure.calcArea() > largestArea.calcArea())
            largestArea = figure


        if (figure.calcArea() < smallestArea.calcArea())
            smallestArea = figure


        if (figure.calcPerimeter() > largestPerimeter.calcArea())
            largestPerimeter = figure


        if (figure.calcPerimeter() < smallestPerimeter.calcArea())
            smallestPerimeter = figure
    }

    println("Sum Total area of figures: $totalArea")
    println("The biggest area: ${largestArea.calcArea()} - $largestArea")
    println("The smallest area: ${smallestArea.calcArea()} - $smallestArea")
    println("The biggest perimeter: ${largestPerimeter.calcPerimeter()} - $largestPerimeter")
    println("The smallest perimeter: ${smallestPerimeter.calcPerimeter()} - $smallestPerimeter")
}
