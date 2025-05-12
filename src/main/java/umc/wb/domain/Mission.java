package umc.wb.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    private Integer rewardPoints;

    private Integer spentAmount;

    private LocalDateTime dueDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions;

    public void addMemberMission(MemberMission memberMission) {
        memberMissions.add(memberMission);
        memberMission.setMission(this);
    }

    public void removeMemberMission(MemberMission memberMission) {
        memberMissions.remove(memberMission);
        memberMission.setMission(null);
    }
}