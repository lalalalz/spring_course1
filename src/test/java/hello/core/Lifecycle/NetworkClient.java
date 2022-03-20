package hello.core.Lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 연결이 된 이후에 요청함수
    public void call(String message) {
        System.out.println("call: " + this.url + "message = " + message);
    }

    // 연결작업
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 연결 끊는 작업
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void myInitMethod() {
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void myDestroyMethod() {
        disconnect();
    }
}
