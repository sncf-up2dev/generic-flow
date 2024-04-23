package fr.sncf.d2d.utils;

import java.util.Collection;

@FunctionalInterface
public interface Reducer<T, R, E extends Throwable> {

    R reduce(R accumulator, T object) throws E;

    default R reduce(Collection<T> collection, R identity) throws E {
        R result = identity;
        for (T item : collection) {
            result = this.reduce(result, item);
        }
        return result;
    }

}
