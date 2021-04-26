package no.trulsjor.snake

import kotlin.random.Random

fun main() {
    val snake = Snake(
        snakeLength = 3,
        startPoint = Point(10, 10),
        dimension = 20,
        apples = mutableListOf(
            Point(3, 10),
            Point(4, 3),
            Point(15, 15),
            Point(12, 12),
            Point(17, 6),
            Point(8, 8)
        )
    )
    do {
        snake.draw()
        Thread.sleep(400)
        val currentDirection = snake.direction()
        val newDir = listOf(currentDirection, currentDirection.turnLeft(), currentDirection.turnRight(), currentDirection)[Random.nextInt(0, 4)]

    } while (snake.move(newDir))
}

class Snake(
    startPoint: Point = Point.START,
    private var snakeLength: Int = 4,
    private var currentDirection: Direction = Direction.UP,
    private val apples: MutableList<Point> = mutableListOf(),
    private val dimension: Int = 20
) {
    private val body: ArrayDeque<Point> = ArrayDeque(listOf(startPoint))


    fun direction() = currentDirection

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
        fun random(): Direction = listOf(DOWN, LEFT, UP, RIGHT)[Random.nextInt(0, 4)]
    }
}


