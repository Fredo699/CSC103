package Lab3;

/******************************************************************************
 * This class is a homework assignment;
 * A DoubleLinkedSeq</CODE> is a collection of double</CODE> numbers.
 * The sequence can have a special "current element," which is specified and
 * accessed through four methods that are not available in the sequence class
 * (start, getCurrent, advance and isCurrent).
 *
 * <dl><dt><b>Limitations:</b>
 *   Beyond Int.MAX_VALUE</CODE> elements, the size</CODE> method
 *   does not work.
 *
 * <dt><b>Note:</b><dd>
 *   This file contains only blank implementations ("stubs")
 *   because this is a Programming Project for my students.
 *
 * <dt><b>Outline of Java Source Code for this class:</b><dd>
 *   <A HREF="../../../../edu/colorado/collections/DoubleLinkedSeq.java">
 *   http://www.cs.colorado.edu/~main/edu/colorado/collections/DoubleLinkedSeq.java
 *   </A>
 *   </dl>
 *
 * @version
 *   Jan 24, 1999
 ******************************************************************************/
public class DoubleLinkedSeq implements Cloneable {
    private Node head, tail, cursor;

    /**
     * Initialize an empty sequence.
     * @param - none
     * <dt><b>Postcondition:</b><dd>
     *   This sequence is empty.
     **/
    public DoubleLinkedSeq(){
        head = tail = cursor = null;
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
    public void addAfter(double element) throws OutOfMemoryError{
        if(!isCurrent())
        	throw new IllegalStateException("There is no current!");
        Node temp = new Node(element, null);
        if(cursor == tail){
        	tail.setLink(temp);
        	tail = temp;
        }
        else{
        	temp.setLink(cursor.getLink());
        	cursor.setLink(temp);
        }
        advance();
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
    public void addBefore(double element) throws OutOfMemoryError{
        Node temp = head;
        if(isCurrent()){
            while(null != temp.getLink() && cursor != temp.getLink())
                temp = temp.getLink();
            temp.setLink(new Node(element, cursor));
            cursor = temp.getLink();
        }else{
            if(null == head){
                head = new Node(element, null);
                start();
            }else{
                temp = new Node(element, head);
                head  = temp;
            }
        }
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
    public void addAll(DoubleLinkedSeq addend){
        if(null != addend){
            tail.setLink(addend.head);
            tail = addend.tail;
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
    public void advance() throws IllegalStateException{
        if(isCurrent()) cursor = cursor.getLink();
        else throw new IllegalStateException("There is no current element");
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
    public Object clone(){
        try {
            return (DoubleLinkedSeq) super.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable");
        }
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
     * @exception NullPointerException.
     *   Indicates that one of the arguments is null.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for the new sequence.
     **/
    public static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2)
            throws NullPointerException, OutOfMemoryError{
        if(null != s1 && null != s2){
            DoubleLinkedSeq result = new DoubleLinkedSeq();
            Node temp = s1.head;
            result.head = new Node(temp.getData(), null);
            result.start();
            for(int i = 0; i < 2; ++i){
                while(null != temp.getLink()){
                    temp = temp.getLink();
                    result.cursor.setLink(new Node(temp.getData(), null));
                    result.advance();
                }
                temp = (0 == i)?s2.head:null;
            }
            result.tail = result.cursor;
            return result;
        }else throw new NullPointerException("One or both parameters are null");
    }

    /**
     * Accessor method to get the current element of this sequence.
     * @param - none
     * <dt><b>Precondition:</b><dd>
     *   isCurrent()</CODE> returns true.
     * @return
     *   the data within current
     * @exception IllegalStateException
     *   Indicates that there is no current element, so
     *   getCurrent</CODE> may not be called.
     **/
    public double getCurrent() throws IllegalStateException{
        if(isCurrent()) return cursor.getData();
        else throw new IllegalStateException("There is no current element");
    }

    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the
     * getCurrent</CODE> method.
     * @param - none
     * @return
     *   true (there is a current element) or false (there is no current element at the moment)
     **/
    public boolean isCurrent(){
        return (null != cursor);
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
    public void removeCurrent() throws IllegalStateException{
        if(isCurrent()){
        	if (cursor == tail){
        		Node temp = head;
        		while(temp.getLink() != tail) temp = temp.getLink();
        		tail = temp;
        		tail.setLink(null);
        	}
        	else{
        		cursor.setData(cursor.getLink().getData());
	            cursor.setLink(cursor.getLink().getLink());
        	}
        		
        }else throw new IllegalStateException("There is no current element");
    }

    /**
     * Determine the number of elements in this sequence.
     * @param - none
     * @return
     *   the number of elements in this sequence
     **/
    public int size(){
        int count = 0;
        Node temp = head;
        if(null != temp){
            ++count;
            while(null != temp.getLink()){
                temp = temp.getLink();
                ++count;
            }
        }
        return count;
    }

    /**
     * Set the current element at the front of this sequence.
     * @param - none
     * <dt><b>Postcondition:</b><dd>
     *   The front element of this sequence is now the current element (but
     *   if this sequence has no elements at all, then there is no current
     *   element).
     **/
    public void start(){
        cursor = head;
    }

    /**
     * Add a new element at the front of the sequence
     * And make it the current element
     * @param element</CODE>
     *   the new element that is being added
     */
    public void addFront(double element){
        head = new Node(element, head);
        if (tail == null) tail = head;
        start();
    }

    /**
     * Remove the element at the front of the sequence
     * Throw an exception if the sequence is empty
     * Make the next element the current element, if there is one
     * @exception IllegalStateException.
     *   Indicates that the list is empty.
     */
    public void removeFront() throws IllegalStateException{
        if(null != head){
            head = head.getLink();
            start();
        }else throw new IllegalStateException("The list is empty");
    }

    /**
     * Add a new element at the end of the sequence
     * And make that element the current element
     * @param element</CODE>
     *   the new element that is being added
     */
    public void addEnd(double element){
        if(null != tail){
            tail.setLink(new Node(element, null));
            tail = tail.getLink();
            currentLast();
        }else{
            throw new IllegalStateException("List is empty!");
        }
    }

    /**
     * Make the last element of the sequence the current Element
     * @exception IllegalStateException.
     *   Indicates that the list is empty.
     */
    public void currentLast() throws IllegalStateException{
        if(tail == null)
        	throw new IllegalStateException("list is empty.");
        cursor = tail;
    }

    /**
     * Returns the ith element of the sequence
     * make current element to the ith element
     * @exception IllegalStateException.
     *   Indicates that the list is empty.
     * @param i
     *  position
     * @return
     *  Node at i-th position
     */
    public Node retrieveElement(int i){
        setCurrent(i);
        return cursor;
    }

    /**
     * Makes the ith element become the current element
     * @exception IllegalStateException.
     *   Indicates that the list is empty.
     * @param i
     *  position
     * @return
     *  Node at i-th position
     */
    public void setCurrent(int i){
        int count = 0;
        Node temp = head;
        if(null != temp){
            while(null != temp.getLink() && i > count){
            	temp = temp.getLink();
            	count++;
            }
            cursor = temp;
        }else throw new IllegalStateException("The list is empty");
    }
}
           

