package fr.sncf.d2d.utils;

import java.util.Collection;

@FunctionalInterface
public interface Reducer<T, R> {

    R reduce(R accumulator, T object);

    default R reduce(Collection<T> collection, R identity) {
        R result = identity;
        for (T item : collection) {
            result = this.reduce(result, item);
        }
        return result;
    }

}
