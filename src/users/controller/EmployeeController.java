package users.controller;

import java.util.Date;

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
        	if (employeeUuid == null || message == null || message.isEmpty()) {
                throw new IllegalArgumentException("Employee UUID or message cannot be null or empty");
            }

            Employee user = Database.getInstance().getEmployees()
                    .stream()
                    .filter(n -> n.getUuid().equals(employeeUuid))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                throw new IllegalArgumentException("Employee with UUID " + employeeUuid + " not found");
            }
            
            System.out.println("Sending message: \"" + message + "\" to employee: " + user.getFirstName());
            this.currentModel.sendMessage(user, message);
        }
        public void viewMessages(){
            this.currentView.showMessage(this.currentModel.viewAllMessages());

        }

        public void sendRequest(Request request, Employee receiver) {
            if (request == null || receiver == null) {
                throw new IllegalArgumentException("Request or receiver cannot be null");
            }

            if (request.getAuthor().getUuid().equals(receiver.getUuid())) {
                throw new IllegalArgumentException("Cannot send a request to yourself");
            }

            Employee user = Database.getInstance().getEmployees()
                    .stream()
                    .filter(emp -> emp.getUuid().equals(receiver.getUuid()))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                throw new IllegalArgumentException("Receiver not found in the database");
            }

            System.out.println("Sending request: " + request + " to employee: " + receiver.getFirstName());
            receiver.addRequest(request);
        }
        
        public void createRequest(String text, String desc, Urgency urg) {
        	Request request = new Request(this.currentModel, text, new Date(), urg, desc);
        	this.currentModel.addMyRequest(request);
            Database.getInstance().addRequest(request);

        }
        
        
        public void redirectRequest(Request request, Employee achiever) {
        	sendRequest(request, achiever);
        }

}
