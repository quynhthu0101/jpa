package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.User;

public interface IUserService {
    User get(int id);

    void insert(User user);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    User findByUserName(String username);

    List<User> findAll();

    boolean changePassword(String username, String newPassword);

    void delete(int id) throws Exception;

    void update(User user);
    
    User login(String username, String password);
}
