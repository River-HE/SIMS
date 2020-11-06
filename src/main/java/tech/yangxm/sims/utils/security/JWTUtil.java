package tech.yangxm.sims.utils.security;

import io.jsonwebtoken.*;
import lombok.SneakyThrows;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    /**
     * 生成token
     *
     * @param username  申请token的人
     * @param issueName 颁发token的人
     * @param cipher    token的密钥
     * @return 返回一个token
     */

    public static String createToken(String username, String issueName, String cipher) throws Exception {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(cipher);
            Key signKey = new SecretKeySpec(apiKeySecretBytes, algorithm.getJcaName());

            //构建JWT token
            JwtBuilder builder = Jwts.builder()
                    //header头部
                    .setHeaderParam("type", "JWT")
                    .setHeaderParam("algorithm", "HS256")
                    //payload载荷
                    .claim("company", "SIMS")
                    .setIssuer(issueName)
                    .setAudience(username)
                    //signature签证
                    .setIssuedAt(new Date())
                    .signWith(algorithm, signKey);
            //将header、payload、signature打包成JWT
            return builder.compact();
        } catch (Exception e) {
            throw new Exception("创建JWT失败", e);
        }
    }

    public static Map<String, String> checkToken(String token, String issueName, String cipher) throws Exception {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(cipher))
                    .parseClaimsJws(token)
                    .getBody();
            String company = claims.get("company").toString();
            if (!"SIMS".equals(company)) {
                throw new Exception("公司:" + company + " 无法识别...");
            }
            String issueName1 = claims.getIssuer();
            if (null == issueName || !issueName.equals(issueName1)) {
                throw new Exception("签发者:" + issueName1 + " 无法识别...");
            }

            String username = claims.getAudience();
            if (null == username) {
                throw new Exception("username为空...");
            } else {
                Map<String, String> map = new HashMap<String, String>() {
                    {
                        put("USERNAME", username);
                    }
                };
                return map;
            }
        } catch (Exception e) {
            throw new Exception("校验token失败...", e);
        }
    }
}
