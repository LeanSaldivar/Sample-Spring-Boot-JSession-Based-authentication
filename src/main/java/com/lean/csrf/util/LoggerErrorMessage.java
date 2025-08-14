package com.lean.csrf.util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

@UtilityClass
public class LoggerErrorMessage {

    public void logError(Logger logger, Exception ex, HttpServletRequest request) {
        logger.error(buildLogMessage(ex, request), ex);
    }

    public void logWarn(Logger logger, String message, HttpServletRequest request) {
        logger.warn(buildSimpleLogMessage(message, request));
    }

    public void logInfo(Logger logger, String message, HttpServletRequest request) {
        logger.info(buildSimpleLogMessage(message, request));
    }

    private String buildLogMessage(Exception ex, HttpServletRequest request) {
        return String.format("Exception: %s | Message: %s | Path: %s",
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getRequestURI());
    }

    private String buildSimpleLogMessage(String message, HttpServletRequest request) {
        return String.format("Message: %s | Path: %s", message, request.getRequestURI());
    }
}
