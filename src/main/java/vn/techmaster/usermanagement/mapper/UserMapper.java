package vn.techmaster.usermanagement.mapper;

import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.entity.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getAvatar()
        );
    }
}
