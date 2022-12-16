package hello.acvanced.app.v1;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 traceV1;

    public void save(String itemId){
        TraceStatus traceStatus = null;
        try{
            traceStatus = traceV1.begin("OrderRepository.save()");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            traceV1.end(traceStatus);
        }catch (Exception e){
            traceV1.exception(traceStatus, e);
        }
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
