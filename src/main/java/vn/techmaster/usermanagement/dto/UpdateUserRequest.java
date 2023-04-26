package vn.techmaster.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UpdateUserRequest(@NotEmpty(message = "Name không được để trống")
                                String name,
                                @NotEmpty(message = "Email không được để trống")
                                @Email
                                String email,
                                String phone) {
}
