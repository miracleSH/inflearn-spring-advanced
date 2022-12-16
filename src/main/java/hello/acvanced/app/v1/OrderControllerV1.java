package hello.acvanced.app.v1;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 traceV1;

    @GetMapping("/v1/request")
    public String request(String itemId){

        TraceStatus traceStatus = null;
        try {
            traceStatus = traceV1.begin("OrderController.request()");
            orderService.orderItem(itemId);
            traceV1.end(traceStatus);
        }catch (Exception e){
            traceV1.exception(traceStatus, e);
            throw e;
        }
        return "Ok";
    }
}
