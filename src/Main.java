import post.News;
import users.controller.AdminController;
import users.controller.ManagerController;
import users.models.Admin;
import users.models.Manager;
import users.view.AdminView;
import users.view.ManagerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
    	Admin admin = new Admin("12","alibek", "zhumabay", "bezdar_v", "admin", new Date(), "Parol12");
    	Manager mg = new Manager("12","alibek", "zhumabay", "bezdar_v", "admin", new Date(), "Parol12");
        ManagerController managerController = new ManagerController<>(mg, new ManagerView());
        managerController.addNews(new News(mg, "hi!", new Date()));
        managerController.viewNews();
//        AdminController adminController = new AdminController<>(admin, new AdminView());

    }
}