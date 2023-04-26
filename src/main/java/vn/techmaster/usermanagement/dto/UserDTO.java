package vn.techmaster.usermanagement.dto;

public record UserDTO(Integer id, String name,
                      String email, String phone,
                      String address, String avatar) {
}
