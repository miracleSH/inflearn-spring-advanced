package hello.acvanced.app.v2;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.helloTrace.HelloTraceV1;
import hello.acvanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 traceV2;

    @GetMapping("/v2/request")
    public String request(String itemId){

        TraceStatus traceStatus = null;
        try {
            traceStatus = traceV2.begin("OrderController.request()");
            orderService.orderItem(itemId, traceStatus);
            traceV2.end(traceStatus);
        }catch (Exception e){
            traceV2.exception(traceStatus, e);
            throw e;
        }
        return "Ok";
    }
}
