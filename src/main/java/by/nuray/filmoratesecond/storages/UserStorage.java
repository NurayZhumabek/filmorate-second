package by.nuray.filmoratesecond.storages;

import by.nuray.filmoratesecond.models.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {

    public Optional<User> getById(int  id);
    public List<User> getAll();
    public void save(User user);
    public void update(int id, User user);
    public void delete(int  id);
    public Optional<User> findByEmail(String email);


    void addFriend(int id1, int id2);

    void removeFriend(int id1, int id2);

    List<User> getFriends(int id);

    List<User> mutualFriends(int id1, int id2);
}
