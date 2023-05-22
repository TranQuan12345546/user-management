package vn.techmaster.usermanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
//    @Column(name = "phone")
//    private String phone;
//    @Column(name = "address")
//    private String address;
//    @Column(name = "avatar")
//    private String avatar;
    @Column(name = "password")
    private String password;
}
