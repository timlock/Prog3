import java.util.ArrayList;

public class Main<T> {
    public static void main(String[] args) {
        Ringpuffer<Integer> puffer = new Ringpuffer<>(5);
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);


        puffer.addAll(list1);
        System.out.println(puffer);
//        puffer.remove();
//        System.out.println(puffer);
        puffer.retainAll(list2);
        System.out.println(puffer);
    }
}