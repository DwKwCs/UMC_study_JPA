package umc.spring.domain.mapping;

import jakarta.persistence.*;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.GenderState;
import umc.spring.domain.enums.UserState;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", length = 12, nullable = false)
    private String loginId;

    @Column(length = 16, nullable = false)
    private String password;

    @Column(length = 13)
    private String phone;

    @Column(length = 4)
    private String name;

    @Column(length = 50)
    private String address;

    @Column(length = 8)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(5)")
    private GenderState genderState;

    @Column(length = 10)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    private UserState state;

    @Column(name = "inactive_date")
    private LocalDate inactiveDate;

    @OneToMany(mappedBy = "user")
    private List<UserMission> userMissions;
}
