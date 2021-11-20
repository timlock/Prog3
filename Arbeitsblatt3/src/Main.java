import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ringpuffer<Integer> fixedDiscarding = new Ringpuffer<>(5,true,true);
        Ringpuffer<Integer> unfixedNotDiscarding = new Ringpuffer<>(5,false,false);
//        Scanner scanner = new Scanner(System.in);
//        int eingabe;
//        int wert;
//        do{
//            System.out.println("1) add()\n2)remove()\n<0)Abbruch");
//            eingabe = scanner.nextInt();
//            switch (eingabe){
//                case 1 : {
//                    System.out.println("Eingabe: ");
//                    wert = scanner.nextInt();
//                    fixedDiscarding.add(wert);
//                    unfixedNotDiscarding.add(wert);
//                    break;
//                }
//                case 2 : {
//                    fixedDiscarding.remove();
//                    unfixedNotDiscarding.remove();
//                    break;
//                }
//            }
//            System.out.println(fixedDiscarding);
//        } while (eingabe > -1);
        Ringpuffer<Integer> ringpuffer = new Ringpuffer<>(5);
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        ringpuffer.addAll(arrayList);
        System.out.println(ringpuffer);
        ArrayList<Integer> remove = new ArrayList<>(2);
        remove.add(1);
        remove.add(3);
        ringpuffer.removeAll(remove);
        System.out.println(ringpuffer);
        ringpuffer.add(1);
        ringpuffer.add(3);
        System.out.println(ringpuffer);
        ArrayList<Integer> retain = new ArrayList<>(2);
        retain.add(1);
        retain.add(3);
        ringpuffer.retainAll(retain);
        System.out.println(ringpuffer);

    }
}