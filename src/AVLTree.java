

class Node {
    Object key;
    int height, a;
    Node left, right;

    Node(Object d, int ai) {
        key = d;
        height = 1;
        a = ai;
    }
}


public class AVLTree {
    Node root = null;

    //agacin yuksekligi
    int height(Node n) {
        if (n != null) {
            return n.height;
        }
        return 0;
    }

    // Node n'den saga cevirme
    Node rotateMyRight(Node n) {
        Node n1 = n.left;
        Node n2 = n1.right;

        n1.right = n;
        n.left = n2;

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        n1.height = Math.max(height(n1.left), height(n1.right)) + 1;

        return n1;
    }

    //Node n'den sola cevirme
    Node rotateMyleft(Node n) {
        Node n1 = n.right;
        Node n2 = n1.left;

        n1.left = n;
        n.right = n2;

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        n1.height = Math.max(height(n1.left), height(n1.right)) + 1;

        return n1;
    }

    
    int getBalance(Node n) {
        if (n != null) {
            return height(n.left) - height(n.right);
        } else {
            return 0;
        }
    }

    
    void insert(Object key, int a) {
        root = insert(root, key, a);
    }

   
    Node insert(Node n, Object key, int a) {
        if (n == null) {
            return (new Node(key, a));
        }

        if (key instanceof java.lang.Integer) {
            int ikey = (int) key;
            int nkey = (int) n.key;

            if (ikey < nkey) {
                n.left = insert(n.left, ikey, a);
            } else if (ikey > nkey) {
                n.right = insert(n.right, ikey, a);
            } else {
                return n;
            }
            n.height = 1 + Math.max(height(n.left), height(n.right));

            int leftkey = null == n.left ?  -1 : (int) n.left.key;
            int rightkey = null == n.right ?  -1 : (int) n.right.key;

            int balance = getBalance(n);
            if (balance > 1 && ikey < leftkey) {
                return rotateMyRight(n);
            }
            if (balance < -1 && ikey > rightkey) {
                return rotateMyleft(n);
            }
            if (balance > 1 && ikey > leftkey) {
                n.left = rotateMyleft(n.left);
                return rotateMyRight(n);
            }

            if (balance < -1 && ikey < rightkey) {
                n.right = rotateMyRight(n.right);
                return rotateMyleft(n);
            }
        } else if (key instanceof java.lang.String) {
            String skey = (String) key;
            String nkey = (String) n.key;

            if (skey.compareTo(nkey) < 0)
                n.left = insert(n.left, skey, a);
            else if (skey.compareTo(nkey) > 0)
                n.right = insert(n.right, skey, a);
            else
                return n;

            n.height = 1 + Math.max(height(n.left),
                    height(n.right));

            int balance = getBalance(n);
            
            if (balance > 1 && skey.compareTo((String) n.left.key) < 0)
                return rotateMyRight(n);

            
            if (balance < -1 && skey.compareTo((String) n.right.key) > 0)
                return rotateMyleft(n);

            
            if (balance > 1 && skey.compareTo((String) n.left.key) > 0) {
                n.left = rotateMyleft(n.left);
                return rotateMyRight(n);
            }

            
            if (balance < -1 && skey.compareTo((String) n.right.key) < 0) {
                n.right = rotateMyRight(n.right);
                return rotateMyleft(n);
            }
        }
        return n;
    }

   
    Node minValueNode(Node n) {
        Node t;
        t = n;
        while (t.left != null) {
            t = t.left;
        }

        return t;
    }

   
    void deleteNode(Object key) {
        deleteNode(root, key);
    }

    
    Node deleteNode(Node root, Object key) {
        if (root == null) {
            return null;
        }

        if (key instanceof java.lang.Integer) {
            int ikey = (int) key;
            int rkey = (int) root.key;
            if (ikey < rkey) {
                root.left = deleteNode(root.left, key);
            } else if (ikey > rkey) {
                root.right = deleteNode(root.right, key);
            } else {
                if ((root.left == null) || (root.right == null)) {
                    Node t;
                    if (null == root.left) {
                        t = root.right;
                    } else {
                        t = root.left;
                    }
                    root = t;
                } else {
                    Node t = minValueNode(root.right);
                    root.key = t.key;
                    root.right = deleteNode(root.right, t.key);
                }
            }
        } else if (key instanceof java.lang.String) {
            String skey = (String) key;
            String nkey = (String) root.key;

            if (skey.compareTo(nkey) < 0) {
                root.left = deleteNode(root.left, key);
            } else if (skey.compareTo(nkey) > 0) {
                root.right = deleteNode(root.right, skey);
            } else {

                if ((root.left == null) || (root.right == null)) {
                    Node temp;
                    if (null == root.left)
                        temp = root.right;
                    else
                        temp = root.left;

                    root = temp;
                } else {
                    Node temp = minValueNode(root.right);
                    root.key = temp.key;
                    root.right = deleteNode(root.right, temp.key);
                }
            }
        }

        if (root == null) {
            return null;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rotateMyRight(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateMyleft(root.left);
            return rotateMyRight(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateMyleft(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateMyRight(root.right);
            return rotateMyleft(root);
        }

        return root;
    }

    
    public Node getNodeByKey(Object key) {
        return getNodeByKey(key, root);
    }

   
    public Node getNodeByKey(Object key, Node n) {
        if (null == n) {
            return null;
        }

        if (key instanceof java.lang.Integer) {
            int ikey = (int) key;
            int nkey = (int) n.key;

            if (ikey < nkey) {
                return getNodeByKey(key, n.left);
            }

            if (ikey > nkey) {
                return getNodeByKey(key, n.right);
            }
        } else if (key instanceof java.lang.String) {
            String skey = (String) key;
            String nkey = (String) n.key;

            if (skey.compareTo(nkey) < 0) {
                return getNodeByKey(skey, n.left);
            }

            if (skey.compareTo(nkey) > 0) {
                return getNodeByKey(skey, n.right);
            }
        }

        return n;
    }
}
