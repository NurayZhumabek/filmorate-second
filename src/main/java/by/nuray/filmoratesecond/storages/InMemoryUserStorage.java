package by.nuray.filmoratesecond.storages;


import by.nuray.filmoratesecond.models.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryUserStorage implements UserStorage {

    Map<Integer, User> users = new HashMap<>();
    Map<Integer, Set<Integer>> friends = new HashMap<>();


    int counter = 1;

    @Override
    public Optional<User> getById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void save(User user) {
        user.setId(counter++);
        users.put(user.getId(), user);
    }

    @Override
    public void update(int id, User user) {
        getById(id).ifPresent(u -> users.put(id, user));
    }

    @Override
    public void delete(int id) {
        users.remove(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst();

    }

    @Override
    public void addFriend(int id1, int id2) {
            friends.putIfAbsent(id1, new HashSet<>());
            friends.putIfAbsent(id2, new HashSet<>());
            friends.get(id1).add(id2);
            friends.get(id2).add(id1);

    }

    @Override
    public void removeFriend(int id1, int id2) {

        friends.getOrDefault(id1, Collections.emptySet()).remove(id2);
        friends.getOrDefault(id1, Collections.emptySet()).remove(id2);
    }

    @Override
    public List<User> getFriends(int id){
        Set<Integer> friendList=friends.getOrDefault(id, Collections.emptySet());


        return friendList.stream()
                .map(friend -> users.get(friend))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> mutualFriends(int id1, int id2) {

        Set<Integer> friendsId1 = friends.getOrDefault(id1, Collections.emptySet());
        Set<Integer> friendsId2 = friends.getOrDefault(id2, Collections.emptySet());

        Set<Integer> result = new HashSet<>(friendsId1);
        result.retainAll(friendsId2);


        return result.stream()
                .map(users::get)
                .collect(Collectors.toList());
    }

}
