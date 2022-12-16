package hello.acvanced.app.v1;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 repositoryV0;
    private final HelloTraceV1 traceV1;

    public void orderItem(String itemId){
        TraceStatus traceStatus = null;
        try {
            traceStatus = traceV1.begin("OrderService1.orderItem");
            repositoryV0.save(itemId);
            traceV1.end(traceStatus);
        }catch (Exception e){
            traceV1.exception(traceStatus, e);
            throw e;
        }
    }
}
