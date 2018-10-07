// -------------------------------------------------------------------------
/**
 * This class contains the methods of Doubly Linked List.
 *
 * @author Luke Hackett
 * @version 01/10/18 17:35:49
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 *
 * @param <T> This is a type parameter. T is used as a class name in the
 *            definition of this class.
 *            <p>
 *            When creating a new DoublyLinkedList, T should be instantiated with an
 *            actual class name that extends the class Comparable.
 *            Such classes include String and Integer.
 *            <p>
 *            For example to create a new DoublyLinkedList class containing String data:
 *            DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *            <p>
 *            The class offers a toString() method which returns a comma-separated sting of
 *            all elements in the data structure.
 *            <p>
 *            This is a bare minimum class you would need to completely implement.
 *            You can add additional methods to support your code. Each method will need
 *            to be tested by your jUnit tests -- for simplicity in jUnit testing
 *            introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode {
        public final T data; // this field should never be updated. It gets its
        // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         *
         * @param theData  : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
            data = theData;
            prev = prevNode;
            next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int length;

    /**
     * Constructor of an empty DLL
     *
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     *
     * @return true if list is empty, and false otherwise
     * <p>
     * Worst-case asymptotic running time cost: O(1)
     * <p>
     * Justification:
     * It is linear, no matter what it is only testing one argument
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Inserts an element in the doubly linked list
     *
     * @param pos  : The integer location at which the new data should be
     *             inserted in the list. We assume that the first position in the list
     *             is 0 (zero). If pos is less than 0 then add to the head of the list.
     *             If pos is greater or equal to the size of the list then add the
     *             element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     * <p>
     * Worst-case asymptotic running time cost: O(n)
     * <p>
     * Justification:
     * Worst case it will have to iterate to the end of the list before inserting an element
     * But it only has to iterate through one time
     */
    public void insertBefore(int pos, T data) {
        if (pos <= 0 || head == null) {
            if (head == null) {
                head = new DLLNode(data, null, tail);
                tail = null;
            } else {
                if (tail == null) {
                    T temp = head.data;
                    head = new DLLNode(data, null, null);
                    tail = new DLLNode(temp, head, null);
                    head.next = tail;

                } else {
                    DLLNode prevHead = head;

                    DLLNode newNode = new DLLNode(data, null, head);
                    prevHead.prev = newNode;
                    head = newNode;
                }
            }
        } else {
            DLLNode next = head;
            int counter = 0;
            while (next.next != null && counter < pos) {
                next = next.next;
                counter++;
            }

            if (counter < pos) {
                DLLNode lastNode = getNode(length-1);
                tail = new DLLNode(data, lastNode, null);
                lastNode.next = tail;
            } else {
                insertNode(data, next, next.prev);
            }
        }
        length++;
    }

    private void insertNode(T data, DLLNode current, DLLNode prev) {
        DLLNode newNode = new DLLNode(data, null, null);
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev = newNode;
        prev.next = newNode;
    }

    /**
     * Returns the data stored at a particular position
     *
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     * <p>
     * Worst-case asymptotic running time cost: O(n)
     * <p>
     * Justification:
     * Worst case it iterates to the end of the list before returning
     */
    public T get(int pos) {
        if (pos >= 0) {
            DLLNode next = head;

            int current = 0;
            while (next != null && current < pos) {
                next = next.next;
                current++;
            }

            if (next != null) {
                return next.data;
            }
        }
        return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     *
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     * <p>
     * Worst-case asymptotic running time cost: O(n)
     * <p>
     * Justification:
     * Worst case it iterates to the end of the list before returning
     */
    public boolean deleteAt(int pos) {
        DLLNode current = head;
        int count = 0;
        while (current != null && count <= pos) {
            if (count == pos) {
                removeNode(current);
                return true;
            }
            current = current.next;
            count++;
        }

        return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     * <p>
     * Worst-case asymptotic running time cost: O(n)
     * <p>
     * Justification:
     * It iterates through the list a single time
     */
    public void reverse() {
        if (length > 1) {
            DLLNode temp = head;
            head = tail;
            tail = temp;

            DLLNode p = head;
            while (p != null) {
                temp = p.next;
                p.next = p.prev;
                p.prev = temp;
                p = p.next;
            }
        }
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     * <p>
     * Worst-case asymptotic running time cost: O(n^2)
     * <p>
     * Justification:
     * It is a nested loop with the second loop running up to n times
     */
    public void makeUnique() {
        for (int i = 0; i < length; i++) {
            DLLNode test = getNode(i);
            DLLNode current = head;
            int count = 0;
            while (current != null) {
                DLLNode next = current.next;
                if (current.data == test.data && count != i) {
                    removeNode(current);
                }
                current = next;
                count++;
            }
        }
    }

    public DLLNode getNode(int pos) {
        DLLNode next = head;
        int current = 0;
        while (next != null) {
            if (current == pos) {
                return next;
            }
            next = next.next;
            current++;
        }

        return null;
    }


    private void removeNode(DLLNode toRemove) {
        DLLNode prev = toRemove.prev;
        DLLNode next = toRemove.next;

        if (next == null && prev == null) {
            head = null;
        } else if (prev == null) {
            head = null;
            head = next;
            head.prev = null;
        } else if (next == null) {
            tail = null;
            tail = prev;
            tail.next = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }

        length--;
    }


    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     *
     * @param item : the item to push on the stack
     *             <p>
     *             Worst-case asymptotic running time cost: O(1)
     *             <p>
     *             Justification:
     *             It doesn't have to loop through any elements, it is a constant
     *             as it only has to check the first element
     */
    public void push(T item) {
        if (head == null) {
            head = new DLLNode(item, null, null);
            tail = null;
        } else {
            DLLNode lastHead = head;
            DLLNode newHead = new DLLNode(item, null, lastHead);
            lastHead.prev = newHead;
            head = newHead;
        }
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     *
     * @return the last item inserted with a push; or null when the list is empty.
     * <p>
     * Worst-case asymptotic running time cost: O(1)
     * <p>
     * Justification:
     *  Only removes head so will be constant
     */
    public T pop() {
        T toReturn = (head != null) ? head.data : null;
        head = (head != null) ? head.next : null;

        return toReturn;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     *
     * @param item : the item to be enqueued to the stack
     *             <p>
     *             Worst-case asymptotic running time cost: O(1)
     *             <p>
     *             Justification:
     *             Does only conditional checks
     */
    public void enqueue(T item) {
        if (head == null) {
            DLLNode newNode = new DLLNode(item, null, null);
            head = newNode;
            tail = null;
        } else {
            if (tail == null) {
                tail = new DLLNode(item, head, null);
                head.next = tail;
            } else {
                DLLNode lastTail = tail;
                DLLNode newTail = new DLLNode(item, lastTail, null);
                lastTail.next = newTail;
                tail = newTail;
            }
        }
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     *
     * @return the earliest item inserted with an enqueue; or null when the list is empty.
     * <p>
     * Worst-case asymptotic running time cost: O(1)
     * <p>
     * Justification:
     * It gets the element from head then changes the reference of head
     */
    public T dequeue() {
        T toReturn = (head != null) ? head.data : null;
        head = (head != null) ? head.next : null;

        return toReturn;
    }

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     * <p>
     * Worst-case asymptotic running time cost:   Theta(n)
     * <p>
     * Justification:
     * We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     * We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     * Thus, every one iteration of the for-loop will have cost Theta(1).
     * Suppose the doubly-linked list has 'n' elements.
     * The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;

        // iterate over the list, starting from the head
        for (DLLNode iter = head; iter != null; iter = iter.next) {
            if (!isFirst) {
                s.append(",");
            } else {
                isFirst = false;
            }
            s.append(iter.data.toString());
        }

        return s.toString();
    }


}



