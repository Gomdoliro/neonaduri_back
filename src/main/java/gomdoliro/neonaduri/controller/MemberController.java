package gomdoliro.neonaduri.controller;

import gomdoliro.neonaduri.config.JwtTokenProvider;
import gomdoliro.neonaduri.domain.Member;
import gomdoliro.neonaduri.domain.MemberRepository;
import gomdoliro.neonaduri.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    // 회원가입
    @PostMapping("/register")
    public UUID register(@RequestBody Map<String, String> user) {
        return memberRepository.save(Member.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .name(user.get("name"))
                .role(Role.ROLE_USER)
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입 되지 않은 사용자입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
        }

        return jwtTokenProvider.createToken(member.getEmail(), member.getRole());
    }

    // 회원정보 수정
    @PutMapping("/update")
    public UUID update(@RequestBody Map<String, String> user) {
        //Member member = memberRepository.findByEmail(user.get("email")).get();

        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        Member.MemberBuilder memberBuilder = member.toBuilder();

        // 새로운 비밀번호가 입력됐을 때 업데이트
        if (user.containsKey("password") && user.get("password") != null) {
            memberBuilder.password(passwordEncoder.encode(user.get("password")));
        }

        // 새로운 이름이 입력됐을 때 업데이트
        if (user.containsKey("name") && user.get("name") != null) {
            memberBuilder.name(user.get("name"));
        }

        Member updatedMember = memberBuilder.build();
        memberRepository.save(updatedMember);

        return updatedMember.getId();
    }




}
