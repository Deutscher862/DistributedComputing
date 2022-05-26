package server.main;

import java.util.logging.Level;
import java.util.logging.Logger;

class ServerLogger {
    private static final Logger logger = Logger.getLogger(ServerLogger.class.getName());

    static void log(Level level, String message) {
        logger.log(level, message);
    }
}
