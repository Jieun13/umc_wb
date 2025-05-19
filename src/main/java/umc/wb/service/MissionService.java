package umc.wb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.wb.domain.Member;
import umc.wb.domain.Mission;
import umc.wb.domain.Restaurant;
import umc.wb.mapper.MissionMapper;
import umc.wb.repository.MissionRepository;
import umc.wb.repository.RestaurantRepository.RestaurantRepository;
import umc.wb.web.dto.MissionRequest;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Mission CreateMission(MissionRequest.CreateMissionRequest request, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new IllegalArgumentException("Restaurant not found"));
        Mission mission = MissionMapper.toMission(request, restaurant);
        return missionRepository.save(mission);
    }

    public boolean isExist(Long missionId) {
        return missionRepository.existsById(missionId);
    }

    public Mission findById(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(() -> new IllegalArgumentException("Mission not found"));
    }

    public Page<Mission> getAllByRestaurant(Long restaurantId, Integer page) {
        return missionRepository.findAllByRestaurantId(restaurantId, PageRequest.of(page, 10));
    }
}
