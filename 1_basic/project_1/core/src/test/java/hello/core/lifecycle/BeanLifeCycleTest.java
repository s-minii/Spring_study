package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        /*
         @Bean(initMethod = "init", destroyMethod = "close")
         언제 사용하냐 ? : 외부 라이브러리를 초기화, 종료해야하는 경우
         destroyMethod는 close랑 shutdown 이름의 메서드를 자동으로 호출해준다. (추론 기능이 있다.)
         추론 기능을 수행하기 싫으면 destroyMethod ="" 처럼 빈 공백을 지정하면 된다.
        */

        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}