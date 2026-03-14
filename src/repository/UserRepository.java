package repository;

import dto.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    volatile static UserRepository instance;
    Map<Integer, User> users = new HashMap<>();

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public User findById(int id) {
        System.out.println("findById userId=" + id);
        return users.get(id);
    }

    public void save(User user) {
        System.out.println("save userId=" + user.getUserId());
        users.put(user.getUserId(), user);
    }

    public void addUser(User user) {
        System.out.println("addUser userId=" + user.getUserId());
        users.put(user.getUserId(), user);
    }

    public void removeUser(User user) {
        System.out.println("removeUser userId=" + user.getUserId());
        users.remove(user.getUserId());
    }
}
