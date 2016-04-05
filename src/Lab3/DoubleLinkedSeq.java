package Lab3;
/**
 * CSC103 DoubleLinkedSeq
 * 3/16/2016
 *
 * DoubleLinkedSeq class
 *
 * @author Timothy Haskins
 */
public class DoubleLinkedSeq implements Cloneable{
    private Node head, cursor = null, tail = null;

    /**
     * Initialize an empty sequence.
     * @param - none
     * <dt><b>Postcondition:</b><dd>
     *   This sequence is empty.
     **/
    DoubleLinkedSeq(){
        cursor = null;
        head = new Node();
        head.setLink(null);
        tail = head;
    }

    DoubleLinkedSeq(double list[]){
        head = new Node(list[0], null);
        Node temp = head;
        for(int i = 1; i < list.length; ++i){
            temp.setLink(new Node(list[i], temp.getLink()));
            temp = temp.getLink();
        }
        cursor = tail = temp;
    }

    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the
     * getCurrent</CODE> method.
     * @param - none
     * @return
     *   true (there is a current element) or false (there is no current element at the moment)
     **/
    boolean isCurrent(){
        return (cursor != tail && cursor.getLink() == null);
    }

    void setCurrent(int distance){
        Node temp = head;
        for(int i = 0; i < distance; ++i) temp = temp.getLink();
        cursor = temp;
    }

    Node retrieveElement(double elem){
        Node temp = head;
        while(temp.getData() != elem){
            if (temp.getLink() == null) return null;
            temp = temp.getLink();
        }
        return temp;
    }

    void removeFront(){
        if(head.getLink() != null) head = head.getLink();
        cursor = head;
        reassociateTail();
    }

    void addEnd(double elem){
        Node temp = new Node(elem, null);
        reassociateTail();
        tail.setLink(temp);
        tail = temp;
    }

    void currentLast(){
        reassociateTail();
        cursor = tail;
    }

    /**
     * Accessor method to get the current element of this sequence.
     * @param - none
     * <dt><b>Precondition:</b><dd>
     *   isCurrent()</CODE> returns true.
     * @return
     *   the current capacity of this sequence
     * @exception IllegalStateException
     *   Indicates that there is no current element, so
     *   getCurrent</CODE> may not be called.
     **/
    double getCurrent(){
        return cursor.getData();
    }

    /**
     * Determine the number of elements in this sequence.
     * @param - none
     * @return
     *   the number of elements in this sequence
     **/
    int size(){
        int count = 1;
        cursor = head;
        while(cursor.getLink() != null){
            count++;
            cursor = cursor.getLink();
        }
        return count;
    }

    /**
     * Add a new element to this sequence, after the current element.
     * @param element</CODE>
     *   the new element that is being added
     * <dt><b>Postcondition:</b><dd>
     *   A new copy of the element has been added to this sequence. If there was
     *   a current element, then the new element is placed after the current
     *   element. If there was no current element, then the new element is placed
     *   at the end of the sequence. In all cases, the new element becomes the
     *   new current element of this sequence.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for a new node.
     **/
    void addAfter(double element) throws OutOfMemoryError{
        if(!isCurrent()){
            reassociateTail();

            if(head == tail){
                if(head.getData() == 0) head.setData(element);
                else{
                    tail = new Node(element, null);
                    head.setLink(tail);
                    cursor = tail;
                }
            }else{
                cursor = new Node(element, null);
                tail.setLink(cursor);
                tail = cursor;
            }
        }else cursor.setLink(new Node(element, cursor.getLink()));
        reassociateTail();
    }

    /**
     * Place the contents of another sequence at the end of this sequence.
     * @param addend</CODE>
     *   a sequence whose contents will be placed at the end of this sequence
     * <dt><b>Precondition:</b><dd>
     *   The parameter, addend</CODE>, is not null.
     * <dt><b>Postcondition:</b><dd>
     *   The elements from addend</CODE> have been placed at the end of
     *   this sequence. The current element of this sequence remains where it
     *   was, and the addend</CODE> is also unchanged.
     * @exception NullPointerException
     *   Indicates that addend</CODE> is null.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory to increase the size of this sequence.
     **/
    void addAll(DoubleLinkedSeq addend) throws NullPointerException, OutOfMemoryError{
        if(addend == null) throw new NullPointerException("Given Link list is null");
        tail.setLink(addend.head);
        tail = addend.tail;
    }

