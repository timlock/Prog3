

public class Main<T> {
    public static void main(String[] args) {
        Ringpuffer<Integer> puffer = new Ringpuffer<>(5);
        Integer[] integers = {1,2,3,4,5};
        System.out.println(puffer);
    }
}