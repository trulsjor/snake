package no.trulsjor.gameoflife

import no.trulsjor.gameoflife.GameOfLife.Cell

fun main() {

    val dimension = 10
    val game = GameOfLife(dimension, GameOfLife.Generation(setOf( Cell(3, 3),
        Cell(4, 3),
        Cell(5, 3))))
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
    val history: MutableList<Generation> = mutableListOf()

    fun nextGen() {
        history.add(currentGeneration)
        currentGeneration = currentGeneration.next(dimension)
    }

    fun draw() {
        for (y in dimension downTo -1) {
            for (x in -1..dimension) {
                print(getSymbol(Cell(x, y)))
            }
            println()
        }
        println("---------------------------")
    }

    private fun getSymbol(cell: Cell): String {
        if (currentGeneration.contains(cell)) return "🔴"
        return "⚪"
    }

    class Generation(val cells: Set<Cell> = setOf<Cell>()) {
        fun contains(cell: Cell) = cells.contains(cell)

        fun next(dimension: Int): Generation {
            val nextGen = mutableSetOf<Cell>()
            for (y in dimension downTo 0) {
                for (x in 0..dimension) {
                    val cell = Cell(x, y)
                    when {
                        cells.contains(cell) && cell.hasTwoOrThreeNeighbours(cells) -> {
                            nextGen.add(cell)
                        }
                        !cells.contains(cell) && cell.hasThreeNeighbours(cells) -> {
                            nextGen.add(cell)
                        }
                    }
                }
            }
            return Generation(nextGen)
        }
    }

    data class Cell(private val x: Int, private val y: Int) {
        override fun equals(other: Any?): Boolean = (other is Cell) && other.x == this.x && other.y == this.y
        private fun neighbourCells() = listOf(Cell(x + 1, y - 1), Cell(x + 1, y), Cell(x + 1, y + 1), Cell(x, y - 1), Cell(x, y + 1), Cell(x - 1, y - 1), Cell(x - 1, y), Cell(x - 1, y + 1))
        fun hasTwoOrThreeNeighbours(cells: Set<Cell>) = listOf(2, 3).contains(neighbourCells().count { cells.contains(it) })
        fun hasThreeNeighbours(cells: Set<Cell>) = neighbourCells().count { cells.contains(it) } == 3
    }
}