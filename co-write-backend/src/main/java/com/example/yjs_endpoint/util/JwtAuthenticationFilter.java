//package com.example.yjs_endpoint.util;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtTokenProvider tokenProvider;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        String token = resolveToken(request);
//        if (token != null && tokenProvider.validateToken(token)) {
//            String userId = tokenProvider.getUserIdFromToken(token);
//            UsernamePasswordAuthenticationToken auth =
//                    new UsernamePasswordAuthenticationToken(userId, null, List.of());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//            // 将userId放入请求头供后续使用
//            HttpServletRequest wrappedRequest = new RequestWrapper(request);
//            wrappedRequest.setAttribute("X-User-Id", userId);
//            filterChain.doFilter(wrappedRequest, response);
//        } else {
//            filterChain.doFilter(request, response);
//        }
//    }
//
//    private String resolveToken(HttpServletRequest req) {
//        String bearerToken = req.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
