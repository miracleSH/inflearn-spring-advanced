package hello.acvanced.app.v4;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v4/request")
    public String request(String itemId){
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            logTrace.end(traceStatus);
        }catch (Exception e){
            logTrace.exception(traceStatus, e);
            throw e;
        }
        return "Ok";
    }
}
