package tech.yangxm.sims.model;

import lombok.Data;
import tech.yangxm.sims.pojo.User;

import java.io.Serializable;

@Data
public class UserModel implements Serializable {

    private String username;

    private String password;

    public User toUser() {
        User user = new User();
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        return user;
    }
}
