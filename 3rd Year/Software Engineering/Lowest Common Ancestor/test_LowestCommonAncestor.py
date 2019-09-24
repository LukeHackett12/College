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

    def test_p_is_lowest(self):
        root = Node(3)
        root.left = Node(5)
        root.right = Node(1)
        root.left.left = Node(6)
        root.left.right = Node(2)
        root.right.left = Node(0)
        root.right.right = Node(8)
        root.left.right.left = Node(7)
        root.left.right.right = Node(4)

        lca = LowestCommonAncestor.findLCA(root, 5, 4)

        assert lca.value is 5