package com.czechowski.fromnewsapitoownapi.output.model.error;

import com.czechowski.fromnewsapitoownapi.output.exception.PaginationParameterException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class PaginationParameterError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;


    public PaginationParameterError(PaginationParameterException exception) {

        this.status = HttpStatus.BAD_REQUEST;
        this.timestamp = LocalDateTime.now();
        this.message = "Page or PageSize parameter is wrong - pageParameter= " + exception.getPageInString() + " pageSize paremeter= " + exception.getPageSizeInString();
        this.debugMessage = "Page and PageSize parameter must be an integer greater than or equal to 0 and less than " + Integer.MAX_VALUE;
    }

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
}
