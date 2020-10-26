import kotlin.math.pow

class Matrix(numberOfcolumns : Int, numberOfrows : Int, isIdentity : Boolean = false) {
    private var elements : ArrayList<ArrayList<Double>>

    val columns : Int
        get() = elements[0].size

    val rows : Int
        get() = elements.size

    init {
        if (isIdentity && numberOfcolumns != numberOfrows)
            throw IllegalArgumentException("ERROR! Identity matrix must be square.")

        if (numberOfcolumns <= 0 || numberOfrows <= 0)
            throw IllegalArgumentException("ERROR! Matrix dimensions Must be positive")

        elements = ArrayList()

        // fill matrix with zeroes, but if it's Identity Matrix then place ones at the diagonal
        for (i in 0 until numberOfrows) {
            val row = List(numberOfcolumns){ j -> if (i == j && isIdentity) 1.0 else 0.0}
            elements.add(ArrayList(row))
        }
    }

    //Creates matrix and checks for rows to have equal lengths
    constructor(elements : ArrayList<ArrayList<Double>>) : this(elements[0].size, elements.size) {
        for (i in 0 until this.elements.size - 1)
            if (this.elements[i].size != this.elements[i+1].size)
                throw IllegalStateException("ERROR! All rows in matrix must have equal length.")

        for (i in 0 until this.rows)
            for (j in 0 until this.columns)
                this.elements[i][j] = elements[i][j]
    }

    private fun isInRange(i : Int, j : Int) = !(i >= rows || j >= columns || i < 0 || j < 0)

    //returns element of the matrix at position ([i],[j]).
    operator fun get(i : Int, j : Int) : Double {
        if (!isInRange(i, j))
            throw IndexOutOfBoundsException("ERROR! Index is out of range.")

        return elements[i][j]
    }

    //Changes the matrix element at position (i,j) to a new value.
    operator fun set(i : Int, j : Int, value : Double) {
        if (!isInRange(i, j))
            throw IndexOutOfBoundsException("ERROR! Index is out of range.")

        elements[i][j] = value
    }

    //Checks for rows length and columns length to be equal in both matrices.
    fun isEqualDimension(second: Matrix) = (this.rows == second.rows && this.columns == second.columns)

   //add 2 matrices and returns the result of the addition
    operator fun plus(second: Matrix) : Matrix {
        if (!this.isEqualDimension(second))
            throw ArithmeticException("ERROR! Matrices must be of the same dimension!")

        val result = Matrix(rows, columns)

        for (i in 0 until rows)
            for (j in 0 until columns)
                result[i, j] = this[i, j] + second[i, j]

        return result
    }

    // Performs matrix operation and returns the result
    operator fun minus(second: Matrix) : Matrix {
        if (!this.isEqualDimension(second))
            throw ArithmeticException("ERROR! Matrices must be of the same dimension!")

        val result = Matrix(rows, columns)

        for (i in 0 until rows)
            for (j in 0 until columns)
                result[i, j] = this[i, j] - second[i, j]

        return result
    }

    // Scalar multiplication
    operator fun times(scalar : Double) : Matrix {
        val result = Matrix(rows, columns)

        for (i in 0 until rows)
            for (j in 0 until columns)
                result[i, j] = this[i, j] * scalar

        return result
    }

    // Matrix multiplication
    //Checks for equality of  number of columns  in first matrix and number of rows in second.
    private fun isCompatible(second: Matrix) = (this.columns == second.rows)

    operator fun times(second : Matrix) : Matrix {
        if (!this.isCompatible(second))
            throw ArithmeticException("ERROR! Matrices are not compatible.")

        val result = Matrix(rows, second.columns)
        for (i in 0 until result.rows)
            for (j in 0 until result.columns)
                for (k in 0 until this.columns)
                    result[i, j] += this[i, k] * second[k, j]

        return result
    }
    //check for equally of 2 matrices
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (elements != other.elements) return false

        return true
    }

    //matrix negation
    operator fun unaryMinus() : Matrix {
        return this * -1.0
    }

    //gets matrix minor
    fun getMinor(i : Int, j : Int) : Matrix {
        val minor = ArrayList<ArrayList<Double>>()

        for (row in 0 until rows) {
            if (row == i) continue

            val minorRow = ArrayList<Double>()

            for (col in 0 until columns)
                if (col != j) minorRow.add(this[row, col]) else continue

            minor.add(minorRow)
        }

        return Matrix(minor)
    }

    // Calculate determinant of the square matrix.
    fun determinant() : Double {
        if (columns != rows)
            throw ArithmeticException("ERROR! Matrix must be square to calculate determinant.")

        if (rows == 1)
            return this[0, 0]

        if (rows == 2)
            return (this[0, 0] * this[1, 1]) - (this[0, 1] * this[1, 0])

        // find number of the row where amount of zeroes is the highest
        var decompositionRow = 0
        for (i in 0 until rows)
            if (elements[i].count { it == 0.0 } > elements[decompositionRow].count { it == 0.0 })
                decompositionRow = i

        // decompose the matrix by row
        var determinant = 0.0
        for (j in 0 until columns) {
            val complementary = (-1.0).pow(decompositionRow + j) * getMinor(decompositionRow, j).determinant()
            determinant += this[decompositionRow, j] * complementary

        }

        return determinant
    }

    //Creates double dimension array list
    fun toArrayList() : ArrayList<ArrayList<Double>> {
        // create new arrayList instead of returning field elements to prevent change of data from outside
        val arrayList = ArrayList<ArrayList<Double>>()
        arrayList.addAll(elements)

        return arrayList
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }

    override fun toString(): String {
        var p = String()

        for (row in elements)
            p += "$row\n"

        return "\n" + p
    }
}