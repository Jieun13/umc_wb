package umc.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
