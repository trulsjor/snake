package no.trulsjor.bst

import no.trulsjor.bst.Node.EmptyNode.EMPTY

class BSTIterator(val root: Node) : Iterator<Node> {
    var current = root.first()
    override fun hasNext() = current < root.last()
    override fun next(): Node {
        current = getNext()
        return current
    }

    private fun x(current: Node, candidate: Node) {
    }

    private fun getNext(): Node {
        if (current.right != EMPTY) return current.right.first()
            
        var tmp = root
        var lastLeft = EMPTY
        var parent = EMPTY
        while (tmp != EMPTY) {
            if (current < tmp) {
                lastLeft = tmp
                tmp = tmp.left
            } else if (current > tmp) {
                tmp = tmp.right
            } else {
                return if (lastLeft == EMPTY) parent else lastLeft
            }
            parent = tmp
        }
        return EMPTY
    }
}