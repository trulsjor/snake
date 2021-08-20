package no.trulsjor.gameoflife

import no.trulsjor.gameoflife.GameOfLife.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameOfLifeTest(){

    @Test
    internal fun `first`(){
        val game = GameOfLife(dimension = 10, Generation(setOf(
            Cell(3, 3),
            Cell(4, 3),
            Cell(5, 3)),
        ))
        game.draw()
        game.nextGen()
        game.draw()
    }


    @Test
    internal fun maintest(){
        main()
    }


    @Test
    internal fun boxing(){
        (20 downTo 0).map{x ->
            (0 .. 20).map{y ->
                println(Cell(x,y))
            }
        }
    }
}