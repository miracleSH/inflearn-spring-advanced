package hello.acvanced.trace;

import lombok.Getter;

@Getter
public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public void setTraceId(TraceId traceId) {
        this.traceId = traceId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
