package hello.core.Web;

import hello.core.Common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class LogDemoService {

    // request scope 빈 Provider 적용
    private final MyLogger myLogger;

    public LogDemoService(MyLogger myLogger) {
        this.myLogger = myLogger;
    }

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
