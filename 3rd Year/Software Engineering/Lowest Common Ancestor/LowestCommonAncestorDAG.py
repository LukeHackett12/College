class Node:
    def __init__(self, key):
        self.key = key
        self.pred = []
        self.succ = []

def dagLCA(root,n1,n2):
    if root is None:
        return None
    if root.key == n1.key or root.key == n2.key:
        return root.key
    if n1.key == n2.key:
        return n1.key
    lca = []
    
    for i in range(len(n1.pred)):
        for j in range(len(n2.pred)):
            if(n1.pred[i].key == n2.pred[j].key):
                lca.append(n1.pred[i].key)

    if(lca == []):
        if(n1.key > n2.key):
            lca.append(dagLCA(root,n1.pred[0],n2))
        else:
            lca.append(dagLCA(root,n1,n2.pred[0]))

    return max(lca)