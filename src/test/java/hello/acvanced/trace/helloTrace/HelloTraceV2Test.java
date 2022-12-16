package hello.acvanced.trace.helloTrace;

import hello.acvanced.trace.TraceId;
import hello.acvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV2Test {

    @Test
    void begin_end(){
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus traceStatus1 = traceV2.begin("hello1");
        TraceStatus traceStatus2 = traceV2.beginSync(traceStatus1.getTraceId(), "hello2");
        traceV2.end(traceStatus2);
        traceV2.end(traceStatus1);
    }

    @Test
    void begin_exception(){
        HelloTraceV2 traceV1 = new HelloTraceV2();
        TraceStatus traceStatus = traceV1.begin("hello");
        traceV1.exception(traceStatus, new IllegalStateException());
    }
}
