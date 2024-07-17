package personal.project.TripPin.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByEmail(String email);

    List<Member> findByNicknameContainingIgnoreCase(String keyword);

    Optional<Member> findByResetToken(String resetToken);
}
