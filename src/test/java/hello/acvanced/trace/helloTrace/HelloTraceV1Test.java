package hello.acvanced.trace.helloTrace;

import hello.acvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV1Test {

    @Test
    void begin_end(){
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus traceStatus = traceV1.begin("hello");
        traceV1.end(traceStatus);
    }

    @Test
    void begin_exception(){
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus traceStatus = traceV1.begin("hello");
        traceV1.exception(traceStatus, new IllegalStateException());
    }

}
