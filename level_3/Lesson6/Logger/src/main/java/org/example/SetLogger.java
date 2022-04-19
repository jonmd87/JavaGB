package org.example;

import java.io.IOException;
import java.util.logging.*;

import static org.example.AppLogger.logger;

// насторойка логера из кода (не рекомендуется так делать)
public class SetLogger {
    public static void main(String[] args) {
        Handler h1 = null; // хэндлер для вывода в файл
        Handler h2 = null; // хэндлер для вывода в другое место, например в консоль
        logger.setLevel(Level.FINE);
        try {
            h1 = new FileHandler("myLoggerFile.txt"); // указываем файл логирования
             h1.setFormatter(new SimpleFormatter()); // настройка формата вывода
            logger.addHandler(h1);            // добавляем хэндлер в логгер
            h1.setLevel(Level.ALL);            // setup logger level

            h2 = new ConsoleHandler();        // создаем консольхэндлер
            h2.setFormatter(new XMLFormatter()); // настройка формата вывода(в XML формате)
            logger.addHandler(h2);               // добавляем хэндлер в логгер
            h2.setLevel(Level.FINE);      // setup logger level
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.warning("program started");
        logger.config("program midle"); // по умолчанию уровень info все что ниже не выводится
        logger.info("program finished");
    }
}
