public class LinkedListDeque<itemsType> {
    /* identify private class IntNode. */
    private class IntNode<itemsType> {
        IntNode pre;
        itemsType items;
        IntNode next;
        public IntNode(IntNode p, itemsType x, IntNode n) {
            pre = p;
            items = x;
            next = n;
        }
    }
    /* identify sentinel and size. */
    public IntNode sentinel;
    public int size;
    /* construct function 1 */
    public LinkedListDeque() {
        sentinel = new IntNode(null, -12345, null);
        size = 0;
    }
    /* construct function 2 */
    public LinkedListDeque(itemsType x) {
        sentinel = new IntNode(null, -12345, null);
        IntNode<itemsType> firstHead = new IntNode(sentinel, x, sentinel);
        sentinel.next = firstHead;
        sentinel.pre = firstHead;
        size = 1;
    }

    public boolean isEmpty() {
        if (this.sentinel.next == null) {
            return true;
        }
        return false;
    }

    public void addFirst(itemsType x) {
        IntNode<itemsType> preHead = sentinel.next;
        IntNode<itemsType> newHead = new IntNode(sentinel, x, preHead);
        preHead.pre = newHead;
        sentinel.next = newHead;
        size = size + 1;
    }

    public void addLast(itemsType x) {
        IntNode<itemsType> head = this.sentinel.next;
        IntNode<itemsType> tail = this.sentinel.pre;
        IntNode<itemsType> newTail = new IntNode(tail, x, head);
        tail.next = newTail;
        sentinel.pre = newTail;
        size = size + 1;
    }

    public itemsType get(int x) {
        if (this.isEmpty()) {
            System.out.println("Error: the linked list is empty.");
            return null;
        }
        int i = 0;
        IntNode p = sentinel.next;
        while (i < x) {
            p = p.next;
            i ++;
        }
        itemsType item = (itemsType) p.items;
        return item;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("Error: the LinkedList is empty.");
            return;
        }
            IntNode p = sentinel.next;
            String str = "";
            for (int i = 0; i < size; i ++) {
                str = str + p.items;
                p = p.next;
            }
            System.out.println(str);
    }

    public itemsType removeFirst() {
        if (this.isEmpty()) {
            System.out.println("Error: the LinkedList is empty.");
            return null;
        }
            itemsType FF = (itemsType) sentinel.next.items;
            IntNode<itemsType> newHead = sentinel.next.next;
            sentinel.next = newHead;
            newHead.pre = sentinel;
            size = size - 1;
            return FF;
    }

    public itemsType removeLast() {
        if (this.isEmpty()) {
            System.out.println("Error: the LinkedList is empty.");
            return null;
        }
        itemsType LL = (itemsType) sentinel.pre.items;
        IntNode<itemsType> newLast = sentinel.pre.pre;
        sentinel.pre = newLast;
        newLast.next = sentinel;
        size = size - 1;
        return LL;
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> A = new LinkedListDeque(0); /* define link A */
//        System.out.println("Weather if the link A is empty: " + A.isEmpty());
//        for (int i = 1; i < 10; i ++) {
//            A.addLast(i);
//        }
//        System.out.println("A including " + A.size + "s elements: ");
//        A.printDeque(); /* print link A */
//        A.addLast(10);
//        System.out.println("add 10 to the last of A: ");
//        A.printDeque();
//        A.removeFirst();
//        System.out.println("remove the first item of A: ");
//        A.printDeque();
//        A.removeLast();
//        System.out.println("remove the last item of A: ");
//        A.printDeque();
//    }
}