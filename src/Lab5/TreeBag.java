package Lab5;

/******************************************************************************
 * This class is a homework assignment;
 * An <CODE>IntTreeBag</CODE> is a collection of int numbers.
 * <p>
 * <dl><dt><b>Limitations:</b> <dd>
 * Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
 * and <CODE>size</CODE> are wrong.
 * <p>
 * <dt><b>Outline of Java Source Code for this class:</b><dd>
 * <A HREF="../edu/colorado/collections/IntTreeBag.java">
 * http://www.cs.colorado.edu/~main/edu/colorado/collections/IntTreeBag.java
 * </A>
 * <p>
 * <dt><b>Note:</b><dd>
 * This file contains only blank implementations ("stubs")
 * because this is a Programming Project for my students.
 *
 * @version Jan 24, 1999
 ******************************************************************************/
public class TreeBag<E> implements Cloneable {
    // Invariant of the IntTreeBag class:
    //   1. The elements in the bag are stored in a binary search tree.
    //   2. The instance variable root is a reference to the root of the
    //      binary search tree (or null for an empty tree).
    private BTNode<E> root;

    /**
     * Insert a new element into this bag
     *
     * @param element
     **/
    @SuppressWarnings("unchecked")
    public void add(Comparable element) {
        System.out.println(element.toString());
        addNode(element, root);
    }

    /**
     * Insert a new element into this bag
     *
     * @param x
     * @param p
     * @return BTNode
     **/
    @SuppressWarnings("unchecked")
    private BTNode<E> addNode(Comparable x, BTNode<E> p) {
        if (p == null) // create the node - base case
            p = new BTNode(x, null, null);
        else if (x.compareTo(p.getData()) < 0)
            p.setLeft(addNode(x, p.getLeft()));
        else if (x.compareTo(p.getData()) > 0)
            p.setRight(addNode(x, p.getRight()));
        else // keys are equal – replace with new data
            p.setData((E) x);
        return p;
    }

    /**
     * Remove node with data equal to target if it exists and restructure tree
     *
     * @param target
     * @return boolean
     **/
    public boolean remove(Comparable target) {
        // Student will replace this return statement with their own code:
        return false;
    }

    /**
     * Retrieve node within tree that has element as data
     *
     * @param element
     * @return BTNode
     **/
    public Object retrieve(Comparable element) {
        return null;
    }

    /**
     * Display the tree
     **/
    public void display() {
        System.out.println("Displaying Tree");
    }
}
           
