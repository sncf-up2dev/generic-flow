package fr.sncf.d2d.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@FunctionalInterface
public
interface Folder<T, E extends Throwable> {

    T fold(T accumulator, T object) throws E;

    default Optional<T> fold(Collection<T> collection) throws E {
        Iterator<T> itr = collection.iterator();
        if (!itr.hasNext()) {
            return Optional.empty();
        }
        T result = itr.next();
        while (itr.hasNext()) {
            result = this.fold(result, itr.next());
        }
        return Optional.of(result);
    }
}
