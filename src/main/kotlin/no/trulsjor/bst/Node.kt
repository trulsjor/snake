package no.trulsjor.bst

import no.trulsjor.bst.Node.EmptyNode.EMPTY

class BSTree(private val root: Node) {
    private val visitor = PrintVisitor()
    private val iter = BSTIterator(root)

    fun add(value: Int) = root.add(Node(value))
    fun printTree() {
        inOrder(root)
        visitor.execute()
    }

    fun iter(){
        while(iter.hasNext()){
            println(iter.next())
        }
    }

    private fun inOrder(node: Node) {
        if (node != EMPTY) {
            inOrder(node.left)
            node.accept(visitor)
            inOrder(node.right)
        }
    }
}
class Node(private val number: Int) {
    var parent = EMPTY
    var left = EMPTY
    var right = EMPTY

    fun accept(visitor: Visitor){
        visitor.visit(this)
    }

    fun first(): Node = if (left == EMPTY) this else left.first()
    fun last(): Node =if (right == EMPTY) this else right.last()

    fun add(node: Node) {
        when {
            this > node -> when (this.left) {
                EMPTY -> {
                    this.left = node
                    node.parent = this
                }
                else -> this.left.add(node)
            }
            this < node -> when (this.right) {
                EMPTY -> {
                    this.right = node
                    node.parent = this
                }
                else -> this.right.add(node)
            }
            else -> return
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Node) return false
        return other.number == this.number
    }
    override fun toString() = " $number"
    operator fun compareTo(node: Node) = this.number.compareTo(node.number)
    override fun hashCode(): Int {
        var result = number
        result = 31 * result + left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    companion object EmptyNode {
        val EMPTY = Node(-1)

    }
}