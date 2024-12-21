import Core.CoreSystem;
import post.News;
import users.controller.AdminController;
import users.controller.ManagerController;
import users.models.Admin;
import users.models.Employee;
import users.models.Manager;
import users.models.Teacher;
import users.view.AdminView;
import users.view.ManagerView;

import java.util.Date;
import java.util.Vector;

public class Main {
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
        
//
//            System.out.println("Hello its wsp system");
//
//        }
    }
}