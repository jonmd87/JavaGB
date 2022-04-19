package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
Level of Logger serve <- warning <- info <- config <- fine <- finer <- finest
 */

public class AppLogger {
    public static final Logger logger = Logger.getLogger(AppLogger.class.getName());
    public static void main( String[] args ) {
        logger.warning("program started");
        logger.config("program midle"); // по умолчанию уровень info все что ниже не выводится
        logger.info("program finished");

        // логирование exception
        try {
            // some work
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Message ", e); //
        }
    }
}

