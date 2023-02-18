package Hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//콜백 인터페이스 : implements InitializingBean, DisposableBean
public class NetWorkClient{
    private String url;

    public NetWorkClient() {
        System.out.println("생성자 호출, url = " + url);

        //afterPropertiesSet에서 해줌
        //connect();
        //call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }
    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //"의존주입이 끝나면 호출해주겠다"
//    @Override
//    public void afterPropertiesSet() throws Exception
    @PostConstruct
    public void init() {
        System.out.println("NetWorkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //bean이 종료되기 직전에 호출
//    @Override
//    public void destroy() throws Exception
    @PreDestroy
    public void close() {
        System.out.println("NetWorkClient.close");
        disconnect();
    }
}
