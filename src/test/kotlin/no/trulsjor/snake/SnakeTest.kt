package no.trulsjor.snake


import no.trulsjor.snake.Direction.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class SnakeTest {

    @Test
    internal fun `snake of length 4 cannot collide into its own tail moving in 2x2 grid `() {
        val snake = Snake(Point(0,0), 4)
        assertTrue(snake.move(RIGHT))
        assertTrue(snake.move(UP))
        assertTrue(snake.move(LEFT))
        assertTrue(snake.move(DOWN))
    }

    @Test
    internal fun `move yields false when snake moves into itself`() {
        val snake = Snake(Point(0,0), 5)
        assertTrue(snake.move(RIGHT))
        assertTrue(snake.move(UP))
        assertTrue(snake.move(LEFT))
        assertFalse(snake.move(DOWN))
    }

    @Test
    internal fun `beware of the apple`() {
        val snake = Snake(Point(0,0), 4, apples = mutableListOf(Point(1, 1)))
        assertTrue(snake.move(RIGHT))
        assertTrue(snake.move(UP))
        assertTrue(snake.move(LEFT))
        assertFalse(snake.move(DOWN))
    }

    @Test
    internal fun `snake on the walls`() {
        val snake = Snake(dimension = 2)
        assertTrue(snake.move(RIGHT))
        assertFalse(snake.move(RIGHT))
    }


}