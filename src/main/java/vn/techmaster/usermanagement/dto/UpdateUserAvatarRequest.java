package vn.techmaster.usermanagement.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateUserAvatarRequest(@NotEmpty(message = "Avatar không được để trống")
                                      String avatar) {
}
