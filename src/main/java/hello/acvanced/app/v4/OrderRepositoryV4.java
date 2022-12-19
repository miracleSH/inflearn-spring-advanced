package hello.acvanced.app.v4;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace logTrace;

    public void save(String itemId){
        TraceStatus traceStatus = null;
        try{
            traceStatus = logTrace.begin("OrderRepository.save()");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            logTrace.end(traceStatus);
        }catch (Exception e){
            logTrace.exception(traceStatus, e);
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
