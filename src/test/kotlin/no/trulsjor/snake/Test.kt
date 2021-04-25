package no.trulsjor.snake


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Test {


    @Test
    internal fun `snake start at 20,20`() {
        val snake = Snake()
        assertThat(snake.size()).isEqualTo(1)
        assertThat(snake.headPoint()).isEqualTo(Point(20, 20))
    }

    @Test
    internal fun `snake can grow`() {
        val snake = Snake()
        snake.grow(5,Direction.LEFT)
        assertThat(snake.size()).isEqualTo(6)
    }


    @Test
    internal fun `snake can move`() {
        val snake = Snake()
        snake.move(Direction.RIGHT)
        assertThat(snake.headPoint()).isEqualTo(Point(21,20))
        snake.move(Direction.UP)
        assertThat(snake.headPoint()).isEqualTo(Point(21,21))
        snake.move(Direction.LEFT)
        assertThat(snake.headPoint()).isEqualTo(Point(20,21))
        snake.move(Direction.DOWN)
        assertThat(snake.headPoint()).isEqualTo(Point(20,20))
    }

}