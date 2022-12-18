package hello.acvanced.trace.logTrace;

import hello.acvanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus traceStatus);
    void exception(TraceStatus traceStatus, Exception e);
}
