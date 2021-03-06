package double_sequence;
/**
 * CSC103 Lab2 SequenceTest
 * 2/10/2016
 *
 * DoubleArraySeq Class
 *
 * @author Timothy Haskins & Fred Frey
 */

public class DoubleArraySeq implements Cloneable
{
    // Invariant of the DoubleArraySeq class:
    //   1. The number of elements in the seqeunces is in the instance variable
    //      manyItems.
    //   2. For an empty sequence (with no elements), we do not care what is
    //      stored in any of data; for a non-empty sequence, the elements of the
    //      sequence are stored in data[0] through data[manyItems-1], and we
    //      don�t care what�s in the rest of data.
    //   3. If there is a current element, then it lies in data[currentIndex];
    //      if there is no current element, then currentIndex equals manyItems.
    private double[ ] data;
    private int manyItems;
    private int currentIndex;

    /**
     * Initialize an empty sequence with an initial capacity of 10.  Note that
     * the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     * @param - none
     * @postcondition
     *   This sequence is empty and has an initial capacity of 10.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for:
     *   new double[10].
     **/
    public DoubleArraySeq(){
        this(5);
    }

    /**
     * Initialize an empty sequence with a specified initial capacity. Note that
     * the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     * @param initialCapacity
     *   the initial capacity of this sequence
     * @precondition
     *   initialCapacity is non-negative.
     * @postcondition
     *   This sequence is empty and has the given initial capacity.
     * @exception IllegalArgumentException
     *   Indicates that initialCapacity is negative.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for:
     *   new double[initialCapacity].
     **/
    public DoubleArraySeq(int initialCapacity){
        data = new double[initialCapacity];
        manyItems = 0;
        start();
    }

    public DoubleArraySeq(double list[]){
        data = list;
        manyItems = data.length;
        start();
    }

    /**
     * Move forward, so that the current element is now the next element in
     * this sequence.
     * @param - none
     * @precondition
     *   isCurrent() returns true.
     * @postcondition
     *   If the current element was already the end element of this sequence
     *   (with nothing after it), then there is no longer any current element.
     *   Otherwise, the new element is the element immediately after the
     *   original current element.
     * @exception IllegalStateException
     *   Indicates that there is no current element, so
     *   advance may not be called.
     **/
    public void advance(){
        currentIndex++;
    }

