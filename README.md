# Exercice Généricité

Le code fourni présente la classe `Flow` qui reproduit certaine fonctionnalité de `java.util.stream.Stream`, d'un point de vue fonctionnel en non pas algorithmique.

Comme vous pouvez remarquer, ni la classe ni aucune de ses méthodes n'a de paramètre de type.

La classe est utilisée dans deux exemples :
* Dans la classe utilitaire `StringUtils` elle est utilisée pour produire le slug à partir d'une chaîne de caractères.
* Dans la classe utilitaire `Filesystem` elle est utilisée pour calculer la taille d'un fichier ou un dossier.

La réalisation de deux example n'est pas facile à lire à cause de manque de type explicite dans la classe `Flow`.

Idéalement, la réalisation de la méthode `StringUtils.slugify` devrait être aussi simple que le suivant.

````java
    public static String slugify(String original) {
        return Flow.of(StringUtils.split(original))
                .filter(s -> !s.isBlank())
                .map(StringUtils::removePunctuations)
                .map(StringUtils::stripAccents)
                .map(String::toLowerCase)
                .fold((s1, s2) -> s1 + "-" + s2)
                .orElse("");
    }
````

La méthode `Filesystem.size` devrait être réalisée ainsi.

````java
    public static long size(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("File does not exist: %s".formatted(file));
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
````

Modifier la classe `Flow` et ses méthodes pour qu'elles prennent les paramètres de type adéquats pour simplifier la réalisation de deux exemples.

Pour commencer, vous pouvez vous mettre sur la branche `start`.
Le code ne compilera plus, mais vous aurez les versions simplifiées des deux exemples.
L'objectif est donc de faire en sorte que le code compile bien et que l'application se lance correctement.