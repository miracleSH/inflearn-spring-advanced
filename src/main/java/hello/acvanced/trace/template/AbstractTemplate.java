package hello.acvanced.trace.template;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.logTrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace logTrace;

    public AbstractTemplate(LogTrace logTrace){
        this.logTrace = logTrace;
    }

    public T execute(String message){
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin(message);
            T result = call();
            logTrace.end(traceStatus);
            return result;
        }catch (Exception e){
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }

    protected abstract T call();

}
