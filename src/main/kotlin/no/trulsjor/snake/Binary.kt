package no.trulsjor.snake

import no.trulsjor.snake.Node.EmptyNode.EMPTY


fun main() {
    val tree = binarySearchTree()
    println(BinarySearch(tree).solve(77))
}

private fun binarySearchTree(): Node {
    return 50.node.children(
        left = 17.node.children(
            left = 12.node.children(left = 9.node, right = 14.node),
            right = 23.node.children(left = 19.node)
        ),
        right = 72.node.children(
            left = 54.node.children(right = 67.node),
            right = 76.node
        )
    )
}

class BinarySearch(private val tree: Node) {
    fun solve(k: Int): Pair<Node, Node> {
        val up = tree.iterator()
        while (up.hasNext()) {
            val x = up.next()
            val down = tree.iterator()
            while (down.hasPrevious()) {
                val y = down.previous()
                if (x.number + y.number == k) {
                    return x to y
                }
            }
        }
        return EMPTY to EMPTY
    }
}


class Node(internal val number: Int) {
    var parent = EMPTY
    var left = EMPTY
    var right = EMPTY

    override fun toString() = "" + number
    fun iterator() = NodeIterator(this)
    fun isLeaf() = right == EMPTY && left == EMPTY
    fun isLeftChild() = parent.left == this
    fun isRightChild() = parent.right == this
    fun isRightGrandChild() = parent.parent.right == parent
    fun isLeftGrandChild() = parent.parent.left == parent
    fun hasSibling() = parent.left != EMPTY && parent.right != EMPTY

    fun children(left: Node = EMPTY, right: Node = EMPTY): Node {
        this.left = left
        this.left.parent = this
        this.right = right
        this.right.parent = this
        return this
    }

    companion object EmptyNode {
        val EMPTY = Node(-1)

    }

    class NodeIterator(node: Node) {
        var low = findFirst(node)
        var high = findLast(node)
        fun hasNext() = low != EMPTY
        fun hasPrevious() = high != EMPTY

        fun previous(): Node {
            val ret = high
            high = if (high.isLeaf()) {
                when {
                    high.isRightGrandChild() && !high.hasSibling() -> high.parent.parent
                    low.isLeftChild() && low.isRightGrandChild() -> low.parent.parent
                    high.isRightChild() -> high.parent
                    else -> EMPTY
                }
            } else {
                when {
                    high.isLeftChild() && high.isRightGrandChild() -> high.parent.parent
                    high.left == EMPTY -> high.parent
                    else -> findLast(high.left)
                }
            }
            return ret
        }

        fun next(): Node {
            val ret = low
            low = if (low.isLeaf()) {
                when {
                    low.isLeftGrandChild() && !low.hasSibling() -> low.parent.parent
                    low.isRightChild() && low.isLeftGrandChild() -> low.parent.parent
                    low.isLeftChild() -> low.parent
                    else -> EMPTY
                }
            } else {
                when {
                    low.isRightChild() && low.isLeftGrandChild() -> low.parent.parent
                    low.right == EMPTY -> low.parent
                    else -> findFirst(low.right)
                }
            }
            return ret
        }

        private fun findLast(node: Node): Node = if (node.right != EMPTY) findLast(node.right) else node
        private fun findFirst(node: Node): Node = if (node.left != EMPTY) findFirst(node.left) else node
    }
}

val Int.node get() = Node(this)

