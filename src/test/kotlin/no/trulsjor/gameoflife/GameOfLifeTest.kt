package no.trulsjor.gameoflife

import no.trulsjor.gameoflife.GameOfLife.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameOfLifeTest(){

    @Test
    internal fun `first`(){
        val game = GameOfLife(dimension = 20, Generation(setOf(
            Cell(3, 13),
            Cell(4, 13),
            Cell(5, 13),
            Cell(5, 14),
            Cell(4, 15)
        )

        ))
        (0 ..200).forEach {
            game.draw()
            game.nextGen()
        }
    }



    @Test
    internal fun `second`(){
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
}