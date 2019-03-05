public interface Deque<itemsType> {

    void addFirst(itemsType item);
    void addLast(itemsType item);
    boolean isEmpty();
    int size();
    void printDeque();
    itemsType removeFirst();
    itemsType removeLast();
    itemsType get(int index);

}
