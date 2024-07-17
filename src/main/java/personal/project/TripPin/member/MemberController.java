package personal.project.TripPin.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.project.TripPin.DataNotFoundException;
import personal.project.TripPin.security.MyUserDetailService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MyUserDetailService myUserDetailService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/signup")
    public String signupForm(MemberForm memberForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        if (!memberForm.getPassword().equals(memberForm.getPassword2())) {
            bindingResult.rejectValue("password2", "error.memberForm", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        // 회원 가입 처리
        try {
            memberService.save(memberForm.getLoginId(), memberForm.getPassword(), memberForm.getEmail(), memberForm.getNickname(), memberForm.getPhone());
        } catch (RuntimeException e) {
            bindingResult.rejectValue("loginId", "error.memberForm", e.getMessage());
            return "signup"; // 예외 발생 시 다시 회원가입 폼을 보여줌
        }

        // 회원가입 성공 후 로그인 페이지로 이동
        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String username, @RequestParam String password) {
        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

        if (userDetails == null) {
            // 사용자가 없는 경우에 대한 처리
            model.addAttribute("error", "존재하지 않는 아이디입니다.");
            return "login";
        }

        // 비밀번호가 일치하지 않는 경우에 대한 처리
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "login";
        }

        // 인증 성공한 경우에 대한 처리
        return "redirect:/";
    }

    @GetMapping("/find-id")
    public String findIdForm() {
        return "find_id";
    }

    @PostMapping("/find-id")
    public String findId(@RequestParam String email, Model model) {
        try {
            memberService.sendPasswordResetLink(email);
            model.addAttribute("message", "비밀번호 재설정 링크가 이메일로 전송되었습니다.");
        } catch (DataNotFoundException e) {
            model.addAttribute("error", "해당 이메일로 등록된 계정이 없습니다.");
        }
        return "find_password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam String token, Model model) {
        log.info("Accessing reset password form with token: {}", token);
        model.addAttribute("token", token);
        return "reset_password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String password, @RequestParam String password2, Model model) {
        log.info("Resetting password with token: {}", token);
        if (!password.equals(password2)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "reset_password";
        }

        try {
            memberService.resetPassword(token, password);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "reset_password";
        }
    }
}
