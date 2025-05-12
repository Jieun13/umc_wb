package umc.wb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import umc.wb.domain.Mission;
import umc.wb.domain.Restaurant;
import umc.wb.mapper.MissionMapper;
import umc.wb.repository.MissionRepository;
import umc.wb.web.dto.MissionRequest;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional
    public Mission CreateMission(MissionRequest.CreateMissionRequest request, Restaurant restaurant) {
        Mission mission = MissionMapper.toMission(request, restaurant);
        return missionRepository.save(mission);
    }

    public boolean isExist(Long missionId) {
        return missionRepository.existsById(missionId);
    }

    public Mission findById(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(() -> new IllegalArgumentException("Mission not found"));
    }
}
