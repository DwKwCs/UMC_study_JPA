package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.enums.RestaurantsState;

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

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rate=" + rate +
                ", address=" + (address != null ? address : "N/A") + // region의 이름 출력
                '}';
    }
}
