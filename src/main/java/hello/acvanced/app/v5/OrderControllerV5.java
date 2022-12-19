package hello.acvanced.app.v5;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.logTrace.LogTrace;
import hello.acvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v5/request")
    public String request(String itemId){
        AbstractTemplate<String> template = new AbstractTemplate<>(logTrace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}
