package umc.spring.domain.mapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurants restaurantId;

    @Column(length = 20, nullable = false)
    @NotNull
    private String title;

    @Column(length = 20, nullable = false)
    @NotNull
    private String contents;

    @Column(length = 13)
    @NotNull
    private String phone;

    @OneToMany(mappedBy = "missionId")
    private List<UserMission> userMissions;
}
