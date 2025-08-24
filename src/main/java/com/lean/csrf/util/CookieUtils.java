package com.lean.csrf.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseCookie;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class CookieUtils {
    /**
     * A reusable helper method to extract a value from a cookie by its name.
     * @param request The incoming HTTP request.
     * @param cookieName The name of the cookie to find.
     * @return The value of the cookie, or null if not found.
     */
    public String extractCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null || cookieName == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    /**
     * A reusable helper method to build a cookie that has expired.
     * @param name The name of the cookie to build.
     * @param httpOnly Whether the cookie should be marked as HTTP-only.
     * @return a built cookie that has expired.
     */
    public ResponseCookie buildExpiredCookie(String name, boolean httpOnly) {
        return buildCookie(name, "", httpOnly, "/", 0);
    }

    /**
     * A reusable helper method to build a cookie with the provided name, value, HTTP-only flag, path, and max age.
     * @param name The name of the cookie to build.
     * @param token The value of the cookie to build.
     * @param httpOnly True if the cookie should be marked as HTTP-only, false otherwise.
     * @param path The path of the cookie to build.
     * @param age The max age of the cookie to build.
     * @return a built cookie with the provided name, value, HTTP-only flag, path, and max age.
     */
     public ResponseCookie buildCookie(String name, String token, boolean httpOnly, String path, long age) {
         if (name == null || token == null || path == null) {
             return null;
         }

        return ResponseCookie.from(name, token)
                .httpOnly(httpOnly)
                .secure(true)
                .path(path)
                .maxAge(age)
                .sameSite("Strict")
                .build();
    }

    public Map<String, String> extractAllCookies(HttpServletRequest request) {
        if (request.getCookies() == null) return Map.of();
        return Arrays.stream(request.getCookies())
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
    }
}
