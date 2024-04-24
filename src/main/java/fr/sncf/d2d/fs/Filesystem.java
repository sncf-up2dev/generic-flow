package fr.sncf.d2d.fs;

import fr.sncf.d2d.utils.Flow;

import java.io.File;
import java.io.IOException;

public class Filesystem {

    public static long size(String path) {
        return size(new File(path));
    }

    public static long size(File file) {
        if (!file.exists()) {
            throw new RuntimeException("File does not exist: %s".formatted(file));
        }
        File[] subFiles = file.listFiles();
        if (subFiles == null) {
            return file.length();
        }
        return Flow.of(subFiles)
                .map(Filesystem::size)
                .fold(Long::sum)
                .orElse(0L);
    }
}
