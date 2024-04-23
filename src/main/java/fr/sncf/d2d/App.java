package fr.sncf.d2d;

import fr.sncf.d2d.fs.Filesystem;
import fr.sncf.d2d.str.StringUtils;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.printf("Current directory size %d bytes%n", Filesystem.size("."));
        System.out.printf("Hello World slugified %s%n", StringUtils.slugify("Hello World"));
    }
}