    /**
     * Generate a copy of this sequence.
     * @param - none
     * @return
     *   The return value is a copy of this sequence. Subsequent changes to the
     *   copy will not affect the original, nor vice versa.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for creating the clone.
     **/
    public DoubleArraySeq clone(){
        // Clone a DoubleArraySeq object.
        DoubleArraySeq answer;

        try
        {
            answer = (DoubleArraySeq) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {  // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the "Implements Cloneable"
            // clause at the start of this class.
            throw new RuntimeException
                    ("This class does not implement Cloneable");
        }

        answer.data = data.clone();

        return answer;
    }

    /**
     * Create a new sequence that contains all the elements from one sequence
     * followed by another.
     * @param s1
     *   the first of two sequences
     * @param s2
     *   the second of two sequences
     * @precondition
     *   Neither s1 nor s2 is null.
     * @return
     *   a new sequence that has the elements of s1 followed by the
     *   elements of s2 (with no current element)
     * @exception NullPointerException
     *   Indicates that one of the arguments is null.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for the new sequence.
     * @note
     *   An attempt to create a sequence with a capacity beyond
     *   Integer.MAX_VALUE will cause an arithmetic overflow
     *   that will cause the sequence to fail.
     **/
    public static DoubleArraySeq catenation(DoubleArraySeq s1, DoubleArraySeq s2) throws NullPointerException{
        return new DoubleArraySeq();
    }

    /**
     * Change the current capacity of this sequence.
     * @param minimumCapacity
     *   the new capacity for this sequence
     * @postcondition
     *   This sequence's capacity has been changed to at least minimumCapacity.
     *   If the capacity was already at or greater than minimumCapacity,
     *   then the capacity is left unchanged.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for: new int[minimumCapacity].
     **/
    public void ensureCapacity(int minimumCapacity){
        int n = getCapacity();
        size();
        if(minimumCapacity > n){
            double temp[] = data;
            data = new double[minimumCapacity+5];
            for(int i = 0; i < temp.length; ++i) data[i] = temp[i];
        }
    }

    /**
     * Accessor method to get the current capacity of this sequence.
     * The add method works efficiently (without needing
     * more memory) until this capacity is reached.
     * @param - none
     * @return
     *   the current capacity of this sequence
     **/
    public int getCapacity(){
        return data.length;
    }

    /**
     * Accessor method to get the current element of this sequence.
     * @param - none
     * @precondition
     *   isCurrent() returns true.
     * @return
     *   the current element of this sequence
     * @exception IllegalStateException
     *   Indicates that there is no current element, so
     *   getCurrent may not be called.
     **/
    public double getCurrent() throws IllegalStateException{
        if(isCurrent()) return data[currentIndex];
        else throw new IllegalStateException("There is no current");
    }

    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the
     * getCurrent method.
     * @param - none
     * @return
     *   true (there is a current element) or false (there is no current element at the moment)
     **/
    public boolean isCurrent(){
        return (currentIndex >= 0 && currentIndex < size());
    }

    /**
     * Remove the current element from this sequence.
     * @param - none
     * @precondition
     *   isCurrent() returns true.
     * @postcondition
     *   The current element has been removed from this sequence, and the
     *   following element (if there is one) is now the new current element.
     *   If there was no following element, then there is now no current
     *   element.
     * @exception IllegalStateException
     *   Indicates that there is no current element, so
     *   removeCurrent may not be called.
     **/
    public void removeCurrent() throws IllegalStateException{
        if(!isCurrent()) throw new IllegalStateException("There is no current element to remove");
        for(int i = currentIndex; i < manyItems - 1; i++) data[i] = data[i+1];
        data[manyItems - 1] = 0.0;
    }

    /**
     * Determine the number of elements in this sequence.
     * @param - none
     * @return
     *   the number of elements in this sequence
     **/
    public int size(){
        int count = 0;
        for(int i = 0; i < data.length; i++) count += (data[i] != 0.0)?1:0;
        manyItems = count;
        return count;
    }

    /**
     * Set the current element at the front of this sequence.
     * @param - none
     * @postcondition
     *   The front element of this sequence is now the current element (but
     *   if this sequence has no elements at all, then there is no current
     *   element).
     **/
    public void start(){
        currentIndex = 0;
    }

    /**
     * Reduce the current capacity of this sequence to its actual size (i.e., the
     * number of elements it contains).
     * @param - none
     * @postcondition
     *   This sequence's capacity has been changed to its current size.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for altering the capacity.
     **/
    public void trimToSize(){
        double[ ] trimmedArray;

        if (data.length != manyItems)
        {
            trimmedArray = new double[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }

    /**
     * toString method for description of sequence
     * @return
     */
    public String toString(){
        if(!isCurrent()) start();
        String deets = "The sequence: ";
        int n = size();
        if(n > 0){
            for(int i = 0; i < n; i++) deets += data[i] + " ";
            deets += "\nNumber of Elements: " + n +
                    "\nThe current element: " + getCurrent();
        }else deets += "is empty";
        return deets + "\n";
    }

    /**
     * Add a new element to this sequence, before the current element.
     * If the new element would take this sequence beyond its current capacity,
     * then the capacity is increased before adding the new element.
     * @param element
     *   the new element that is being added
     * @postcondition
     *   A new copy of the element has been added to this sequence. If there was
     *   a current element, then the new element is placed before the current
     *   element. If there was no current element, then the new element is placed
     *   at the start of the sequence. In all cases, the new element becomes the
     *   new current element of this sequence.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for increasing the sequence's capacity.
     * @note
     *   An attempt to increase the capacity beyond
     *   Integer.MAX_VALUE will cause the sequence to fail with an
     *   arithmetic overflow.
     **/
    public void addBefore(double element){
        ensureCapacity(size() + 1);
        if(!isCurrent()) addFront(element);
        else{
            for(int i = getCapacity() - 1; i > currentIndex; --i) data[i] = data[i - 1];
            data[currentIndex] = element;
        }
        size();
    }

    /**
     * Add a new element to this sequence, after the current element.
     * If the new element would take this sequence beyond its current capacity,
     * then the capacity is increased before adding the new element.
     * @param element
     *   the new element that is being added
     * @postcondition
     *   A new copy of the element has been added to this sequence. If there was
     *   a current element, then the new element is placed after the current
     *   element. If there was no current element, then the new element is placed
     *   at the end of the sequence. In all cases, the new element becomes the
     *   new current element of this sequence.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for increasing the sequence's capacity.
     * @note
     *   An attempt to increase the capacity beyond
     *   Integer.MAX_VALUE will cause the sequence to fail with an
     *   arithmetic overflow.
     **/
    public void addAfter(double element){
        int n = size();
        ensureCapacity(n + 1);
        if(n == 0) addFront(element);
        else{
            if(!isCurrent()) currentLast();
            advance();
            for(int i = getCapacity() - 1; i > currentIndex;--i) data[i] = data[i-1];
            data[currentIndex] = element;
        }
        size();
    }

    /**
     * Place the contents of another sequence at the end of this sequence.
     * @param addend
     *   a sequence whose contents will be placed at the end of this sequence
     * @precondition
     *   The parameter, addend, is not null.
     * @postcondition
     *   The elements from addend have been placed at the end of
     *   this sequence. The current element of this sequence remains where it
     *   was, and the addend is also unchanged.
     * @exception NullPointerException
     *   Indicates that addend is null.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory to increase the size of this sequence.
     * @note
     *   An attempt to increase the capacity beyond
     *   Integer.MAX_VALUE will cause an arithmetic overflow
     *   that will cause the sequence to fail.
     **/
    public void addAll(DoubleArraySeq addend){
        int n = addend.size();
        addend.start();
        ensureCapacity(size() + n);
        for(int i = 0; i < n; ++i){
            addAfter(addend.getCurrent());
            addend.advance();
        }
    }

    public void addFront(double num){
        ensureCapacity(size() + 1);
        int n = getCapacity();
        for(int i = n + 1; i > 0; --i) data[i] = data[i - 1];
        data[0] = num;
        start();
    }

    public void addEnd(double num){
        currentLast();
        addAfter(num);
    }

    public void removeFront(){
        for(int i = 0; i < size(); ++i) data[i] = data[i + 1];
        if(isCurrent() && currentIndex > 0) --currentIndex;
        else start();
    }

    public void currentLast(){
        int n = size();
        if(n == 0) start();
        else currentIndex = n - 1;
    }

    public int retrieveElement(double num){
        int temp = -1;
        int i = -1;
        while(i < size() && temp == -1){
            if(data[++i] == num) temp = i;
        }
        return temp;
    }

    public void setCurrent(int i){
        if(i > size()) currentLast();
        else currentIndex = i;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }
}
           
