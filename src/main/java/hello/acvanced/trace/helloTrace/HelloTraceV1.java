package hello.acvanced.trace.helloTrace;

import hello.acvanced.trace.TraceId;
import hello.acvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X--";

    public TraceStatus begin(String message){
        TraceId traceId =  new TraceId();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    //V2에서 만듦
    public TraceStatus beginSync(TraceId beforeTraceId, String message){
        TraceId traceId =  beforeTraceId.createNextId();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    public void end(TraceStatus traceStatus){
        complete(traceStatus, null);
    }

    public void exception(TraceStatus traceStatus, Exception e){
        complete(traceStatus, e);
    }

    private void complete(TraceStatus traceStatus, Exception e){
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - traceStatus.getStartTimeMs();
        TraceId traceId = traceStatus.getTraceId();
        if(e == null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), traceStatus.getMessage(), resultTimeMs);
        }else{
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), traceStatus.getMessage(), resultTimeMs, e.toString());
        }
    }

    private String addSpace(String prefix, int level){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level; i++){
            stringBuilder.append((i == level - 1)? "|" + prefix : "|    ");
        }
        return stringBuilder.toString();
    }

}
