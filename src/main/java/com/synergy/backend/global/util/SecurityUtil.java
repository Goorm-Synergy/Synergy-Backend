package com.synergy.backend.global.util;

import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.global.exception.AuthorizedException;
import com.synergy.backend.global.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    public static Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Current user: {}", authentication.getPrincipal());
        log.info("Current username: {}", authentication.getName());

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthorizedException();
        }

        Object principal = authentication.getPrincipal();

        return (Member) principal;
    }
}