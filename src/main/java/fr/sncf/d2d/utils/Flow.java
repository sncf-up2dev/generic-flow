package fr.sncf.d2d.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public final class Flow<T> {

    private final Collection<T> collection;

    private Flow(Collection<T> collection) {
        this.collection = collection;
    }

    public <R, E extends Throwable> Flow<R> map(Mapper<T, R, E> mapper) throws E {
        return of(mapper.map(this.collection));
    }

    public <E extends Throwable> Flow<T> filter(Filterer<T, E> filterer) throws E {
        return of(filterer.filter(this.collection));
    }

    public <R, E extends Throwable> R reduce(Reducer<T, R, E> reducer, R identity) throws E {
        return reducer.reduce(this.collection, identity);
    }

    public <E extends Throwable> Optional<T> fold(Folder<T, E> folder) throws E {
        return folder.fold(this.collection);
    }

    public Collection<T> collect() {
        return this.collection;
    }

    public static <T> Flow<T> of(Collection<T> collection) {
        return new Flow<>(collection);
    }

    public static <T> Flow<T> of(T[] array) {
        return new Flow<>(Arrays.asList(array));
    }
}
