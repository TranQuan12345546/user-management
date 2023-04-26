package vn.techmaster.usermanagement.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.dto.CreateUserRequest;
import vn.techmaster.usermanagement.dto.UpdateUserPasswordRequest;
import vn.techmaster.usermanagement.dto.UpdateUserRequest;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.exception.NotFoundException;
import vn.techmaster.usermanagement.exception.UserHandleException;
import vn.techmaster.usermanagement.mapper.UserMapper;
import vn.techmaster.usermanagement.model.FileResponse;
import vn.techmaster.usermanagement.repository.UserDB;
import vn.techmaster.usermanagement.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers().stream().map(UserMapper::toUserDTO).toList();
    }

    @Override
    public List<UserDTO> getUserByNameContain(String name) {
        return userRepository.getUserByNameContain(name).stream()
                .map(user -> new UserDTO(user.getId(), user.getName(),
                        user.getEmail(), user.getPhone(), user.getAddress(), user.getAvatar())).toList();
    }

    @Override
    public UserDTO getUserById(int id) {
        User user2Find = getUserFromRepoById(id);
        return UserMapper.toUserDTO(user2Find);
    }

    private User getUserFromRepoById(int id) {
        User user2Find = userRepository.getUserById(id).orElseThrow(() -> new NotFoundException("User" + id + "not found"));
        return user2Find;
    }

    @Override
    public UserDTO createUser(CreateUserRequest userRequest) {
        for(User user : userRepository.getAllUsers()) {
            if(user.getEmail().equals(userRequest.email())) {
                throw new UserHandleException("Email da ton tai!");
            }
        }
        User user2Save = new User();
        user2Save.setName(userRequest.name());
        user2Save.setEmail(userRequest.email());
        user2Save.setPhone(userRequest.phone());
        user2Save.setAvatar(userRequest.avatar());
        user2Save.setPassword(BCrypt.hashpw(userRequest.password(), BCrypt.gensalt(12)));
        userRepository.createUser(user2Save);
        return UserMapper.toUserDTO(user2Save);
    }

    @Override
    public UserDTO updateUser(int id, UpdateUserRequest userRequest) {
        for (User user : userRepository.getAllUsers()) {
            if (user.getId() == id) {
                user.setName(userRequest.name());
                user.setPhone(userRequest.phone());
                user.setAddress(userRequest.email());
                return UserMapper.toUserDTO(user);
            }
        }
        throw new NotFoundException("No user found");
    }

    @Override
    public boolean deleteUser(int id) {
        for (User user : userRepository.getAllUsers()) {
            if (user.getId() == id) {
                userRepository.getAllUsers().remove(user);
                return true;
            }
        }
        throw new NotFoundException("No user found");
    }

    @Override
    public UserDTO updateUserPassWord(int id, UpdateUserPasswordRequest req) {
        User user2Find = getUserFromRepoById(id);
        // Encrypted password string is random for each time generating
        /*if (!req.oldPassword().equals(BCrypt.hashpw(user2Find.getPassword(), BCrypt.gensalt(12)))) {
            throw new UserHandleException("Password not match");
        }*/
        // Check if old password is right
        if (!BCrypt.checkpw(req.oldPassword(), user2Find.getPassword())) {
            throw new UserHandleException("Password not match");
        }
        user2Find.setPassword(BCrypt.hashpw(req.newPassword(), BCrypt.gensalt(12)));
        return UserMapper.toUserDTO(user2Find);
    }

    @Override
    public FileResponse updateUserAvatar(int id, MultipartFile file) {
        return null;
    }
}
