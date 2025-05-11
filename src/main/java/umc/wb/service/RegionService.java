package umc.wb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wb.domain.Region;
import umc.wb.repository.RegionRepository;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public boolean isRegionExist(Long id) {
        return regionRepository.existsById(id);
    }

    public Region findById(Long regionId) {
        return regionRepository.findById(regionId).orElse(null);
    }
}
