package hello2.exception.exception2.api;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MultiListResponse {

    private int listCount;

    Map<String, List> gatheringLists = new HashMap<>();

    public void changeListCount(int listCount) {
        this.listCount = listCount;
    }

    public void addHello1() {
        gatheringLists.put("ongoing", new ArrayList<>(List.of(new Hello1(1L, "1th content"), new Hello1(2L, "2nd content"))));
    }

    public void addHello2() {
        gatheringLists.put("completion", new ArrayList<>(List.of(new Hello2(1L, "1th content"), new Hello2(2L, "2nd content"))));
    }

    public void addHello3() {
        gatheringLists.put("closed", new ArrayList<>(List.of(new Hello3(1L, "1th content"), new Hello3(2L, "2nd content"))));
    }
}
