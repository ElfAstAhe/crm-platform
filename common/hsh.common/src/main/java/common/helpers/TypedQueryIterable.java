package common.helpers;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * реализация iterable для TypedQuery
 *
 * @param <T>
 *
 * @author elf
 */
public class TypedQueryIterable<T> implements Iterable<T>{
    private final TypedQueryIterator<T> iterator;

    public TypedQueryIterable(TypedQuery<T> query, TypedQueryIterator.NextIterationConditionSetter<T> nextIterationConditionSetter) {
        iterator = new TypedQueryIterator<>(query, nextIterationConditionSetter);
    }

    public void reset() {
        iterator.reset();
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        while (iterator().hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
