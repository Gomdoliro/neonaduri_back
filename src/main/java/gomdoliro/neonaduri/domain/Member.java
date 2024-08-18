package gomdoliro.neonaduri.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Member {
    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID id;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;
}
