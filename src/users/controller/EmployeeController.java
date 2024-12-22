package users.controller;

import database.Database;
import post.Message;
import post.Request;
import post.Urgency;
import users.models.Employee;
import users.models.User;
import users.view.EmployeeView;
import users.view.UserView;

public class EmployeeController<Model extends Employee, View extends EmployeeView> extends UserController<Employee, EmployeeView>  {
        public void sendMessage(Employee employee, String message){
            this.currentModel.sendMessage(employee, message);
        }

        public void sendMessage(String employeeUuid, String message){
            Employee user = Database.getInstance().getEmployees()
                    .stream()
                    .filter(n->n.getUuid().equals(employeeUuid))
                    .findFirst()
                    .orElse(null);
        }
        public void viewMessages(){
            this.currentView.showMessage(this.currentModel.viewAllMessages());
        }

        public void sendRequest(Request request, Urgency urgency){

        }
}
