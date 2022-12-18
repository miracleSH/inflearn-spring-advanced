package hello.acvanced.app.v3;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.helloTrace.HelloTraceV2;
import hello.acvanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 repositoryV0;
    private final LogTrace logTrace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem");
            repositoryV0.save(itemId);
            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
