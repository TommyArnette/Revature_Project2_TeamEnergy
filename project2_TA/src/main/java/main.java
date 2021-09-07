import com.energy.dao.UserDao;
import com.energy.dao.UserDaoImpl;
import com.energy.models.Posts;
import com.energy.models.User;
import com.energy.service.UserService;
import com.energy.service.UserServiceImpl;

public class main {
    public static void main(String[] args) {
        /*
        User user = new User();
       // Posts post = new Posts();

        user.setUserFirstName("testingh2");
        user.setUserLastName("testingh2");
        user.setUserEmail("email");
        user.setUsername("username");
        user.setPassword("password");
        user.setUserProfileDescription("description");
        user.setUserProfileImage("url");


        UserService service = new UserServiceImpl();
        //service.registerNewUser(user);
        */
        UserDao dao = new UserDaoImpl();
        System.out.println(dao.selectUserByName("username").getUserFirstName());
    }
}
