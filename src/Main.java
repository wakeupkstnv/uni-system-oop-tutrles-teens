<<<<<<< HEAD
import Core.CoreSystem;
=======

>>>>>>> b1490337331f2c528d456f35a4a61e00e436bceb
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
<<<<<<< HEAD
    public static void main(String[] args) {
    	Admin admin = new Admin("12","alibek", "zhumabay", "bezdar_v", "admin", new Date(), "Parol12");
    	Manager mg = new Manager("12","alibek", "zhumabay", "bezdar_v", "admin", new Date(), "Parol12");
        ManagerController managerController = new ManagerController<>(mg, new ManagerView());
        managerController.addNews(new News(mg, "hi!", new Date()));
        managerController.viewNews();
//        AdminController adminController = new AdminController<>(admin, new AdminView());
//        
//        adminController.registerUser(2, "12","alibek", "zhumabay", "bezdar_v", "admin", new Date(), "Parol12");
////        while (true){
//        AdminView adminView = new AdminView();
//        adminView.showRegisterUser(adminController, null);
        
=======
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
>>>>>>> b1490337331f2c528d456f35a4a61e00e436bceb
//
//            System.out.println("Hello its wsp system");
//
//        }
    }
}