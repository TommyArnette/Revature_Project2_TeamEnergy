import com.energy.dao.UserDao;
import com.energy.dao.UserDaoImpl;
import com.energy.models.Post;
import com.energy.models.User;
import com.energy.service.UserService;
import com.energy.service.UserServiceImpl;

public class main {
    public static void main(String[] args) {

        User user = new User();
       // Posts post = new Posts();

        user.setUserFirstName("testingh2");
        user.setUserLastName("testingh2");
        user.setUserEmail("email");
        user.setUsername("username");
        user.setPassword("password");
        user.setUserProfileDescription("description");
        user.setUserProfileImage("url");


        //service.registerNewUser(user);

        // userService = new UserServiceImpl(new UserDaoImpl());
        //System.out.println(userService.login(user));
       // System.out.println(dao.selectUserByName("username").getUserId());
    }
}
