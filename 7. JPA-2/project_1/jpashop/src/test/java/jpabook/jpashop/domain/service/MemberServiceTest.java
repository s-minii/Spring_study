package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false) // DB에 들어가는지 확인
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

        Assert.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class) // IllegalStateException 예외가 발생하면 테스트 성공
    public void 중복회원예외() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        memberService.join(member2); // 예외 발생

        fail("예외가 발생해야 한다."); // 이 코드가 실행되면 테스트가 실패된다.
    }
}