    /**
     * Add a new element to this sequence, before the current element.
     * @param element</CODE>
     *   the new element that is being added
     * <dt><b>Postcondition:</b><dd>
     *   A new copy of the element has been added to this sequence. If there was
     *   a current element, then the new element is placed before the current
     *   element. If there was no current element, then the new element is placed
     *   at the start of the sequence. In all cases, the new element becomes the
     *   new current element of this sequence.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for a new node.
     **/
    void addBefore(double element) throws OutOfMemoryError{
        Node temp = head;
        if(!isCurrent()) cursor = head;
        while(temp.getLink() != cursor && temp != cursor) temp = temp.getLink();
        if(temp == head){
            head = new Node(element, temp);
            cursor = head;
        }else{
            temp.setLink(new Node(element, cursor));
            cursor = temp.getLink();
        }
    }

    /**
     * Move forward, so that the current element is now the next element in
     * this sequence.
     * @param - none
     * <dt><b>Precondition:</b><dd>
     *   isCurrent()</CODE> returns true.
     * <dt><b>Postcondition:</b><dd>
     *   If the current element was already the end element of this sequence
     *   (with nothing after it), then there is no longer any current element.
     *   Otherwise, the new element is the element immediately after the
     *   original current element.
     * @exception IllegalStateException
     *   Indicates that there is no current element, so
     *   advance</CODE> may not be called.
     **/
    void advance() throws IllegalStateException{
        if(isCurrent()) cursor = (cursor.getLink() == null)?null:cursor.getLink();
    }

    /**
     * Remove the current element from this sequence.
     * @param - none
     * <dt><b>Precondition:</b><dd>
     *   isCurrent()</CODE> returns true.
     * <dt><b>Postcondition:</b><dd>
     *   The current element has been removed from this sequence, and the
     *   following element (if there is one) is now the new current element.
     *   If there was no following element, then there is now no current
     *   element.
     * @exception IllegalStateException
     *   Indicates that there is no current element, so
     *   removeCurrent</CODE> may not be called.
     **/
    void removeCurrent() throws IllegalStateException{
        Node temp = head;

        if(isCurrent()){
            while(temp != cursor && temp.getLink() != cursor)  temp = temp.getLink();
            temp.setLink(cursor.getLink() == null?null:cursor.getLink());
            cursor = (cursor.getLink() == null)?null:cursor.getLink();
        }
    }

    /**
     * Set the current element at the front of this sequence.
     * @param - none
     * <dt><b>Postcondition:</b><dd>
     *   The front element of this sequence is now the current element (but
     *   if this sequence has no elements at all, then there is no current
     *   element).
     **/
    void start(){
        if(size() > 0) cursor = head;
    }

    void reassociateTail(){
        tail = head;
        while(tail.getLink() != null) tail = tail.getLink();
    }

    /**
     * Generate a copy of this sequence.
     * @param - none
     * @return
     *   The return value is a copy of this sequence. Subsequent changes to the
     *   copy will not affect the original, nor vice versa. Note that the return
     *   value must be type cast to a DoubleLinkedSeq</CODE> before it can be used.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for creating the clone.
     **/
    public DoubleLinkedSeq clone(){
        DoubleLinkedSeq replica;
        Node temp = head.getLink();
        Node rep_temp;

        try{
            replica = (DoubleLinkedSeq) super.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable");
        }

        replica.head = (Node)((Node)head).clone();
        rep_temp = replica.head;

        while(temp.getLink() != null){
            rep_temp.setLink((Node)((Node)temp).clone());
            if(temp == cursor) replica.cursor = rep_temp;
            temp = temp.getLink();
            rep_temp = rep_temp.getLink();
        }

        return replica;
    }

    /**
     * Create a new sequence that contains all the elements from one sequence
     * followed by another.
     * @param s1</CODE>
     *   the first of two sequences
     * @param s2</CODE>
     *   the second of two sequences
     * <dt><b>Precondition:</b><dd>
     *   Neither s1 nor s2 is null.
     * @return
     *   a new sequence that has the elements of s1</CODE> followed by the
     *   elements of s2</CODE> (with no current element)
     * @exception NullPointerException
     *   Indicates that one of the arguments is null.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for the new sequence.
     **/
    static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) throws NullPointerException{
        if(s1 == null || s2 == null) throw new NullPointerException("Can't concatenate null sequences");
        s1.addAll(s2);
        return s1;
    }

    static String toString(DoubleLinkedSeq subj){
        Node temp = subj.head;
        String desc = "The Sequence:\t";
        if(temp.getLink() == null){
            desc += (temp.getData() == 0)?"Blank Sequence\n":temp.getData() + "\n";
        }else{
            while(temp.getLink() != null){
                desc += temp.getData() + ",";
                temp = temp.getLink();
            }
            desc += temp.getData() + "\n";
        }
        return desc;
    }
}
           

