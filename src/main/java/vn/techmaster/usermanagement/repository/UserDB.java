package vn.techmaster.usermanagement.repository;

import org.mindrot.jbcrypt.BCrypt;
import vn.techmaster.usermanagement.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> users = new ArrayList<>(List.of(
            User.builder().withId(1).withName("Ngô Linh").withPassword(BCrypt.hashpw("11111111", BCrypt.gensalt(12))).withPhone("034343434343434").withEmail("ngolinh@gmail.com").build(),
            User.builder().withId(2).withName("Nguyệt").withPassword(BCrypt.hashpw("22222222", BCrypt.gensalt(12))).withPhone("034343434343434").withEmail("nguyet@gmail.com").build(),
            User.builder().withId(3).withName("Nguyên").withPassword(BCrypt.hashpw("33333333", BCrypt.gensalt(12))).withPhone("034343434343434").withEmail("nguyen@gmail.com").build()
    ));
}
