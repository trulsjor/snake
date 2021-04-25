package no.trulsjor.snake

fun main() {
    val snake = Snake()
    println(snake)
    snake.move(Direction.RIGHT)
    println(snake)
}

class Snake(val startPoint: Point = Point.START) {
    private val body: ArrayDeque<Point> = ArrayDeque(listOf(Point.START))

    //init {
    //    grow(3, Direction.LEFT)
    //}

    fun headPoint(): Point {
        return Point.of(body.first())
    }

    fun size(): Int {
        return body.size
    }

    fun move(direction: Direction) {
        body.addFirst(body.first() + direction)
        body.removeLast()
    }

    fun grow(size: Int, direction: Direction) {
        for (a in 1..size) {
            body.add(body.last() + direction)
        }
    }

    override fun toString(): String {
        return body.toString()
    }
}

data class Point(private val x: Int, private val y: Int) {

    operator fun plus(direction: Direction): Point {
        return Point(x + direction.x, y + direction.y)
    }

    companion object Start {
        fun of(point: Point): Point {
            return Point(point.x, point.y)
        }

        val START = Point(20, 20)
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}

enum class Direction(val x: Int, val y: Int) {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    operator fun times(length: Int) {
        x.times(length)
    }
}


