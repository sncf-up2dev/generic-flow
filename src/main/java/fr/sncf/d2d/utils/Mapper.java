package fr.sncf.d2d.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@FunctionalInterface
public interface Mapper<T, R, E extends Throwable> {

    R map(T object) throws E;

    default Collection<R> map(Collection<T> collection) throws E {
        List<R> result = new ArrayList<>(collection.size());
        for (T item : collection) {
            result.add(this.map(item));
        }
        return result;
    }
}
