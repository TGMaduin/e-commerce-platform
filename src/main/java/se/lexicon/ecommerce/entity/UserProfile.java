package se.lexicon.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "profile")
    private Customer customer;

    @Column(nullable = false, length = 100)
    private String nickname;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phoneNumber;

    @Column(length = 500)
    private String bio;

    public UserProfile(String nickname, String phoneNumber, String bio) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }
}