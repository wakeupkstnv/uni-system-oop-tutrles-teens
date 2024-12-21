import core.CoreSystem;
import post.News;
import users.controller.AdminController;
import users.controller.ManagerController;
import users.models.Admin;
import users.models.Employee;
import users.models.Manager;
import users.models.Teacher;
import users.view.AdminView;
import users.view.ManagerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Manager mg = new Manager("1", "firstName", "secondName", "t_kustanayev", "rfrf", new Date(), "fdd");
        ManagerController managerController = new ManagerController<>(mg, new ManagerView());
        managerController.addNews(new News(mg, "hi!", new Date()));
        managerController.viewNews();

        Admin admin = new Admin("1", "firstName", "secondName", "t_kustanayev", "rfrf", new Date(), "fdd");
        AdminController adminController = new AdminController<>(admin, new AdminView());
        AdminView.showRegisterUser(adminController, reader);
//        while (true){
//
//            System.out.println("Hello its wsp system");
//
//        }
    }
}