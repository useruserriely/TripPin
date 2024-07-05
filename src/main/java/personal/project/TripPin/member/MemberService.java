package personal.project.TripPin.member;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.project.TripPin.DataNotFoundException;
import personal.project.TripPin.plan.Plan;
import personal.project.TripPin.plan.PlanRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;

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


}
