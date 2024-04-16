package hello2.exception.exception2.api;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public MultiListResponse getMultiList() {
        MultiListResponse multiListResponse = new MultiListResponse();
        multiListResponse.changeListCount(2);
        multiListResponse.addHello1();
        multiListResponse.addHello3();
        return multiListResponse;
    }
}
