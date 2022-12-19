package hello.acvanced.trace.logTrace;

import hello.acvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    ThreadLocalTrace threadLocalTrace = new ThreadLocalTrace();

    @Test
    void begin_end_level2(){
        TraceStatus traceStatus1 = threadLocalTrace.begin("hello1");
        TraceStatus traceStatus2 = threadLocalTrace.begin("hello2");
        threadLocalTrace.end(traceStatus2);
        threadLocalTrace.end(traceStatus1);
    }

    @Test
    void begin_exception_level2(){
        TraceStatus traceStatus1 = threadLocalTrace.begin("hello1");
        TraceStatus traceStatus2 = threadLocalTrace.begin("hello2");
        threadLocalTrace.exception(traceStatus2, new IllegalStateException());
        threadLocalTrace.exception(traceStatus1, new IllegalStateException());
    }

}