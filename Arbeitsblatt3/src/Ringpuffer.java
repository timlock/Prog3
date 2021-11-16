import java.io.Serializable;
import java.util.*;

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private ArrayList<T> elements;
    private int writePos = 0;
    private int readPos = 0;
    private int size = 0;
    private int capacity;
    private boolean fixedCapacity;
    private boolean discarding = false;

    public Ringpuffer(int capacity) {
        this.capacity = capacity;
        elements = new ArrayList<T>();
    }

    public int readIncrement() {
        readPos = (readPos + 1) % capacity;
        return readPos;
    }

    public int writeIncrement() {
        writePos = (writePos + 1) % capacity;
        return writePos;
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
            private int index = readPos;

            @Override
            public boolean hasNext() {
                if (!isEmpty()) return false;
                if (size < capacity && index < writePos) {
                    return true;
                } else if ((index + 1) % capacity != writePos) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("Es existiert kein weiteres Element");
                else {
                    return elements.get(index = (index + 1) % capacity);
                }
            }
        };
        return iterator;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        Scanner eingabe = new Scanner(System.in);
        //Puffer darf nicht beschrieben werden
        if (!discarding) {
            System.out.println("Puffer ist voll und darf nicht überschrieben werden\n" +
                    "soll der Puffer wieder Elemente überschreiben dürfen? 1) ja 2) nein");
            int antwort;
            do {
                antwort = eingabe.nextInt();
            } while (antwort == 1 || antwort == 2);
            if (antwort == 1) discarding = false;
            add(t);
        }
        //Puffer ist voll
        if (size == capacity) {
            System.out.println("Kapazitätsgrenze wurde erreicht, soll der Puffer\n1) das Element "
                    + elements.get(readPos) + "überschreiben?\n" +
                    "2)Keine neuen Elemente mehr aufnehmen?\n" +
                    "Die Kapazität erhöhen?");

            int antwort;
            do {
                antwort = eingabe.nextInt();
                switch (antwort) {
                    case 1: {
                        readIncrement();
                        writePos = readPos;
                        elements.set(writePos, t);
                        System.out.println("Element wurde überschrieben");
                        return true;
                    }

                    case 2: {
                        discarding = false;
                        System.out.println("Puffer kann keine neuen Elemente mehr aufnehmen");
                        return false;
                    }

                    case 3: {
                        System.out.println("Bitte neue Kapazität angeben");
                        int capacity = eingabe.nextInt();
                        this.capacity = capacity;
                        add(t);
                    }
                    break;
                }
            } while (antwort < 4 && antwort > 0);
            return true;
        }
        elements.set(writePos, t);
        writeIncrement();
        if (writePos == readPos) readIncrement();
        size++;
        return true;

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
