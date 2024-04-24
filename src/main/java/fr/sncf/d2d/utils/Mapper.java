package fr.sncf.d2d.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@FunctionalInterface
public interface Mapper<T, R> {

    R map(T object);

    default Collection<R> map(Collection<T> collection) {
        List<R> result = new ArrayList<>(collection.size());
        for (T item : collection) {
            result.add(this.map(item));
        }
        return result;
    }
}
