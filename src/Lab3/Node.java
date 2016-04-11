package Lab3;
/**
 * CSC103 Node
 * 3/16/2016
 *
 * Node class
 *
 * @author Fred Frey & Timothy Haskins
 */
public class Node {
    private double data;
    private Node link;
    //constructors
    public Node(){
        data = 0;
        link = null;
    }
    public Node(double d, Node l){
        data = d;
        link = l;
    }
    void setData(double newElem){
        data = newElem;
    }
    void setLink (Node newNode){
        link = newNode;
    }
    double getData(){
        return data;
    }
    Node getLink(){
        return link;
    }
}