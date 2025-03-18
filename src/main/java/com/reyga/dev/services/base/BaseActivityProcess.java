package com.reyga.dev.services.base;

import com.reyga.dev.enumeration.ActivityType;
import com.reyga.dev.utils.CommonLogger;

import java.util.function.Function;

public abstract class BaseActivityProcess {

    protected final CommonLogger logger;

    public BaseActivityProcess(CommonLogger logger) {
        this.logger = logger;
    }

    protected <P> P process (String id, String serviceName, Function<String, P> process) {
        constructPreActivity(id, serviceName);
        P processActivity = process.apply(id);
        constructPostActivity();
        return processActivity;
    }

    protected <SP> SP subProcess(String id, String processName, Function<String, SP> process) {
        ActivityType.INSTANCE.addProcess(id, processName);
        return process.apply(id);
    }

    protected void constructPreActivity (String requestId, String serviceName) {
        ActivityType.INSTANCE.addRequestAndService(requestId, serviceName);
    }


    protected void constructPostActivity() {
        logger.info("Service Process : " + ActivityType.INSTANCE.getActivityLog());
    }

}
