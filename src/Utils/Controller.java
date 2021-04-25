package Utils;

import Entities.user;
import Services.LoginService;

public class Controller {
    LoginService service = new LoginService();

    private static int userId;

    public static void setUserId(int id)
    {
        if(id == 0) return ;
        Controller.userId = id ;
    }
    public static int getUserId()
    {
        return Controller.userId;
    }

    public user getUser()
    {
        return service.getUserById(userId);
    }

}
