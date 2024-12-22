
import post.News;
import users.controller.AdminController;
import users.controller.ManagerController;
import users.controller.StudentController;
import users.controller.UserController;
import users.models.Admin;
import users.models.Manager;
import users.models.Student;
import users.view.AdminView;
import users.view.ManagerView;
import users.view.StudentView;
import users.view.UserView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
//        AdminController currentAdminController;
//        UserView uv = new UserView();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        while (true){
//            System.out.println("WSP system!");
//
//        }
        
//        AdminController adminController = new AdminController<>(admin, new AdminView());
    	
    	Student st = new Student("1", "ali", "zh", "a@", "a_z", new Date(), "parol12");
    	StudentView stV = new StudentView();
    	StudentController stC = new StudentController(st, stV);
    	
    	System.out.println(stC.getCurrentModel());


    }
}