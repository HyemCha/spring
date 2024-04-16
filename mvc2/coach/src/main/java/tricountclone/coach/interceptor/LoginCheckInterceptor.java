package tricountclone.coach.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tricountclone.coach.MemberContext;
import tricountclone.coach.TricountConst;
import tricountclone.coach.service.MemberService;

@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();

        if (!this.containUserCookie(cookies)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        for (Cookie cookie : cookies) {
            if (TricountConst.LOGIN_MEMBER_COOKIE.equals(cookie.getName())) {
                try {
                    MemberContext.setCurrentMember(memberService.findMemberById(Long.valueOf(cookie.getValue())));
                    break;
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean containUserCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TricountConst.LOGIN_MEMBER_COOKIE.equals(cookie.getName())) {
                    return true;
                }
            }
        }

        return false;
    }
}
