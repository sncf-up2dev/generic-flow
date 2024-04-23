package fr.sncf.d2d.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Filterer<T, E extends Throwable> {

    boolean test(T object) throws E;

    default Collection<T> filter(Collection<T> collection) throws E {
        List<T> result = new ArrayList<>();
        for (T item : collection) {
            if (this.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
