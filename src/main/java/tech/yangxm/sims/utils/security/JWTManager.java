package tech.yangxm.sims.utils.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.yangxm.sims.config.JWTProperties;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JWTManager {

    @Autowired
    private JWTProperties properties;

    /**
     * key是username
     */
    private Map<String, JwtWrapper> tokenMap = new ConcurrentHashMap<>();

    public synchronized String createToken(String username) throws Exception {
        try {
            String token = JWTUtil.createToken(username, properties.getIssueName(), properties.getCipher());
            tokenMap.put(username, new JwtWrapper(token));
            return token;
        } catch (Exception e) {
            throw e;
        }
    }

    public synchronized String checkToken(String token) throws Exception {
        Map<String, String> map = JWTUtil.checkToken(token,
                properties.getIssueName(),
                properties.getCipher());
        if (null == map || !map.containsKey("USERNAME")) {
            throw new Exception("Token不合法，无法找到用户名...");
        } else {
            String username = map.get("USERNAME");
            if (!tokenMap.containsKey(username)) {
                //这种情况可能是token不是在服务器生成的
                throw new Exception("Token验证失败...");
            } else {
                JwtWrapper jwtWrapper = tokenMap.get(username);
                if (jwtWrapper.getExpiration().before(new Date())) {
                    throw new Exception("token已过期...");
                }
                if (!jwtWrapper.getToken().equals(token)) {
                    throw new Exception("已在其他地方登陆");
                }
                //给token有效期延长十分钟
                jwtWrapper.getExpiration();
                return username;
            }
        }
    }

    //jwt包装
    @Getter
    private class JwtWrapper {

        private String token;
        private Date expiration;

        public JwtWrapper(String token) {
            this.token = token;
            setExpiration();
        }

        public void setExpiration() {
            this.expiration = new Date(System.currentTimeMillis() + properties.getExpiration() * 1000);
        }
    }
}
