package com.reyga.dev.services.base;

import com.reyga.dev.dto.content.base.BaseContentDto;
import com.reyga.dev.utils.ActivityConstruction;
import com.reyga.dev.utils.CommonLogger;

import java.util.function.Function;

public abstract class BaseActivityProcess<CONTENT extends BaseContentDto> {

    protected final ActivityConstruction activityConstruction;
    protected final CommonLogger logger;

    public BaseActivityProcess(ActivityConstruction activityConstruction, CommonLogger logger) {
        this.activityConstruction = activityConstruction;
        this.logger = logger;
    }

    protected <P> P construct(CONTENT contentDto, String id, String serviceName, Function<String, P> process) {
        constructPreActivity(contentDto, id, serviceName);
        P processActivity = process.apply(id);
        constructPostActivity(contentDto);
        return processActivity;
    }

    protected <SP> SP constructSubProcess(CONTENT contentDto, String id, String processName, Function<String, SP> process) {
        activityConstruction.addNewProcess(contentDto.getProcessList(), id, processName);
        return process.apply(id);
    }

    protected void constructPreActivity (CONTENT contentDto, String requestId, String serviceName) {
        activityConstruction.initProcess(contentDto.getActivityLog(), contentDto.getProcessList(), requestId, serviceName);
    }


    protected void constructPostActivity(CONTENT contentDto) {
        logger.info("Service Process : " + activityConstruction.getActivityLog(false, contentDto.getActivityLog()));
    }

}
