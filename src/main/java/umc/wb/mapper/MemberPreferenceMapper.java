package umc.wb.mapper;

import umc.wb.domain.Category;
import umc.wb.domain.Member;
import umc.wb.domain.MemberPreference;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferenceMapper {

    public static List<MemberPreference> toMemberPreferences(List<Category> categories) {
        return categories.stream()
                .map(category ->
                        MemberPreference.builder()
                                .build()
                ).collect(Collectors.toList());
    }
}
