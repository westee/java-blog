package hello.entiry;

import java.time.Instant;

public class User {
    Integer id;
    String username;
    String avatar;
    String encryptedPassword;
    Instant createdAt;
    Instant updatedAt;

    public User(Integer id, String username, String encryptedPassword) {
        this.id = id;
        this.username = username;
        this.avatar = "avatar";
        this.encryptedPassword = encryptedPassword;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
