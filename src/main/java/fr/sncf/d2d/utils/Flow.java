package fr.sncf.d2d.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public final class Flow {

    private final Collection collection;

    private Flow(Collection collection) {
        this.collection = collection;
    }

    public Flow map(Mapper mapper) {
        return of(mapper.map(this.collection));
    }

    public Flow filter(Filterer filterer) {
        return of(filterer.filter(this.collection));
    }

    public Object reduce(Reducer reducer, Object identity) {
        return reducer.reduce(this.collection, identity);
    }

    public Optional fold(Folder folder) {
        return folder.fold(this.collection);
    }

    public Collection collect() {
        return this.collection;
    }

    public static Flow of(Collection collection) {
        return new Flow(collection);
    }

    public static Flow of(Object[] array) {
        return new Flow(Arrays.asList(array));
    }
}
