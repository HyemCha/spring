package mvc.resnerrorcoach.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter
public class ApiResponse<T> {

    private Status status;
    private Metadata metadata;
    private List<T> results = new ArrayList<>();

    public ApiResponse(Status status, Metadata metadata, List<T> results) {
        this.status = status;
        this.metadata = metadata;
        this.results = results;
    }

    public ApiResponse(List<T> results) {
        this.status = new Status(ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.metadata = new Metadata(results.size());
        this.results = results;
    }

    public ApiResponse(int code, String message, int value) {
        this.status = new Status(code, message);
        this.metadata = new Metadata(results.size());
        this.results = (List<T>) Collections.singletonList(value);
    }

    public ApiResponse(int code, String message, List<T> integers) {
        this.status = new Status(code, message);
        this.metadata = new Metadata(results.size());
        this.results = integers;
    }


    @Getter @Setter @AllArgsConstructor
    private static class Status {
        private int code;
        private String message;
    }

    @Getter @Setter @AllArgsConstructor
    private static class Metadata {
        private int resultCount = 0;
    }


}
