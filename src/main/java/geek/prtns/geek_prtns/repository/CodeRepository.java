package geek.prtns.geek_prtns.repository;

import geek.prtns.geek_prtns.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {
    Optional<CodeEntity> findFirstByOrderByCreateAtDesc();
}
