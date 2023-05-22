package vn.techmaster.usermanagement.service;

import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.dto.CreateUserRequest;
import vn.techmaster.usermanagement.dto.UpdateUserPasswordRequest;
import vn.techmaster.usermanagement.dto.UpdateUserRequest;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.model.FileResponse;

import java.util.List;

public interface UserService {
    List<UserDTO>  getAllUsers();
    List<UserDTO>  getUserByNameContain(String name);
    UserDTO getUserById(int id);
    UserDTO createUser(CreateUserRequest userRequest);
    UserDTO updateUser(int id, UpdateUserRequest userRequest);

    boolean deleteUser(int id);
//    UserDTO updateUserPassWord(int id, UpdateUserPasswordRequest updateUserPasswordRequest);
//    FileResponse updateUserAvatar(int id, MultipartFile file);

}
