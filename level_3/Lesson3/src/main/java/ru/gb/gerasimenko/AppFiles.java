package ru.gb.gerasimenko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// Better Files
public class AppFiles
{
    public static void main(String[] args) {
        Path path = Paths.get("/home/evgen/prog/javaProjects/gbJava/level_3/Lesson3");
        List<Path> result = null;
        try (final Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Path path1 : result) {
            System.out.println(path1.getFileName());
        }
    }
}
