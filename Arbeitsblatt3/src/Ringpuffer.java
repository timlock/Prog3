import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

@FunctionalInterface
interface CollectionLambda {
    void execute(int matches);
}

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private ArrayList<T> elements;
    private int writePos = 0;
    private int readPos = 0;
    private int size = 0;
    private int capacity = 10;
    private boolean fixedCapacity = false;
    private boolean discarding = false;

    public Ringpuffer() {
        elements = new ArrayList<T>();
        fillUp(capacity);
    }

    public Ringpuffer(int capacity) {
        this.capacity = capacity;
        elements = new ArrayList<T>();
        fillUp(capacity);
    }

    public Ringpuffer(int capacity, boolean fixedCapacity, boolean discarding) {
        this(capacity);
        this.fixedCapacity = fixedCapacity;
        this.discarding = discarding;
    }

    public int readIncrement() {
        readPos = (readPos + 1) % capacity;
        return readPos;
    }

    public int writeIncrement() {
        writePos = (writePos + 1) % capacity;
        return writePos;
    }

    public void fillUp(int requiredSpace) {
        for (int i = 0; i < requiredSpace; i++) {
            elements.add(writePos + i, null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : elements) {
            if (o.equals(element)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            private int index = (readPos - 1) % capacity;
            private int remainingElements = size;

            @Override
            public boolean hasNext() {
//                if (isEmpty()) return false;
//                 if ((index + 1) % capacity != writePos) {
//                    return true;
//                }
                if (remainingElements == 0) return false;
                else return true;
            }

            @Override
            public T next() {
                T rekursiveVar;
                if (!hasNext()) throw new NoSuchElementException("Es existiert kein weiteres Element");
                if (elements.get((index + 1) % capacity) == null) {
                    index++;
                    rekursiveVar = next();
                } else {
                    index = (index + 1) % capacity;
                    remainingElements--;
                    return elements.get(index);
                }
                return rekursiveVar;
            }
        };
        return iterator;
    }

    @Override
    public Object[] toArray() {
        Object[] objArray = new Object[size];
        for (int i = 0; i < capacity; i++) {
            objArray[i] = (Object) elements.get((readPos + i) % capacity);
        }
        return objArray;
    }

    @Override
    public <ForeignType> ForeignType[] toArray(ForeignType[] a) throws NullPointerException {
        if (a == null) throw new NullPointerException("Übergebener Array ist null");
        if (a.length < size) a = (ForeignType[]) Array.newInstance(a.getClass(), size);
        for (int i = 0; i < capacity; i++) {
            a[i] = (ForeignType) elements.get((readPos + i) % capacity);
        }

        return null;
    }

    @Override
    public boolean add(T t) {
        if (!discarding && size == capacity && fixedCapacity) {
            System.out.println(t + " konnte nicht hinzugefügt werden,der Puffer ist voll und Elemente müssen entnommen werden um neue Elemente aufzunehmen");
            return false;
        }
        elements.set(writePos, t);
        System.out.println(t + " wurde hinzugefügt an Stelle:" + writePos);
        writeIncrement();
        if(size < capacity) size++;
        if(size == capacity && discarding){
            System.out.print("und " + elements.get(writePos) + " wurde von " + t + " überschrieben");
            readIncrement();
        }
        Scanner eingabe = new Scanner(System.in);
        if (size == capacity) {
            System.out.println("Kapazitätsgrenze wurde erreicht, soll der Puffer\n1)Beim hinzufügen von Elementen alte ELemente überschreiben?\n" +
                    "2)Keine neuen Elemente mehr aufnehmen?\n" +
                    "3)Die Kapazität erhöhen?");

            int antwort;
            do {
                antwort = eingabe.nextInt();
                switch (antwort) {
                    case 1: {
                        discarding = true;
                        System.out.println("Alte Elemente werden nun überschrieben");
//                        System.out.print(elements.get(writePos) + " wurde von ");
//                        readIncrement();
//                        writePos = readPos;
//                        elements.set(writePos, t);
//                        System.out.println(t + " überschrieben");
//                        return true;
                        break;
                    }

                    case 2: {
                        discarding = false;
                        System.out.println("Puffer nimmt erst Elemente an, wenn wieder Speicher frei ist");
//                        System.out.println("Puffer kann keine neuen Elemente mehr aufnehmen, soll der Puffer Elemente überschreiben dürfen?\n1) Ja\n2)Nein");
//                        if (eingabe.nextInt() == 1) discarding = true;
//                        else discarding = false;
//                        return add(t);
                        break;
                    }

                    case 3: {
                        if(fixedCapacity) {
                            System.out.println("Kapazität darf nicht mehr geändert werden");
                            return true;
                        }
                        System.out.println("Bitte die Anzahl neuer zusätzlicher Speicherstellen angeben");
                        int newCapacity = eingabe.nextInt();
                        capacity += newCapacity;
                        readPos = (readPos + newCapacity) % capacity;
                        fillUp(newCapacity);
                        break;

                    }
                }
            } while (antwort > 4 || antwort < 0);
        }
        return true;

    }

    @Override
    public boolean remove(Object o) {
        if (!elements.contains(o)) return false;
        System.out.println("Entferne " + o + " aus dem Puffer");
        elements.remove(o);
        size--;
        fillUp(1);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) { //Collection vom unbekanntem Typ
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) { //Collection mit Typen die von T erben bsp. T = Number, dann können auch Float und Double teil der Collection sein
        if (c.isEmpty()) return false;
        System.out.println("Füge " + c + " hinzu");
        int altered = 0;
        for (T i :
                c) {
            if (add(i)) altered++;
        }
        if (altered == 0) return false;
        return true;
    }

    private boolean removeElements(CollectionLambda function, Collection<?> c) {
        if (c.isEmpty()) return false;
        int oldSize = size;
        for (int i = 0; i < size; i++) {
            Iterator<?> iterator = c.iterator();
            int matches = 0;
            while (iterator.hasNext()) {
                if ((iterator.next()) == peek()) matches++;
            }
            function.execute(matches);
        }

//        fillUp(c.size());
        if (oldSize == size) return false;
//        while (elements.get(writePos) == null) writeIncrement();
//        while (elements.get(readPos) == null) readIncrement();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        System.out.println("Entferne" + c );
        CollectionLambda remove = (matches) -> {
            if(matches > 0) remove();
        };
        return removeElements(remove, c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        System.out.println("Entferne alle Elemente außer" + c);
        CollectionLambda retain = (matches) -> {
            if(matches == 0) remove();
        };
        return removeElements(retain, c);
    }

    @Override
    public void clear() {
        System.out.println("Ringpuffer wird geleert");
        for (int i = 0; i < capacity; i++) {
            elements.set(i, null);
        }

    }

    @Override
    public boolean offer(T t) {
        if (size == capacity && fixedCapacity) return false;
        add(t);
        return true;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException("Der Puffer ist leer");
//        T output = elements.get(readPos);
        System.out.println(elements.get(readPos) + " wurde entfernt");
        readIncrement();
        size--;
        return elements.get((readPos - 1) % capacity);
    }

    @Override
    public T poll() {
        return remove();
    }

    @Override
    public T element() {
        if (size == 0) throw new NoSuchElementException("Es existiert kein weiteres Element");
        return elements.get(readPos);
    }

    @Override
    public T peek() {
        if (size == 0) return null;
        return elements.get(readPos);
    }

    @Override
    public String toString() {
        Iterator iterator = iterator();
        String output = "Inhalt vom Ringpuffer (size:" + size + " capacity: " + capacity + " Lesepositon: " + readPos + " Schreibposition: " + writePos + ")\n[";
        while (iterator.hasNext()) {
            output += iterator.next();
            if (iterator.hasNext()) output += ", ";
        }
        output += "]";
        return output;
    }
}
/*
Führen Sie eine Literaturrecherche durch, ob zur Umsetzung des generischen Ringpuffers
        anstelle von ArrayList<T> zur Verwaltung der Elemente nicht besser ein generisches Array
        verwendet werden sollte.
      Ein parametrisierter Array ist nicht Typsicher, da ein Array erst zur Laufzeit den Array Store-check, durchführt,
      generische Typen jedoch zur Laufzeit wegen der Type Erasure gar nicht sichtbar sind für den Array.
      Beim anschließenden ausführen und Zugriffs auf eine Stelle im Array, würde es zu einer ClassCastException kommen,
      da versucht wird Integer vom Typ Object nach String zu casten.
      Beispiel:
        Object[] objects = new String[10];
        objects[0] = new Integer(1);
      Würde während der Compiler Zeit zur ArrayStoreException führen
        Object[] objects = new Instance<String>[10];
        objects[0] = new Instance<Integer>(1);
       Nach Type Erasur:
        Object[] objects = new Instance<>[10];
        objects[0] = new Instance<>(1);
    Würde nicht zu einer ArrayStoreException führen
*/
