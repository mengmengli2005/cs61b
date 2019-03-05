public class ArrayDeque<itemsType> {
    public itemsType[] items;
    public int nextFirst;
    public int nextLast;
    public int size;

    public ArrayDeque() {
        items = (itemsType[]) new Object[8]; // 很重要！！！总是忘记，为什么来着？
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public void addFirst(itemsType x) {
        if (nextFirst == nextLast) {
            itemsType[] newA = (itemsType[]) new Object[this.items.length * 2];
            this.copy(this.items, newA);
            newA[newA.length -1] = x;
            nextFirst = newA.length - 2;
            nextLast = size;
            items = newA;
        } else {
            items[nextFirst] = x;
            if (nextFirst == 0) {
                nextFirst = this.items.length - 1;
            } else {nextFirst = nextFirst - 1;}
        }
        size = size + 1;
    }

    public void addLast(itemsType x) {
        if (nextFirst == nextLast) {
            itemsType[] newA = (itemsType[]) new Object[this.items.length * 2];
            this.copy(this.items, newA);
            newA[size] = x;
            nextFirst = newA.length - 1;
            nextLast = size + 1;
            items = newA;
        } else {
            items[nextLast] = x;
            if (nextLast == items.length - 1) {
                nextLast = 0;
            } else {nextLast = nextLast + 1;}
        }
        size = size + 1;
    }

    public itemsType removeFirst() {
        int len = this.items.length;
        nextFirst = (nextFirst + 1) % len;
        size = size - 1;
        itemsType RR = this.items[nextFirst];
        this.cut(0.25);
        return RR;
    }

    public itemsType removeLast() {
        int len = this.items.length;
        nextLast = (nextLast - 1 + len) % len;
        size = size - 1;
        itemsType RR = this.items[nextLast];
        this.cut(0.25);
        return RR;
    }

    private void cut(double rate) {
        if (rate < 0 || rate > 1) {
            System.out.println("Error: rate should be a positive number smaller than 1");
            return;
        }
        int len = this.items.length;
        if ((size / len) < rate) {
            itemsType[] newA = (itemsType[]) new Object[len / 2];
            this.copy(this.items, newA);
            nextFirst = newA.length - 1;
            nextLast = size;
            items = newA;
        }
    }

    public void printDeque() {
        int len = this.items.length;
        int index = (nextFirst + 1) % len;
        String str = "";
        for (int i = 0; i < size; i ++) {
            str = str + this.items[index];
            str = str + " ";
            index = (index + 1) % len;
        }
        System.out.println("The array includes the following elements: " + str);
    }

    public int getSize() {
        return size;
    }

    public itemsType get(int pos) {
        return this.items[pos];
    }

    private void copy(itemsType[] arrOld, itemsType[] arrNew) {
        int len = this.items.length;
        int indexOld = (nextFirst + 1) % len;
        int indexNew = 0;
        for (int i = 0; i < size; i ++) {
            arrNew[indexNew] = arrOld[indexOld % len];
            indexNew ++;
            indexOld ++;
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for (int i = 0; i < 10; i ++) {
            A.addFirst(i);
        }
        A.printDeque();
        A.addLast(22);
        A.addLast(33);
        A.addLast(44);
        A.addLast(55);
        A.addLast(66);
        A.addLast(77);
        A.addLast(88);
        A.addLast(99);
        System.out.println("A.size before remove is " + A.getSize());
        System.out.println("The 10th element of A.items before remove is: " + A.get(10));
        A.removeFirst();
        A.removeLast();
    }
}
