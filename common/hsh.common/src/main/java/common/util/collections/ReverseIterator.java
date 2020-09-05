package common.util.collections;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author elf
 * @param <T>
 */
/*
http://qaru.site/questions/33434/iterating-through-a-list-in-reverse-order-in-java
List<String> list = new ArrayList<String>();
list.add("A");
list.add("B");
list.add("C");
list.add("D");
list.add("E");

for (String s : new ReverseIterator<String>(list)) {
    System.out.println(s);
}*/
public class ReverseIterator<T> implements Iterator<T>, Iterable<T> {

    private final List<T> list;
    private int position;

    public ReverseIterator(List<T> list) {
        this.list = list;
        this.position = list.size() - 1;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public T next() {
        return list.get(position--);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}