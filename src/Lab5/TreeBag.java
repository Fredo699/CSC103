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
 * @author Fred Frey & Tim Haskins
 ******************************************************************************/
public class TreeBag<E> implements Cloneable {
    // Invariant of the IntTreeBag class:
    //   1. The elements in the bag are stored in a binary search tree.
    //   2. The instance variable root is a reference to the root of the
    //      binary search tree (or null for an empty tree).
    private BTNode<E> root;
    
    public TreeBag(){
    	root = null;
    }

    /**
     * Insert a new element into this bag
     *
     * @param element
     **/
    @SuppressWarnings("rawtypes")
	public void add(Comparable element) {
        System.out.println(element);
        root = addNode(element, root);
    }

    /**
     * Insert a new element into this bag
     *
     * @param x
     * @param p
     * @return BTNode
     **/
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private BTNode<E> addNode(Comparable x, BTNode<E> p) {
        if (p == null) // create the node - base case
            p = new BTNode(x, null, null);
        
        else if (x.compareTo(p.getData()) < 0){
            if (p.getLeft() == null){
            	p.setLeft(new BTNode(x, null, null));
            }
        	
            else
            	p.setLeft(addNode(x, p.getLeft()));
        }
        else if (x.compareTo(p.getData()) > 0){
        	if (p.getRight() == null){
        		p.setRight(new BTNode(x, null, null));
        	}
        	else
        		p.setRight(addNode(x, p.getRight()));
        }
        else // keys are equal ��� replace with new data
            p.setData((E) x);
        return p;
    }

    /**
     * Remove node with data equal to target if it exists and restructure tree
     *
     * @param target
     * @return boolean
     **/
    @SuppressWarnings("rawtypes")
	public boolean remove(Comparable target) {
        BTNode<E> new_root = removeNode(root, target);
        if (new_root != null){
        	root = new_root;
        	return true;
        	}
        else 
        	return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private BTNode<E> removeNode(BTNode<E> p, Comparable target){
    	if (p == null)
    		return p; // We've been handed a nothing node.
    	if (target.compareTo(p.getData()) < 0)
    		p.setLeft(removeNode(p.getLeft(), target));
    	
    	else if (target.compareTo(p.getData()) > 0)
    		p.setRight(removeNode(p.getRight(), target));
    	
    	else if (p.getLeft() != null && p.getRight() != null){
    		p.setData(p.getLeft().getRightmostData());
    		p.setLeft(p.getLeft().removeRightmost());
    	}
    	
    	else if (p.getLeft() == null)
    		p = p.getRight();
    	else
    		p = p.getLeft();
    	
    	return p;
    }

    /**
     * Retrieve node within tree that has element as data
     *
     * @param element element.compareTo(<your search target>) = 0
     * @return E
     **/
    @SuppressWarnings("rawtypes")
	public Object retrieve(Comparable element) {
        return retrieveNode(root, element).getData();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private BTNode<E> retrieveNode(BTNode<E> p, Comparable element){
    	if (element.compareTo(p.getData()) == 0) 
    		return p;
    	
    	else if (element.compareTo(p.getData()) > 0)
    		return retrieveNode(p.getRight(), element);
    	
    	else if (element.compareTo(p.getData()) < 0)
    		return retrieveNode(p.getLeft(), element);
    	
    	return null;
    }

    /**
     * Display the tree
     **/
    public String display() {
    	return in_order_string(root);
    }
    
    /**
     * display pre-order
     */
    public String display_preorder(){
    	return pre_order_string(root);
    }
    
    /**
     * Give a string list of every node pre-order
     * @param p the root of the subtree.
     */
    public String pre_order_string(BTNode<E> p){
    	String ret = "";
    	if (p != null){
    		ret += p.getData() + "\n";
    		ret += pre_order_string(p.getLeft());
    		ret += pre_order_string(p.getRight());
    	}
    	
    	return ret;
    }
    
    /**
     * Give a string list of every node pre-order
     * @param p the root of the subtree.
     */
    public String post_order_string(BTNode<E> p){
    	String ret = "";
    	if (p != null){
    		ret += post_order_string(p.getLeft());
    		ret += post_order_string(p.getRight());
    		ret += p.getData() + "\n";
    	}
    	
    	return ret;
    }
    
    /**
     * Give a string list of every node in-order
     * @param p the root of the subtree.
     */
    public String in_order_string(BTNode<E> p){
    	String ret = "";
    	if (p != null){
    		ret += in_order_string(p.getLeft());
    		ret += p.getData() + "\n";
    		ret += in_order_string(p.getRight());
    	}
    	return ret;
    }
}
           
