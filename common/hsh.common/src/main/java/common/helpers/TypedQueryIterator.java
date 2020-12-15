package common.helpers;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * реализация iterator для TypedQuery
 *
 * @param <T>
 * @author elf
 */
public class TypedQueryIterator<T> implements Iterator<T> {
    private final TypedQuery<T> query;
    private boolean isFirstElement;
    private final NextIterationConditionSetter<T> nextIterationConditionSetter;
    private T result;

    /**
     * Уницифированый констрктор
     *
     * @param query                        typed query
     * @param nextIterationConditionSetter next iteration condition
     */
    public TypedQueryIterator(TypedQuery<T> query, NextIterationConditionSetter<T> nextIterationConditionSetter) {
        this.query = query;
        this.nextIterationConditionSetter = nextIterationConditionSetter;
    }

    public static <T> TypedQueryIterator<T> createSimpleIterator(TypedQuery<T> query,
                                                                 String paramName,
                                                                 Function<T, Object> nextIterationConditionGetter,
                                                                 Object startCondition) {
        return new TypedQueryIterator<>(query, (q, p, f) -> q.setParameter(paramName, f ? startCondition : nextIterationConditionGetter.apply(p)));
    }

    @Override
    public boolean hasNext() {
        doIteration();
        return result != null;
    }

    @Override
    public T next() {
        if (result == null)
            throw new NoSuchElementException();

        return result;
    }

    public void reset() {
        isFirstElement = true;
    }

    private void doIteration() {
        try {
            nextIterationConditionSetter.setCondition(query, result, isFirstElement);
            isFirstElement = false;
            result = query.setMaxResults(1).getSingleResult();
        } catch (Exception ex) {
            result = null;
        }
    }

    public interface NextIterationConditionSetter<T> {
        void setCondition(TypedQuery<T> query, T prevResult, boolean isFirstResult);
    }
}
