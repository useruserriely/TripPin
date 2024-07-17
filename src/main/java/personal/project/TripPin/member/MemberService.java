package personal.project.TripPin.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.project.TripPin.DataNotFoundException;
import personal.project.TripPin.plan.Plan;
import personal.project.TripPin.plan.PlanRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;
    private final EmailService emailService;

    @Transactional
    public Member save(String loginId, String password, String email, String nickname, String phone) {
        // 아이디 중복 체크
        if (memberRepository.findByLoginId(loginId).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
        // 이메일 중복 체크 (필요시 추가)
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(encodedPassword);
        member.setEmail(email);
        member.setNickname(nickname);
        member.setPhone(phone);

        // Member 저장
        return memberRepository.save(member);
    }

    public Member getMember(String loginId) {
        Optional<Member> member = this.memberRepository.findByLoginId(loginId);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> getSearchList(String keyword) {
        return memberRepository.findByNicknameContainingIgnoreCase(keyword);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public void sendPasswordResetLink(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("member not found"));

        // 비밀번호 재설정 링크 생성 로직 (예: JWT 토큰 또는 UUID)
        String resetToken = generateResetToken();

        // 비밀번호 재설정 링크
        String resetLink = "http://yourdomain.com/reset-password?token=" + resetToken;

        log.info("Sending password reset link to email: {}", email);
        log.info("Reset link: {}", resetLink);

        // 이메일 전송
        emailService.sendEmail(email, "비밀번호 재설정", "비밀번호를 재설정하려면 다음 링크를 클릭하세요: " + resetLink);

        // 토큰 저장 로직 (선택)
        saveResetToken(member, resetToken);
    }

    private String generateResetToken() {
        // 토큰 생성 로직 구현
        return UUID.randomUUID().toString();
    }
    @Transactional
    private void saveResetToken(Member member, String resetToken) {
        member.setResetToken(resetToken);

        member.setResetTokenExpiration(LocalDateTime.now().plusHours(1)); // 토큰 유효 기간 설정
        memberRepository.save(member);
    }

    public void resetPassword(String token, String newPassword) {
        Member member = validateResetToken(token);

        String encodedPassword = passwordEncoder.encode(newPassword);
        member.setPassword(encodedPassword);
        member.setResetToken(null); // 토큰 초기화
        member.setResetTokenExpiration(null); // 토큰 유효 기간 초기화
        memberRepository.save(member);
    }

    private Member validateResetToken(String token) {
        Member member = memberRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (member.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token has expired");
        }

        return member;
    }
}

