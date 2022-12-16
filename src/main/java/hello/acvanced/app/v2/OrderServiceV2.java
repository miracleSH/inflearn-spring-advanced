package hello.acvanced.app.v2;

import hello.acvanced.trace.TraceId;
import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.helloTrace.HelloTraceV1;
import hello.acvanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 repositoryV0;
    private final HelloTraceV2 traceV2;

    public void orderItem(String itemId, TraceStatus traceStatus){
        try {
            traceStatus = traceV2.beginSync(traceStatus.getTraceId(), "OrderService.orderItem");
            repositoryV0.save(itemId, traceStatus);
            traceV2.end(traceStatus);
        }catch (Exception e){
            traceV2.exception(traceStatus, e);
            throw e;
        }
    }
}
