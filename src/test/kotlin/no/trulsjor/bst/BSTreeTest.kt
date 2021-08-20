package no.trulsjor.bst

import org.junit.jupiter.api.Test

internal class BSTreeTest{


    @Test
    fun test(){
        val tree = BSTree(Node(50))
        tree.add(17)
        tree.add(72)
        tree.add(12)
        tree.add(23)
        tree.add(54)
        tree.add(76)
        tree.add(9)
        tree.add(14)
        tree.add(19)
        tree.add(67)
        tree.printTree()
    }

    @Test
    fun tes2t(){
        val tree = BSTree(Node(50))
        tree.add(17)
        tree.add(72)
        tree.add(12)
        tree.add(23)
        tree.add(54)
        tree.add(76)
        tree.add(9)
        tree.add(14)
        tree.add(19)
        tree.add(67)
     //   tree.printTree()
        tree.iter()
    }
}