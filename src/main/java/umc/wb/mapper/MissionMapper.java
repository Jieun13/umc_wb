package umc.wb.mapper;

import umc.wb.domain.Mission;
import umc.wb.domain.Restaurant;
import umc.wb.web.dto.MissionRequest;
import umc.wb.web.dto.MissionResponse;

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
}
