package umc.wb.mapper;

import org.springframework.data.domain.Page;
import umc.wb.domain.Mission;
import umc.wb.domain.Restaurant;
import umc.wb.web.dto.MissionRequest;
import umc.wb.web.dto.MissionResponse;
import umc.wb.web.dto.ReviewResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MissionMapper {

    public static Mission toMission(MissionRequest.CreateMissionRequest request, Restaurant restaurant) {

        return Mission.builder()
                .name(request.getName())
                .spentAmount(request.getSpentAmount())
                .rewardPoints(request.getRewardPoints())
                .dueDate(request.getDueDate())
                .restaurant(restaurant)
                .build();
    }

    public static MissionResponse.CreateMissionResult toResponse(Mission mission) {
        return MissionResponse.CreateMissionResult.builder()
                .name(mission.getName())
                .spentAmount(mission.getSpentAmount())
                .dueDate(mission.getDueDate())
                .RestaurantName(mission.getRestaurant().getName())
                .rewardPoints(mission.getRewardPoints())
                .build();
    }

    public static MissionResponse.MissionView toView(Mission mission) {
        return MissionResponse.MissionView.builder()
                .name(mission.getName())
                .spentAmount(mission.getSpentAmount())
                .dueDate(mission.getDueDate())
                .rewardPoints(mission.getRewardPoints())
                .RestaurantName(mission.getRestaurant().getName())
                .build();
    }

    public static MissionResponse.MissionViewList toViewList(Page<Mission> missions) {
        List<MissionResponse.MissionView> missionViews = missions.stream()
                .map(MissionMapper::toView).collect(Collectors.toList());

        return MissionResponse.MissionViewList.builder()
                .missions(missionViews)
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .listSize(missions.getNumberOfElements())
                .totalPage(missions.getTotalPages())
                .build();
    }
}
