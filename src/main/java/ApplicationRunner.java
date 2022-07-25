import com.abdev.spring.database.pool.ConnectionPool;
import com.abdev.spring.database.repository.UserRepository;
import com.abdev.spring.service.UserService;

public class ApplicationRunner {
    public static void main(String[] args) {
        var connectionPool = new ConnectionPool();
        var userRepository = new UserRepository(connectionPool);
        var userService = new UserService(userRepository);
    }
}
