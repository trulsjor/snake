package no.trulsjor.snake

import no.trulsjor.snake.Node.EmptyNode.EMPTY

class Node(internal val number: Int) {
    var parent = EMPTY
    var left = EMPTY
    var right = EMPTY


    infix fun left(child: Node) {
        left = child
        child.parent = this
    }

    infix fun right(child: Node) {
        right = child
        child.parent = this
    }

    override fun toString(): String {
        return "" + number
    }

    fun iterator(): NodeIterator {
        return NodeIterator(this)
    }

    fun isLeaf() = right == EMPTY && left == EMPTY
    fun isLeftChild() = parent.left == this
    fun isRightChild() = parent.right == this
    fun isRightGrandChild() = parent.parent.right == parent
    fun isLeftGrandChild() = parent.parent.left == parent
    fun hasSibling() = parent.left != EMPTY && parent.right != EMPTY

    companion object EmptyNode {
        val EMPTY = Node(-1)
    }
}

fun main() {
    val root = createBetterTree()
    println(solve(63, root))
}


private fun createBetterTree(): Node {
    val root = Node(50)
    val a = Node(17)
    val b = Node(72)
    root left a
    root right b

    val c = Node(12)
    val f = Node(23)
    a left c
    a right f

    val d = Node(9)
    val e = Node(14)
    c left d
    c right e

    f left Node(19)

    val h = Node(54)
    b left h
    h right Node(67)
    b right Node(76)
    return root
}

private fun createSimpleTree(): Node {
    val root = Node(8)
    val three = Node(3)
    val six = Node(6)
    root left three
    root right Node(10)
    three left Node(1)
    three right six
    six left Node(4)
    return root
}

fun solve(k: Int, tree: Node): Pair<Node, Node> {
    val upwards = tree.iterator()
    while (upwards.hasNext()) {
        val x = upwards.next()
        val downwards = tree.iterator()
        while (downwards.hasPrevious()) {
            val y = downwards.previous()
            if (x.number + y.number == k) {
                return x to y
            }
        }
    }
    return EMPTY to EMPTY
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
