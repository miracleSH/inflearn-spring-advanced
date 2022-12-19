package hello.acvanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    // 변하지 않는 부분은 여기에 작성
    public void execute(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직실행
        call(); //상속
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    //변하는 부분은 상속해서 개발
    protected abstract void call();


}
