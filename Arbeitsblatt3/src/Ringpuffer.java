import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

@FunctionalInterface
interface CollectionLambda<T> {
    boolean execute(Collection<T> collection);
}

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private ArrayList<T> elements;
    private int writePos = 0;
    private int readPos = 0;
    private int size = 0;
    private int capacity = 10;
    private boolean fixedCapacity = true;
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
                if(elements.get((index + 1) % capacity) == null) {
                    index++;
                    rekursiveVar = next();
                }
                else {
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
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) a = (T1[]) Array.newInstance(a.getClass(), size);
        for (int i = 0; i < capacity; i++) {
            a[i] = (T1) elements.get((readPos + i) % capacity);
        }
        return null;
    }

    @Override
    public boolean add(T t) {
        if(discarding && size == capacity) {
            System.out.println("Puffer ist voll und Elemente müssen entnommen werden um neue Elemente aufzunehmen");
            return false;
        }
        Scanner eingabe = new Scanner(System.in);
        if (size == capacity) {
            System.out.println("Kapazitätsgrenze wurde erreicht, soll der Puffer\n1)Das Element "
                    + elements.get(readPos) + " überschreiben?\n" +
                    "2)Die Schreibrechte ändern?\n" +
                    "3)Die Kapazität erhöhen?");

            int antwort;
            do {
                antwort = eingabe.nextInt();
                switch (antwort) {
                    case 1: {
                        readIncrement();
                        writePos = readPos;
                        elements.set(writePos, t);
                        System.out.println(t + " hat eine Speicherstelle überschrieben");
                        return true;
                    }

                    case 2: {
                        System.out.println("Puffer kann keine neuen Elemente mehr aufnehmen, soll der Puffer Elemente überschreiben dürfen?\n1) Ja\n2)Nein");
                        if (eingabe.nextInt() == 1) discarding = false;
                        else discarding = true;
                        return add(t);

                    }

                    case 3: {
                        System.out.println("Bitte die Anzahl neuer zusätzlicher Speicherstellen angeben");
                        int newCapacity = eingabe.nextInt();
                        capacity += newCapacity;
                        readPos = (readPos + newCapacity) % capacity;
                        fillUp(newCapacity);
                        return add(t);
                    }
                }
            } while (antwort > 4 || antwort < 0);
        }
        System.out.println("Füge " + t + " zur Ringpuffer hinzu");
        elements.set(writePos, t);
        writeIncrement();
//        if (writePos == readPos) readIncrement();
        size++;
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
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) return false;
        int altered = 0;
        for (T i :
                c) {
            if (add(i)) altered++;
        }
        if (altered == 0) return false;
        return true;
    }

    public boolean removeElements(CollectionLambda function, Collection<?> c) {
        if (c.isEmpty()) return false;
        int oldSize = size;
        function.execute(c);
        fillUp(c.size());
        size = size - c.size();
        if (oldSize == size) return false;
        while (elements.get(writePos) == null) writeIncrement();
        while (elements.get(readPos) == null) readIncrement();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        System.out.println("Entferne" + c);
        CollectionLambda<T> retain = ((a) -> elements.removeAll(a));
        return removeElements(retain, c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        System.out.println("Entferne alle Elemente außer" + c);
        CollectionLambda<T> remove = ((a) -> elements.retainAll(a));
        return removeElements(remove, c);
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
        System.out.println("Entferne " + elements.get(readPos));
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
        String output = "Inhalt vom Ringpuffer (size:" + size + " Lesepositon: " + readPos + " Schreibposition: " + writePos + ")\n[";
        while (iterator.hasNext()) {
            output += iterator.next();
            if(iterator.hasNext()) output += ", ";
        }
        output += "]";
        return output;
    }
}
