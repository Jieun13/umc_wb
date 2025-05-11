package umc.wb.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wb.domain.Category;
import umc.wb.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public boolean allCategoriesExist(List<Long> ids) {
        return ids.stream().allMatch(categoryRepository::existsById);
    }

    public Category findById(@NotNull Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public boolean isExist(Long id) {
        return categoryRepository.findById(id).isPresent();
    }
}
