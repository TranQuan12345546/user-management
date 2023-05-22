package vn.techmaster.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.usermanagement.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//
     Optional<User> findById(Integer id);
//
    List<User> findUsersByName(String name);
//
//
//    public User updateUser(User user) {
//        User updatedUser = null;
//        for (User u : UserDB.users) {
//            if (u.getId().equals(user.getId())) {
//                updatedUser = u;
//                break;
//            }
//        }
//
//        if (updatedUser != null) {
//            updatedUser.setName(user.getName());
//            updatedUser.setEmail(user.getEmail());
//            updatedUser.setPhone(user.getPhone());
//            return updatedUser;
//        }
//        return null;
//    }
//
//    public boolean deleteUser(Integer id) {
//        User user2Delete = null;
//        for (User user : UserDB.users) {
//            if (user.getId() == id) {
//                user2Delete = user;
//            }
//        }
//        if (user2Delete != null) {
//            UserDB.users.remove(user2Delete);
//            return true;
//        }
//        return false;
//    }
}
