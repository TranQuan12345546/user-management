package vn.techmaster.usermanagement.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateUserPasswordRequest(@NotEmpty(message = "Password không được để trống")
                                        String oldPassword,
                                        @NotEmpty(message = "Password không được để trống")
                                        String newPassword) {
}
