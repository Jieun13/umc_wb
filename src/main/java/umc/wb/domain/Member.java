package umc.wb.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.wb.domain.enums.Gender;
import umc.wb.domain.enums.MemberStatus;
import umc.wb.domain.enums.SocialType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 50)
    private String address;

    private LocalDate inactiveDate;

    @Column(nullable = true, length = 50) //nullable 바꿔둔 상태, 나중에 수정해야 함
    private String email;

    @Builder.Default
    private Integer point = 0;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPreference> preferences = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setMember(this);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        review.setMember(null);
    }

    public void addPreference(MemberPreference preference) {
        this.preferences.add(preference);
        preference.setMember(this);
    }

    public void removePreference(MemberPreference preference) {
        this.preferences.remove(preference);
        preference.setMember(null);
    }

    public void addMemberMission(MemberMission mission) {
        this.memberMissions.add(mission);
        mission.setMember(this);
    }

    public void removeMemberMission(MemberMission mission) {
        this.memberMissions.remove(mission);
        mission.setMember(null);
    }
}