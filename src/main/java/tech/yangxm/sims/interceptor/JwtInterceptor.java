package tech.yangxm.sims.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tech.yangxm.sims.service.UserService;
import tech.yangxm.sims.utils.security.JWTManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@Order(1)
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTManager jwtManager;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("JWT-Token");
        log.info(token);
        if (null == token) {
            log.info("该请求没有携带token");
            response.setStatus(401);
            return false;
        }
        try {
            log.info("jwtmanager:" + jwtManager);
            String username = jwtManager.checkToken(token);
            if (!userService.findByUsername(username)) {
                log.info("无法识别用户信息");
                response.setStatus(401);
                return false;
            } else {
                log.info("验证成功");
                return true;
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            response.setStatus(401);
            return false;
        }
    }
}
