package hello.entiry;

import java.time.Instant;

public class User {
    int id;
    String username;
    String avatar;
    String encryptedPassword;
    Instant createdAt;
    Instant updatedAt;

    public User(int id, String username, String encryptedPassword) {
        this.id = id;
        this.username = username;
        this.avatar = "avatar";
        this.encryptedPassword = encryptedPassword;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public User(int id, String username, String avatar, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
