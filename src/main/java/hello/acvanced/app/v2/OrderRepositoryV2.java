package hello.acvanced.app.v2;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.helloTrace.HelloTraceV1;
import hello.acvanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 traceV2;

    public void save(String itemId, TraceStatus traceStatus){
        try{
            traceStatus = traceV2.beginSync(traceStatus.getTraceId(),"OrderRepository.save()");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            traceV2.end(traceStatus);
        }catch (Exception e){
            traceV2.exception(traceStatus, e);
            throw e;
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
