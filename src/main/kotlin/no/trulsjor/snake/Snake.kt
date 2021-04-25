package no.trulsjor.snake

class Snake(startPoint: Point = Point.START) {
    private val body: ArrayDeque<Point> = ArrayDeque(listOf(startPoint))

    fun headPoint(): Point {
        return Point.of(body.first())
    }

    fun size(): Int {
        return body.size
    }

    fun move(direction: Direction): Boolean {
        val newHead = body.first() + direction
        val validMove = !body.contains(newHead)
        body.addFirst(newHead)
        body.removeLast()
        return validMove
    }

    fun grow(size: Int, direction: Direction) = (1..size).forEach { _ -> body.add(body.last() + direction) }

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
}


