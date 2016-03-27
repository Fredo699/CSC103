package Lab3;
/**
 * CSC103 DoubleLinkedSeq
 * 3/16/2016
 *
 * Lab3 class
 *
 * @author Timothy Haskins
 */
public class Lab3 {
    public static void main(String args[]){
        DoubleLinkedSeq test = new DoubleLinkedSeq(),
            test_2 = new DoubleLinkedSeq();

        System.out.println("Initializing first list ..........");
        test.start();
        System.out.println(DoubleLinkedSeq.toString(test));
        test.addAfter(1);
        System.out.println(DoubleLinkedSeq.toString(test));
        test.addAfter(2);
        System.out.println(DoubleLinkedSeq.toString(test));
        test.addAfter(3);
        System.out.println(DoubleLinkedSeq.toString(test));
        test.addAfter(4);
        System.out.println(DoubleLinkedSeq.toString(test));
        test.addAfter(5);
        System.out.println(DoubleLinkedSeq.toString(test));
        test.addAfter(6);
        System.out.println(DoubleLinkedSeq.toString(test));

        System.out.println("Initializing second list .........");
        test_2.start();
        test_2.addBefore(1);
        test_2.addBefore(2);
        test_2.addBefore(3);
        test_2.addBefore(4);
        test_2.addBefore(5);
        test_2.addBefore(6);
        System.out.println(DoubleLinkedSeq.toString(test_2));
    }
}
