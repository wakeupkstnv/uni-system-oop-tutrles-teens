import core.CoreSystem;
import post.News;
import users.controller.ManagerController;
import users.models.Employee;
import users.models.Manager;
import users.models.Teacher;
import users.view.ManagerView;

import java.util.Date;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        Manager mg = new Manager("1", "firstName", "secondName", "t_kustanayev", "rfrf", new Date(), "fdd", new Vector<>());
        ManagerController managerController = new ManagerController<>(mg, new ManagerView());
        managerController.addNews(new News(mg, "hi!", new Date()));
        managerController.viewNews();

//        while (true){
//
//            System.out.println("Hello its wsp system");
//
//        }
    }
}