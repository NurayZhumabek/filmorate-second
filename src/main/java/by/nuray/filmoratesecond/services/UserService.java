package by.nuray.filmoratesecond.services;


import by.nuray.filmoratesecond.models.User;
import by.nuray.filmoratesecond.storages.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService {

    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public List<User> getAllUsers() {
        return userStorage.getAll();
    }

    public User getUserById(int id) {
        return userStorage.getById(id).orElseThrow(()->new IllegalArgumentException("User not found"));
    }

    public void save(User user) {
        userStorage.save(user);
    }

    public void update(User user,int id) {
        userStorage.update(id, user);
    }

    public void delete(int id) {
        userStorage.delete(id);
    }

    public void addFriend(int id1, int id2) {
        getUserById(id1);
        getUserById(id2);
        userStorage.addFriend(id1, id2);
    }

    public void removeFriend(int id1, int id2) {
        getUserById(id1);
        getUserById(id2);
        userStorage.removeFriend(id1, id2);
    }


    public List<User> getFriends(int id) {
        getUserById(id);
        return userStorage.getFriends(id);
    }
    public List<User> getMutualFriends(int id1, int id2) {
        getUserById(id1);
        getUserById(id2);

        return userStorage.mutualFriends(id1, id2);
    }


}
