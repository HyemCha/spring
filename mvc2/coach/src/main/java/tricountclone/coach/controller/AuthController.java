package tricountclone.coach.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tricountclone.coach.TricountConst;
import tricountclone.coach.model.LoginRequest;
import tricountclone.coach.model.Member;
import tricountclone.coach.model.SignupRequest;
import tricountclone.coach.service.MemberService;

import java.net.http.HttpResponse;

import static tricountclone.coach.TricountConst.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> singUp(@RequestBody SignupRequest signupRequest) {
        Member member = new Member();
        member.setLoginId(signupRequest.getLoginId());
        member.setPassword(signupRequest.getPassword());
        member.setName(signupRequest.getName());

        return new ResponseEntity<>(memberService.signUp(member), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Member member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());
        Cookie cookie = new Cookie(LOGIN_MEMBER_COOKIE, String.valueOf(member.getId()));
        response.addCookie(cookie);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie idCookie = new Cookie(LOGIN_MEMBER_COOKIE, null);
        idCookie.setMaxAge(0);
        response.addCookie(idCookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
