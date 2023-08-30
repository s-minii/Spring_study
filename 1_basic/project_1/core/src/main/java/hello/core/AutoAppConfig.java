package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(

        // basePackages : 설정한 위치부터 검색하게 한다. (필요하지 않는 것들을 제외하고 검색하기 위해 사용)
        // 설정하지 않을 경우, 이 클래스의 패키지가 시작 위치가 된다.
        // 권장하는 방법 : 기본값으로 사용한다. (그럼 프로젝트 최상단에 두면 된다.)
        basePackages = "hello.core.member",

        // 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
        basePackageClasses = AutoAppConfig.class,

        // excludeFilters : spring에서 자동으로 스프링 빈을 등록해 주는데, 그 중 제외할 것 설정
        // classes = Configuration.class 를 제외함으로써 이전 예제와 충돌나지 않게 방지함.
        excludeFilters =  @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // MemoryMemberRepository 수동 빈 등록 테스트 (원래는 우선 순위 : 수동 빈 > 자동 빈)
    // 그런데, 최근 스프링 부트에서는 오류가 나게 바뀜!!
    // (우선 순위를 가지는 애매한 상황을 만들지 않고, 스프링 내에서 오류를 발생시켜서 애매한 상황 자체를 만들지 않음)
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }


}