package currencyrate.utils;

import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Logger {
    private final org.slf4j.Logger logger;

    private Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public static Logger getLogger(Class aClass) {
        return new Logger(LoggerFactory.getLogger(aClass));
    }

    public void debug(Supplier<String> msgSupplier) {
        if (logger.isDebugEnabled()) {
            logger.debug(msgSupplier.get());
        }
    }

    public void error(Supplier<String> msgSupplier) {
        if (logger.isErrorEnabled()) {
            logger.error(msgSupplier.get());
        }
    }

    public void info(Supplier<String> msgSupplier) {
        if (logger.isInfoEnabled()) {
            logger.info(msgSupplier.get());
        }
    }

    public void trace(Supplier<String> msgSupplier) {
        if (logger.isTraceEnabled()) {
            logger.trace(msgSupplier.get());
        }
    }

    public void warn(Supplier<String> msgSupplier) {
        if (logger.isWarnEnabled()) {
            logger.trace(msgSupplier.get());
        }
    }

    public void error(Supplier<String> msgSupplier, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error(msgSupplier.get(), throwable);
        }
    }
}
