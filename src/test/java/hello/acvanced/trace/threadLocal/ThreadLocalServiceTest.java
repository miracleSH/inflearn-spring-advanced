package hello.acvanced.trace.threadLocal;

import hello.acvanced.trace.threadLocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };

        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        // sleep(2000); // 동시성 문제 발생 x
        sleep(100);
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
