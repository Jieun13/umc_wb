package umc.wb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wb.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public boolean allCategoriesExist(List<Long> ids) {
        return ids.stream().allMatch(categoryRepository::existsById);
    }
}
