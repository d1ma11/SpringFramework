package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    /*
     * В дополнение к операциям CRUD, которые поддерживает CrudRepository,
     * интерфейс определяет метод findByUsername(), который будет использоваться
     * для поиска учетной записи по имени пользователя
     * P.S.
     * Spring Data JPA автоматически генерирует реализацию этого интерфейса
     * во время выполнения
     */
    User findByUsername(String username);
}
