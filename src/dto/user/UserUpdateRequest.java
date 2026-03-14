package dto.user;

import java.time.LocalDateTime;

public class UserUpdateRequest {
    int id;
    String name;
    String email;
    String phoneNo;
    LocalDateTime dateOfBirth;

    public UserUpdateRequest(int id, String name, String email, String phoneNo, LocalDateTime dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }
}
