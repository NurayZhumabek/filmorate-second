package by.nuray.filmoratesecond;

import by.nuray.filmoratesecond.models.User;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setLogin("test_login");
        user.setName("Test Name");

        System.out.println(user); // Проверяет @ToString
        System.out.println(user.getId()); // Проверяет @Getter
        user.setName("Updated Name"); // Проверяет @Setter
    }
}
