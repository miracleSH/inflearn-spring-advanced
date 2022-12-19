package hello.acvanced;

import hello.acvanced.trace.logTrace.FieldLogTrace;
import hello.acvanced.trace.logTrace.LogTrace;
import hello.acvanced.trace.logTrace.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalTrace();
    }
}
