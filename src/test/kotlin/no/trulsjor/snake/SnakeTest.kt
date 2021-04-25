package no.trulsjor.snake


import no.trulsjor.snake.Direction.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class SnakeTest {

    @Test
    internal fun `snake can start at whatever`() {
        val snake = Snake(Point(10,10))
        assertThat(snake.size()).isEqualTo(1)
        assertThat(snake.headPoint()).isEqualTo(Point(10, 10))
    }

    @Test
    internal fun `snake start at 20,20 default`() {
        val snake = Snake()
        assertThat(snake.size()).isEqualTo(1)
        assertThat(snake.headPoint()).isEqualTo(Point(20, 20))
    }

    @Test
    internal fun `snake can grow`() {
        val snake = Snake()
        snake.grow(5, LEFT)
        assertThat(snake.size()).isEqualTo(6)
    }

    @Test
    internal fun `snake can move`() {
        val snake = Snake()
        assertTrue(snake.move(RIGHT))
        assertThat(snake.headPoint()).isEqualTo(Point(21,20))
        assertTrue(snake.move(UP))
        assertThat(snake.headPoint()).isEqualTo(Point(21,21))
        assertTrue(snake.move(LEFT))
        assertThat(snake.headPoint()).isEqualTo(Point(20,21))
        assertTrue(snake.move(DOWN))
        assertThat(snake.headPoint()).isEqualTo(Point(20,20))
    }

    @Test
    internal fun `snake moving doesn't change size`() {
        val snake = Snake()
        snake.grow(9, LEFT)
        assertThat(snake.size()).isEqualTo(10)
        snake.move(RIGHT)
        assertThat(snake.size()).isEqualTo(10)

    }

    @Test
    internal fun `move yields false when snake moves into itself`() {
        val snake = Snake()
        snake.grow(10, LEFT)
        assertTrue(snake.move(UP))
        assertTrue(snake.move(LEFT))
        assertFalse(snake.move(DOWN))
    }

}