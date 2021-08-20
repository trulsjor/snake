package no.trulsjor.bst//package no.trulsjor.snake
//
//import no.trulsjor.bst.Node.EmptyNode.EMPTY
//
//class BinaryTreePairSolver(private val tree: Node) {
//
//
//    fun solve(k: Int): Pair<Int, Int> {
//        val up = tree.iterator()
//        while (up.hasNext()) {
//            val x = up.next()
//            val down = tree.iterator()
//            while (down.hasPrevious()) {
//                val y = down.previous()
//                if (x.number + y.number == k && x != y) {
//                    return x.number to y.number
//                }
//            }
//        }
//        return EMPTY.number to EMPTY.number
//    }
//
//    fun sorted(): MutableList<Int> {
//        val sorted = mutableListOf<Int>()
//        val it = tree.iterator()
//        while (it.hasNext()){
//            sorted.add(it.next().number)
//        }
//        return sorted
//    }
//}
//
//
//class Node(internal val number: Int) {
//    var parent = EMPTY
//    var left = EMPTY
//    var right = EMPTY
//
//    override fun toString() = "" + number
//    fun iterator() = NodeIterator(this)
//    fun isLeaf() = right == EMPTY && left == EMPTY
//    fun isLeftChild() = parent.left == this
//    fun isRightChild() = parent.right == this
//    fun isRightGrandChild() = parent.parent.right == parent
//    fun isLeftGrandChild() = parent.parent.left == parent
//    fun hasSibling() = parent.left != EMPTY && parent.right != EMPTY
//
//    fun children(left: Node = EMPTY, right: Node = EMPTY): Node {
//        this.left = left
//        this.left.parent = this
//        this.right = right
//        this.right.parent = this
//        return this
//    }
//
//    companion object EmptyNode {
//        val EMPTY = Node(-1)
//
//    }
//
//    class NodeIterator(node: Node) {
//        var low = findFirst(node)
//        var high = findLast(node)
//        fun hasNext() = low != EMPTY
//        fun hasPrevious() = high != EMPTY
//
//        fun previous(): Node {
//            val ret = high
//            high = if (high.isLeaf()) {
//                when {
//                    high.isRightGrandChild() && !high.hasSibling() -> high.parent.parent
//                    low.isLeftChild() && low.isRightGrandChild() -> low.parent.parent
//                    high.isRightChild() -> high.parent
//                    else -> EMPTY
//                }
//            } else {
//                when {
//                    high.isLeftChild() && high.isRightGrandChild() -> high.parent.parent
//                    high.left == EMPTY -> high.parent
//                    else -> findLast(high.left)
//                }
//            }
//            return ret
//        }
//
//        fun next(): Node {
//            val ret = low
//            low = if (low.isLeaf()) {
//                when {
//                    low.isLeftGrandChild() && !low.hasSibling() -> low.parent.parent
//                    low.isRightChild() && low.isLeftGrandChild() -> low.parent.parent
//                    low.isLeftChild() -> low.parent
//                    else -> EMPTY
//                }
//            } else {
//                when {
//                    low.isRightChild() && low.isLeftGrandChild() -> low.parent.parent
//                    low.right == EMPTY -> low.parent
//                    else -> findFirst(low.right)
//                }
//            }
//            return ret
//        }
//
//        private fun findLast(node: Node): Node = if (node.right != EMPTY) findLast(node.right) else node
//        private fun findFirst(node: Node): Node = if (node.left != EMPTY) findFirst(node.left) else node
//    }
//}
//
//val Int.node get() = Node(this)
//
