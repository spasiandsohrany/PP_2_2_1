package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> getlistUsers();

    User getUserByModelAndSeries(String model, int series);
}
