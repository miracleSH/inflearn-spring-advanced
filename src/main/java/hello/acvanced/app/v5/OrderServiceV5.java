package hello.acvanced.app.v5;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.logTrace.LogTrace;
import hello.acvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {
    private final OrderRepositoryV5 repositoryV0;
    private final LogTrace logTrace;

    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(logTrace) {
            @Override
            protected Void call() {
                repositoryV0.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem");
    }
}
