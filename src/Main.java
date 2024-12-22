
import post.News;
import users.controller.AdminController;
import users.controller.ManagerController;
import users.controller.UserController;
import users.models.Admin;
import users.models.Manager;
import users.view.AdminView;
import users.view.ManagerView;
import users.view.UserView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
        AdminController currentAdminController;
        UserView uv = new UserView();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.println("WSP system!");

        }
//        AdminController adminController = new AdminController<>(admin, new AdminView());

    }
}