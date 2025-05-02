package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 24, nullable = false)
    private String address;

    @Column(length = 24)
    private String category;

    @Column(nullable = false)
    private double rate;

    @Column(length = 13)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'CLOSED'")
    private RestaurantsState state;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;
}
