package Array;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * Created by caohao on 2018-04-18.
 */
public class ArrayList<T> implements List<T> {

    private transient Object[] str;

    private static int size = 0;

    public Itst itst;

    class Itst implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return (T) str[index++];
        }

        @Override
        public void remove() {

        }

    }

    public ArrayList() {
        str = new Object[10];
    }

    public ArrayList(int length) {
        str = new Object[length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return str == null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itst();
    }

    @Override
    public Object[] toArray() {
        return str;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size == str.length) {
            Object[] newStr = str;
            str = new Object[size + 1];
            for (int i = 0; i < newStr.length; i++) {
                str[i] = newStr[i];
            }
        }
        str[size] = t;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = checkValue(o);
        remove(index);
        return index != -1;
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
    public boolean addAll(int index, Collection<? extends T> c) {
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
    public T get(int index) {
        checkIndex(index);
        return backValue(str[index]);
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object o = str[index];
        for (; index < size - 1; index++) {
            str[index] = str[index + 1];
        }
        size--;
        return backValue(o);
    }

    @Override
    public int indexOf(Object o) {
        return checkValue(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return unCheckValue(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new NullPointerException();
        }
    }

    private T backValue(Object o) {
        return (T) o;
    }

    private int checkValue(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (str[i].equals(o)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int unCheckValue(Object o) {
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (str[i].equals(o)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
