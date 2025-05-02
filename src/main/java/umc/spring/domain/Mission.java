package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurants restaurant;

    @Column(length = 10, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String contents;

    @Column(length = 13)
    private String phone;

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions;
}
