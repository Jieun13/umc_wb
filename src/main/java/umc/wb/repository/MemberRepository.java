package umc.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
