package dto.user;

import dto.helper.ObjectModificationHelper;

import java.time.LocalDateTime;

public class User {
    private final int userId;
    private String name;
    private String email;
    private String phoneNo;
    private LocalDateTime dateOfBirth;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public User(int userId, String name, String email, String phoneNo, LocalDateTime dateOfBirth) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = this.modifiedAt = LocalDateTime.now();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
