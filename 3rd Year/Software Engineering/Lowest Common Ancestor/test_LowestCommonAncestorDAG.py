from LowestCommonAncestorDAG import Node, dagLCA

class TestLowestCommonAncestorDAG:
    def test_simple(self):
        root = Node(1)
        r2 = Node(2)
        r3 = Node(3)
        r4 = Node(4)
        r5 = Node(5)
        r6 = Node(6)
        root.succ = [r2,r3,r4,r5]
        r2.succ = [r4]
        r2.pred = [root]
        r3.succ = [r4, r5]
        r3.pred = [root]
        r4.succ = [r5]
        r4.pred = [r2,r3,root]
        r5.pred = [r3,r4,root]
        r6.pred = [r4]

        lca = dagLCA(root, r4, r5)

        assert lca is 3

    def test_null(self):
        root = None

        lca = dagLCA(root, 4, 5)

        assert lca is None