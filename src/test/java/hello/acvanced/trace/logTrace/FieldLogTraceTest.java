package hello.acvanced.trace.logTrace;

import hello.acvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {

    FieldLogTrace fieldLogTrace = new FieldLogTrace();

    @Test
    void begin_end_level2(){
        TraceStatus traceStatus1 = fieldLogTrace.begin("hello1");
        TraceStatus traceStatus2 = fieldLogTrace.begin("hello2");
        fieldLogTrace.end(traceStatus2);
        fieldLogTrace.end(traceStatus1);
    }

    @Test
    void begin_exception_level2(){
        TraceStatus traceStatus1 = fieldLogTrace.begin("hello1");
        TraceStatus traceStatus2 = fieldLogTrace.begin("hello2");
        fieldLogTrace.exception(traceStatus2, new IllegalStateException());
        fieldLogTrace.exception(traceStatus1, new IllegalStateException());
    }

}