package gtceugbh.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gtceugbh.Tags;

public class GBHLogger {

    public static Logger log = LogManager.getLogger(Tags.MODNAME);

    private GBHLogger() {}

    public static void init(@NotNull Logger modLogger) {
        log = modLogger;
    }
}
