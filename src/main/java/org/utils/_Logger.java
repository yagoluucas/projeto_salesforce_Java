package org.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.message.SimpleMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// esta interface de _LOGGER foi feita para todas as classes de repository para registrar logs
public interface _Logger<T>{
    Logger LOGGER = LogManager.getLogger(_Logger.class);
    PatternLayout layout = PatternLayout.newBuilder().
            withPattern("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n").build();
    public default void logInfo(String message){
        LOGGER.info(message);
        saveLog(formatadeLog(Level.INFO, message));
    }
    public default void logWarn(String message){
        LOGGER.warn(message);
        saveLog(formatadeLog(Level.WARN, message));
    }
    public default void logError(String message){
        LOGGER.error(message);
        saveLog(formatadeLog(Level.ERROR, message));
    }

    // este método é responsável por salvar o log em um arquivo .log
    private void saveLog(String message) {
        try {
            File file = new File("logs\\salesforce.log");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(message);
            fileWriter.close();
        }catch (IOException e) {
            logError("Erro ao salvar log: " + e.getMessage());
        }
    }
    private String formatadeLog(Level level, String message) {
        LogEvent event = Log4jLogEvent.newBuilder()
                .setLoggerName(LOGGER.getName())
                .setLoggerFqcn(LOGGER.getClass().getName())
                .setLevel(level)
                .setMessage(new SimpleMessage(message))
                .build();
        return layout.toSerializable(event);
    }
}
