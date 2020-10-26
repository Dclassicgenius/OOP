
import kotlin.random.Random

fun main(args : Array<String>) {
    test1()
}

fun test1() {
    val arrList = ArrayList<ArrayList<Double>>(List(3){
        return@List ArrayList<Double>(List(3) {
            Random.nextInt(-5, 5).toDouble()
        })
    })

    val nullMatrix = Matrix(4, 3)
    println("null Matrix : $nullMatrix")
    val identityMatrix = Matrix(3, 3, true)
    println("Identity matrix : $identityMatrix")

    //creates Matrix filled with elements from double-dimension ArrayList
    val matrix = Matrix(arrList)
    println("Matrix with random elements from array list : $matrix")

    // Get value of matrix element at position (i, j)
    val element = identityMatrix[1, 1]
    println("element at position (1, 1) of identity matrix: $element")

    // Change value of matrix element at position (i, j)
    nullMatrix[1, 2] = 111.0
    println("updated the value at (1, 2) of nullMatrix: ${nullMatrix[1, 2]}")
    println("null matrix after updating value:$nullMatrix")

    // Add one matrix to another
    val sum = identityMatrix + Matrix(3, 3, true)
    println("Result of matrix addition: $sum")

    //Subtract one matrix from another
    val diff = matrix - Matrix(3, 3, true)
    println("Result of matrix subtraction: $diff")

    // Multiply one matrix by another
    val product = matrix * Matrix(3, 3, true)
    println("Result of multiplication by Identity Matrix: $product")

    //Multiple matrix by scalar
    val scalarProd = identityMatrix * 5.0
    println("Multiplication of  identity Matrix by 5: $scalarProd")

    // Negate the matrix
    val negate = -identityMatrix
    println("Negated identityMatrix: $negate")

    //Get matrix's minor at (i,j)
    val minor = matrix.getMinor(2, 2)
    println("Matrix's minor at position (2,2): $minor")

    //Calculate determinant of the matrix
    val det = matrix.determinant()
    println("Determinant of the matrix: $det")

    // Compare two matrices
    val areEqual = nullMatrix == identityMatrix
    println("Result of comparison:   $areEqual")

    // Get elements of the matrix as ArrayList
    val newArrList = matrix.toArrayList()
    println("Elements of matrix as ArrayList: $newArrList")
}