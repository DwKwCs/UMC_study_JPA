package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurants restaurant;

    @Column(length = 13)
    private String contents;

    @Column(nullable = false)
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
