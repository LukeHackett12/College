def findLCA(root, n1, n2):
    if root is None:
        return None;

    if root.value == n1 or root.value == n2:
        return root

    left_lca = findLCA(root.left, n1, n2)
    right_lca = findLCA(root.right, n1, n2)

    if left_lca and right_lca:
        return root

    return left_lca if left_lca is not None else right_lca
