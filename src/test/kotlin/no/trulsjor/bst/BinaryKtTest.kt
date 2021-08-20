package no.trulsjor.bst//package no.trulsjor.snake
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//
//data class QA(val question: Int, val answer:Pair<Int,Int>)
//val qa = listOf(
//    QA(21,9 to 12),
//    QA(21 , 9 to 12),
//    QA(23 , 9 to 14),
//    QA(26 , 9 to 17),
//    QA(28 , 9 to 19),
//    QA(29 , 12 to 17),
//    QA(31 , 12 to 19),
//    QA(32 , 9 to 23),
//    QA(33 , 14 to 19),
//    QA(35 , 12 to 23),
//    QA(36 , 17 to 19),
//    QA(37 , 14 to 23),
//    QA(40 , 17 to 23),
//    QA(42 , 19 to 23),
//    QA(59 , 9 to 50),
//    QA(62 , 12 to 50),
//    QA(63 , 9 to 54),
//    QA(64 , 14 to 50),
//    QA(66 , 12 to 54),
//    QA(67 , 17 to 50),
//    QA(68 , 14 to 54),
//    QA(69 , 19 to 50),
//    QA(71 , 17 to 54),
//    QA(73 , 19 to 54),
//    QA(76 , 9 to 67),
//    QA(77 , 23 to 54),
//    QA(79 , 12 to 67),
//    QA(81 , 9 to 72),
//    QA(84 , 12 to 72),
//    QA(85 , 9 to 76),
//    QA(86 , 14 to 72),
//    QA(88 , 12 to 76),
//    QA(89 , 17 to 72),
//    QA(90 , 14 to 76),
//    QA(91 , 19 to 72),
//    QA(93 , 17 to 76),
//    QA(95 , 19 to 76),
//    QA(99 , 23 to 76),
//    QA(104 , 50 to 54),
//    QA(117 , 50 to 67),
//    QA(121 , 54 to 67),
//    QA(122 , 50 to 72),
//    QA(126 , 50 to 76),
//    QA(130 , 54 to 76),
//    QA(139 , 67 to 72),
//    QA(143 , 67 to 76),
//    QA(148 , 72 to 76))
//
//internal class BinaryKtTest {
//
//    private val aBinaryTree =
//        50.node.children(
//            left = 17.node.children(
//                left = 12.node.children(
//                    left = 9.node.children(
//                        left = 6.node.children(left = 4.node)),
//                    right = 14.node),
//                right = 23.node.children(left = 19.node)
//            ),
//            right = 72.node.children(
//                left = 54.node.children(right = 67.node),
//                right = 76.node
//            )
//        )
//    @Test
//    internal fun `solve all permutations`() {
//        val binaryTreePairSolver = BinaryTreePairSolver(aBinaryTree)
//        qa.forEach { assertThat(binaryTreePairSolver.solve(it.question)).isEqualTo(it.answer) }
//    }
//
//
//    @Test
//    internal fun `solve all prem`() {
//        val binaryTreePairSolver = BinaryTreePairSolver(aBinaryTree)
//       println(binaryTreePairSolver.sorted())
//    }
//}
//
