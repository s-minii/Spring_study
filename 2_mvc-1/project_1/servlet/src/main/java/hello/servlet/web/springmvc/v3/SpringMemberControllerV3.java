package hello.servlet.web.springmvc.v3;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // ("/springmvc/v2/members/new-form")
    @GetMapping("/new-form")
    public String newForm() {
        return"new-form";
    }

    // ("/springmvc/v2/members/save")
    @PostMapping("save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model){

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    // ("/springmvc/v2/members")
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("member", members);

        return "members";
    }
}