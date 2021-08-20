package no.trulsjor.snake

import kotlin.random.Random.Default.nextInt

fun main() {

    val dimension = 20
    fun randomApple() = Point(nextInt(0, dimension), nextInt(0,dimension))

    val initialApples = ((0..30).map { randomApple() }).toMutableList()
    val snake = Snake(
        snakeLength = 3,
        startPoint = Point(10, 10),
        dimension = dimension,
        apples = initialApples
    )
    do {
        snake.draw()
        Thread.sleep(100)

    } while (snake.moveRandomDir())
}

class Snake(
    startPoint: Point = Point.START,
    private var snakeLength: Int = 4,
    private var currentDirection: Direction = Direction.UP,
    private val apples: MutableList<Point> = mutableListOf(),
    private val dimension: Int = 20
) {
    private val body: ArrayDeque<Point> = ArrayDeque(listOf(startPoint))


    fun moveRandomDir(tries: Int = 10): Boolean {
        val newDir = listOf(
            currentDirection,
            currentDirection.turnLeft(),
            currentDirection.turnRight(),
            currentDirection
        )[nextInt(0, 4)]
        println("Moving $newDir, $tries remaining")
        val candidatePoint = body.first() + newDir
        val canMove = !body.contains(candidatePoint) && candidatePoint.notInWall(dimension)

        return if (tries > 0) when {
            canMove -> move(newDir)
            else -> moveRandomDir(tries-1)
        } else false
    }


    fun move(direction: Direction): Boolean {
        currentDirection = direction
        body.addFirst(body.first() + direction)
        if (apples.contains(body.first())) eat(body.first())
        if (snakeLength < body.size) body.removeLast()
        return body.validSnakeMove() && body.first().notInWall(dimension)
    }

    private fun eat(point: Point) {
        apples.remove(point)
        snakeLength++
    }

    override fun toString(): String = body.toString()


    fun draw() {
        for (y in dimension downTo -1) {
            for (x in -1..dimension) {
                print(getSymbol(Point(x, y)))
            }
            println()
        }
        println("---------------------------")
    }

    private fun getSymbol(point: Point): String {
        if (body.first() == point) return "\uD83D\uDE0B"
        if (body.contains(point)) return "\uD83D\uDFE1"
        if (apples.contains(point)) return "\uD83C\uDF4F"
        if (!point.notInWall(dimension)) return "\uD83D\uDFEB"
        return "  "
    }
}

private fun ArrayDeque<Point>.validSnakeMove() = !this.subList(1, this.size).contains(this.first())

data class Point(private val x: Int, private val y: Int) {

    operator fun plus(direction: Direction): Point = Point(x + direction.x, y + direction.y)

    companion object Start {
        val START = Point(0, 0)
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    fun notInWall(dimension: Int) = x in 0 until dimension && y in 0 until dimension
}


enum class Direction(val x: Int, val y: Int) {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    fun turnRight() =
        when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }

    fun turnLeft() =
        when (this) {
            UP -> LEFT
            LEFT -> DOWN
            DOWN -> RIGHT
            RIGHT -> UP
        }

    companion object RandomDirection {
        fun random(): Direction = listOf(DOWN, LEFT, UP, RIGHT)[nextInt(0, 4)]
    }
}


