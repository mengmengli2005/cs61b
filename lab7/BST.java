public class BST<Key extends Comparable> {

    private Key key;
    private BST right;
    private BST left;

    public BST(Key key, BST right, BST left) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
    public BST(Key key) {
        this.key = key;
    }

    public BST search(BST T, Key k) {
        if (T == null) return null;
        if (k.equals(T.key)) return T;
        else if (k.compareTo(T.key) < 0) return search(T.left, k);
        else return search(T.right, k);
    }

    public BST insert(BST T, Key k) {
        if (T == null) return new BST(k);
        if (search(T, k) != null) return T;
        else {
            BST cmp = new BST(k);
            cmp.left = T.left;
            T.left = cmp;
            return T;
        }
    }
}
