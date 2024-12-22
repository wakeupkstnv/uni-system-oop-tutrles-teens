package users.controller;

import post.Request;
import post.Urgency;
import users.models.Employee;
import users.view.EmployeeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class EmployeeController<Model extends Employee, View extends EmployeeView> extends UserController<Employee, EmployeeView>  {
        
		public EmployeeController(Model currentModel, View currentView) {
			super(currentModel, currentView);
		}
	
	public void sendMessage(BufferedReader reader) throws IOException {
            System.out.println("Введите UUID работника для сообщения: ");
            String employeeUuid = reader.readLine();
            System.out.print("Введите текст: ");
            String complaintText = reader.readLine();



            Employee user = database.getInstance().getEmployees()
                .stream()
                .filter(n -> n.getUuid().equals(employeeUuid))
                .findFirst()
                .orElse(null);

            if (user == null) {
                System.out.println("Работник не найден.");
                return;
            }

            sendMessage(employeeUuid, complaintText);
    }


	public void sendMessage(Employee employee, String message){
            this.currentModel.sendMessage(employee, message);
        }

        public void sendMessage(String employeeUuid, String message){
        	if (employeeUuid == null || message == null || message.isEmpty()) {
                throw new IllegalArgumentException("Employee UUID or message cannot be null or empty");
            }

            Employee user = database.getInstance().getEmployees()
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

            Employee user = database.getInstance().getEmployees()
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
            database.getInstance().addRequest(request);

        }

    public void createRequest(BufferedReader reader) throws IOException  {
        System.out.println("Вы создаете запрос!: ");
        System.out.print("Введите тему: ");
        String Text = reader.readLine();

        if (Text == null || Text.trim().isEmpty()) {
            System.out.println("Ошибка: Тема не может быть пустой!");
            return;
        }

        System.out.print("Введите текст жалобы: ");
        String complaintText = reader.readLine();

        if (complaintText == null || complaintText.trim().isEmpty()) {
            System.out.println("Ошибка: Текст жалобы не может быть пустым!");
            return;
        }

        System.out.print("Выберите уровень срочности (1: LOW, 2: MEDIUM, 3: HIGH): ");
        String urgencyStr = reader.readLine();

        Urgency urgency;
        switch (urgencyStr) {
            case "1":
                urgency = Urgency.LOW;
                break;
            case "2":
                urgency = Urgency.MEDIUM;
                break;
            case "3":
                urgency = Urgency.HIGH;
                break;
            default:
                System.out.println("Ошибка: Неверный выбор уровня срочности.");
                return;
        }


        Request request = new Request(this.currentModel, Text, new Date(), urgency, complaintText);

        if (this.currentModel == null) {
            System.out.println("Ошибка: Текущий пользователь не найден.");
            return;
        }

        this.currentModel.addMyRequest(request);
        database.getInstance().addRequest(request);
        System.out.println("Вы успешно создали запрос!: ");
    }

        
        
        public void redirectRequest(Request request, Employee achiever) {
        	sendRequest(request, achiever);
        }

}
