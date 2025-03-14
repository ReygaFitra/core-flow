package com.reyga.dev.services.base;

import com.reyga.dev.enumeration.ActivityKey;
import com.reyga.dev.enumeration.ActivityType;
import com.reyga.dev.utils.CommonLogger;
import org.slf4j.MDC;

import java.util.function.Function;

public abstract class BaseActivityProcess<R> {

    protected final CommonLogger logger;

    public BaseActivityProcess(CommonLogger logger) {
        this.logger = logger;
    }

    protected void constructPreActivity (String processId, String serviceName) {
        ActivityType.INSTANCE.addActivities(ActivityKey.PROCESS_ID.getKeyName(), processId);
        ActivityType.INSTANCE.addActivities(ActivityKey.SERVICE_NAME.getKeyName(), serviceName);
        MDC.put(ActivityKey.PROCESS_ID.getKeyName(), processId);
    }

    protected R process (String processId, String serviceName, Function<String, R> process) {
        constructPreActivity(processId, serviceName);
        R processActivity = process.apply(processId);
        constructPostActivity();
        return processActivity;
    }

    protected void constructPostActivity() {
        logger.info(ActivityType.INSTANCE.getAllActivities());
    }

}
