package hello.core.Web;


import hello.core.Common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LogDemoController {

    private final MyLogger myLogger;
    private final LogDemoService logDemoService;

    public LogDemoController(MyLogger myLogger, LogDemoService logDemoService) {
        this.myLogger = myLogger;
        this.logDemoService = logDemoService;

        System.out.println("myLogger = " + myLogger.getClass());
    }

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
