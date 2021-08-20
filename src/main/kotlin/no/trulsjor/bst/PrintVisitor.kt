package no.trulsjor.bst

import java.util.*

class PrintVisitor : Visitor {

    val stack = Stack<Node>()
    override fun visit(node : Node){
        stack.add(node)
        //println(" $node")
    }

    override fun execute() {
        println(stack)
    }
}

interface Visitor {
    fun visit(node: Node)
    fun execute()
}
