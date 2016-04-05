package Lab3;
/**
 * CSC103 Lab3 Node
 * 3/16/2016
 *
 * Node class
 *
 * @author Timothy Haskins
 */
public class Node implements Cloneable{
    private double data;
    private Node link;

    Node(){
        this(0, null);
    }

    Node(double d, Node l){
        data = d;
        link = l;
    }

    Node getLink (){
        return link;
    }

    double getData(){
        return data;
    }

    void setData(double newElem){
        data = newElem;
    }

    void setLink (Node newNode){
        link = newNode;
    }

    public Node clone(){
        Node replica;

        try{
            replica = (Node) super.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable");
        }

        replica.data = data;

        return replica;
    }
}
