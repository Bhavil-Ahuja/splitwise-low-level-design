package manager;

import dto.user.User;
import dto.user.UserUpdateRequest;
import repository.UserRepository;

public class UserManager {
    UserRepository userRepository;

    public UserManager() {
        userRepository = UserRepository.getInstance();
    }

    public void createProfile(User user) {
        System.out.println("createProfile userId=" + user.getUserId());
        userRepository.addUser(user);
    }

    public void deleteProfile(User user) {
        System.out.println("deleteProfile userId=" + user.getUserId());
        userRepository.removeUser(user);
    }

    public void editProfile(UserUpdateRequest userUpdateRequest) {
        System.out.println("editProfile userId=" + userUpdateRequest.getId());
        User user = userRepository.findById(userUpdateRequest.getId());
        if (userUpdateRequest.getName() != null) {
            user.setName(userUpdateRequest.getName());
        }
        if (userUpdateRequest.getEmail() != null) {
            user.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getPhoneNo() != null) {
            user.setPhoneNo(userUpdateRequest.getPhoneNo());
        }
        if (userUpdateRequest.getDateOfBirth() != null) {
            user.setDateOfBirth(userUpdateRequest.getDateOfBirth());
        }
        userRepository.save(user);
    }
}
