package vn.techmaster.usermanagement.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.usermanagement.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    public List<User> getAllUsers() {
        return UserDB.users;
    }

    public Optional<User> getUserById(Integer id) {
        return UserDB.users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public List<User> getUserByNameContain(String name) {
        return UserDB.users.stream().filter(
                    user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public User createUser(User user) {
        int maxId = UserDB.users.stream().mapToInt(User::getId).max().orElse(0);
        User creatingUser = user.toBuilder().withId(maxId).build();
        UserDB.users.add(creatingUser);
        return creatingUser;
    }

    public User updateUser(User user) {
        User updatedUser = null;
        for (User u : UserDB.users) {
            if (u.getId().equals(user.getId())) {
                updatedUser = u;
                break;
            }
        }

        if (updatedUser != null) {
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhone(user.getPhone());
            return updatedUser;
        }
        return null;
    }

    public boolean deleteUser(Integer id) {
        User user2Delete = null;
        for (User user : UserDB.users) {
            if (user.getId() == id) {
                user2Delete = user;
            }
        }
        if (user2Delete != null) {
            UserDB.users.remove(user2Delete);
            return true;
        }
        return false;
    }
}
