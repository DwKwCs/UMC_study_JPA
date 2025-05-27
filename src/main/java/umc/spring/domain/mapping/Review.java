package umc.spring.domain.mapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurants restaurantId;

    @Column(length = 20)
    @NotNull
    private String contents;

    @Column(nullable = false)
    @NotNull
    private double rate;

    @Lob
    @Column(name = "image_1")
    private byte[] image1;

    @Lob
    @Column(name = "image_2")
    private byte[] image2;

    @Lob
    @Column(name = "image_3")
    private byte[] image3;
}
