package hello.springmvc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {


    @RequestMapping("/log-test")
    public String LogTest(){
        String name = "Spring";
        
        // 심각도 정도 순서 (error가 가장 심각)
        // 로그레벨에서는 연산을 하지말자. (쓸모없는 리소스를 사용하기 떄문)
        // ex) log.trace("log : " + name) 과 같이 +를 사용하지 말자!
        log.trace("trace log ={}", name);
        log.debug("debug log ={}", name);
        log.info("info log ={}", name);
        log.warn("warn log ={}", name);
        log.error("error log ={}", name);
        return "ok";
    }
}