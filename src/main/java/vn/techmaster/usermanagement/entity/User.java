package vn.techmaster.usermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, setterPrefix = "with")
public class User {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String avatar;

    private String password;
}
