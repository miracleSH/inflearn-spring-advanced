package hello.acvanced.trace.logTrace;

import hello.acvanced.trace.TraceId;
import hello.acvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X--";
    private ThreadLocal<TraceId> traceIdStore = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdStore.get();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);

    }

    private void syncTraceId(){
        if(traceIdStore.get() == null){
            traceIdStore.set(new TraceId());
        }else{
            traceIdStore.set(traceIdStore.get().createNextId());
        }
    }

    @Override
    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    @Override
    public void exception(TraceStatus traceStatus, Exception e) {
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
        releaseTraceId();
    }

    private void releaseTraceId() {
        if(traceIdStore.get().isZeroLevel()){
            traceIdStore.remove(); //destroy
        }else {
            traceIdStore.set(traceIdStore.get().createPreviousId());
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
