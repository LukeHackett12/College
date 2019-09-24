from binarytree import Node

import LowestCommonAncestor


class TestLowestCommonAncestor:
    def test_simple(self):
        root = Node(1)
        root.left = Node(2)
        root.right = Node(3)
        root.left.left = Node(4)
        root.left.right = Node(5)
        root.right.left = Node(6)
        root.right.right = Node(7)

        lca = LowestCommonAncestor.findLCA(root, 4, 5)

        assert lca.value is 2

    def test_null(self):
        root = None

        lca = LowestCommonAncestor.findLCA(root, 4, 5)

        assert lca is None
