package com.czechowski.fromnewsapitoownapi.output.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public abstract class AbstractUnsupportedError {

    protected HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected LocalDateTime timestamp;
    protected String message;
    protected String debugMessage;
    protected Map<String, Set<String>> additionalInfo = new HashMap();

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public Map<String, Set<String>> getAdditionalInfo() {
        return additionalInfo;
    }
}
