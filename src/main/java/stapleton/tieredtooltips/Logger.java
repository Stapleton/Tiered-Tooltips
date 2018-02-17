package stapleton.tieredtooltips;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Logger {
    private org.apache.logging.log4j.Logger logger;

    public Logger(String loggerName) {
        this.logger = LogManager.getLogger(loggerName);
    }

    private void log(Level logLevel, Object obj) {
        logger.log(logLevel, obj);
    }

    public void all(Object obj) {
        log(Level.ALL, obj);
    }

    public void debug(Object obj) {
        log(Level.DEBUG, obj);
    }

    public void trace(Object obj) {
        log(Level.TRACE, obj);
    }

    public void fatal(Object obj) {
        log(Level.FATAL, obj);
    }

    public void error(Object obj) {
        log(Level.ERROR, obj);
    }

    public void warn(Object obj) {
        log(Level.WARN, obj);
    }

    public void info(Object obj) {
        log(Level.INFO, obj);
    }

    public void off(Object obj) {
        log(Level.OFF, obj);
    }
}