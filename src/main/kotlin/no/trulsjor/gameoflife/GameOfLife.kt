package no.trulsjor.gameoflife

import no.trulsjor.gameoflife.GameOfLife.Cell
import no.trulsjor.gameoflife.GameOfLife.Generation

fun main() {

    val dimension = 10
    val game = GameOfLife(
        dimension, Generation(
            setOf(
                Cell(3, 3),
                Cell(4, 3),
                Cell(5, 3)
            )
        )
    )
    do {
        game.draw()
        game.nextGen()
        Thread.sleep(100)
    } while (true)
}


class GameOfLife(
    val dimension: Int = 20,
    var currentGeneration: Generation
) {
    fun nextGen() {
        currentGeneration = currentGeneration.next(dimension)
    }

    fun draw() {
        (dimension downTo 0).forEach{y ->
            (0 .. dimension).forEach{x ->
                print(getSymbol(Cell(x, y)))
            }
            println()
        }
        println("---------------------------")
    }

    private fun getSymbol(cell: Cell) = when {
        currentGeneration.contains(cell) -> "🔴"
        else -> "⚪"
    }

    class Generation(private val cells: Set<Cell> = setOf()) {
        fun next(dimension: Int) = Generation(
            (dimension downTo 0)
                .flatMap { y -> (0..dimension).map { x -> Cell(x, y) } }
                .filter { staysAlive(it) || getsBorn(it) }.toSet()
        )

        fun contains(cell: Cell) = cells.contains(cell)
        private fun staysAlive(cell: Cell) = this.contains(cell) && listOf(2, 3).contains(cell.neighbourCells().count { this.contains(it) })
        private fun getsBorn(cell: Cell) = !this.contains(cell) && cell.neighbourCells().count { this.contains(it) } == 3
    }

    data class Cell(private val x: Int, private val y: Int) {
        fun neighbourCells() = listOf(
            Cell(x + 1, y - 1),
            Cell(x + 1, y),
            Cell(x + 1, y + 1),
            Cell(x, y - 1),
            Cell(x, y + 1),
            Cell(x - 1, y - 1),
            Cell(x - 1, y),
            Cell(x - 1, y + 1)
        )

        override fun toString(): String {
            return "($x,$y)"
        }
    }
}