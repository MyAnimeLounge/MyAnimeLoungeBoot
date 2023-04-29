package com.barta.myanimelounge.interceptors;

import com.barta.myanimelounge.exceptions.EntryNotFoundException;
import com.barta.myanimelounge.models.User;
import com.barta.myanimelounge.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IsVerifiedInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;

    public IsVerifiedInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(EntryNotFoundException::new);
            System.out.println(user);
            if (!user.isEmailVerified()) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter()
                        .write("{\"message\": \"Email verification is required to call this controller\"}");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
        }
        return true;
    }
}
