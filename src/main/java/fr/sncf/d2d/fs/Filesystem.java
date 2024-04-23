package fr.sncf.d2d.fs;

import fr.sncf.d2d.utils.Flow;

import java.io.File;
import java.io.IOException;

public class Filesystem {

    public static long size(String path) throws IOException {
        return size(new File(path));
    }

    public static long size(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("File does not exist: %s".formatted(file));
        }
        File[] subFiles = file.listFiles();
        if (subFiles == null) {
            return file.length();
        }
        try {
            return (Long) Flow.of(subFiles)
                    .map(o -> Filesystem.size((File) o))
                    .fold((o1, o2) -> (long) o1 + (long) o2)
                    .orElse(0L);
        } catch (Throwable throwable) {
            throw (IOException) throwable;
        }
    }
}
