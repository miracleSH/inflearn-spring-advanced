package hello.acvanced.app.v5;

import hello.acvanced.trace.TraceStatus;
import hello.acvanced.trace.logTrace.LogTrace;
import hello.acvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {

    private final LogTrace logTrace;

    public void save(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(logTrace) {
            @Override
            protected Void call() {
                if(itemId.equals("ex")){
                    throw new IllegalStateException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
