package hello.acvanced.app.v4;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 repositoryV0;
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
