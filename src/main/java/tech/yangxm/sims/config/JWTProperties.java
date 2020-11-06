package tech.yangxm.sims.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@ToString
@Component
public class JWTProperties {
    @Value("${sims.jwt.cipher}")
    private String cipher;

    @Value("${sims.jwt.issuename}")
    private String issueName;

    @Value("${sims.jwt.expiration}")
    private String expiration;

    public long getExpiration() {
        return Long.parseLong(expiration);
    }
}
