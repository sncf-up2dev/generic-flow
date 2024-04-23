package fr.sncf.d2d.str;

import fr.sncf.d2d.utils.Flow;

import java.text.Normalizer;
import java.util.List;

public class StringUtils {

    public static String slugify(String original) {
        try {
            return (String) Flow.of(StringUtils.split(original))
                    .filter(o -> !((String) o).isBlank())
                    .map(o -> StringUtils.removePunctuations((String) o))
                    .map(o -> StringUtils.stripAccents((String) o))
                    .map(o -> ((String) o).toLowerCase())
                    .fold((s1, s2) -> s1 + "-" + s2)
                    .orElse("");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String stripAccents(String original) {
        return Normalizer.normalize(original, Normalizer.Form.NFKD).replaceAll("\\P{ASCII}+", "");
    }

    public static String removePunctuations(String original) {
        return original.replaceAll("\\p{Punct}+", "");
    }

    public static List<String> split(String original) {
        return List.of(original.split("[\\s\\-_]+"));
    }
}
