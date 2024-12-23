package users.view;

import core.*;
import post.Message;
import post.News;
import post.Urgency;
import users.controller.EmployeeController;
import users.models.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;


public class EmployeeView<Model extends Employee> extends UserView<Employee> {

    public EmployeeView() {
        super();
    }

    @Override
    public void showLoginResult(boolean success) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println(success ? "Вход успешен!" : "Не удалось войти. Проверьте ваши данные.");
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println(success ? "Login successful!" : "Login failed. Please check your credentials.");
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println(success ? "Кіру сәтті!" : "Кіру сәтсіз. Деректеріңізді тексеріңіз.");
        }
    }

    @Override
    public void showLogoutMessage() {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Вы успешно вышли.");
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("You have successfully logged out.");
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println("Сіз сәтті шықтыңыз.");
        }
    }


    public void showChat(Vector<Message> messages) {
        if (messages.isEmpty()) {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Нет сообщений.");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("No messages.");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Хабарламалар жоқ.");
            }
        } else {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Сообщения:");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("Messages:");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Хабарламалар:");
            }

            for (Message message : messages) {
                System.out.println(message);
            }
        }
    }

    @Override
    public void showSubscriptionResult(boolean subscribed) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println(subscribed ? "Вы успешно подписались!" : "Не удалось подписаться.");
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println(subscribed ? "You have successfully subscribed!" : "Subscription failed.");
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println(subscribed ? "Сіз сәтті жазылдыңыз!" : "Жазылу сәтсіз аяқталды.");
        }
    }

    @Override
    public void showNotifications(Vector<News> notifications) {
        if (notifications.isEmpty()) {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Нет новых уведомлений.");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("No new notifications.");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Жаңа хабарламалар жоқ.");
            }
        } else {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Уведомления:");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("Notifications:");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Хабарламалар:");
            }

            for (News news : notifications) {
                System.out.println(news);
            }
        }
    }

    @Override
    public void showMessage(Vector<Message> messages) {
        if (messages.isEmpty()) {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Нет сообщений.");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("No messages.");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Хабарламалар жоқ.");
            }
        } else {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Сообщения:");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("Messages:");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Хабарламалар:");
            }

            for (Message message : messages) {
                System.out.println(message);
            }
        }
    }


    public void showRequest() {
 
    }
    
    public static void showCreateRequest(EmployeeController employeeController, BufferedReader reader) throws IOException {
    	if(CoreSystem.getLanguageMode() == Language.ENG) {
    		System.out.print("Enter title: ");
			String text = reader.readLine();
			
			System.out.print("Enter description: ");
			String desc = reader.readLine();
			
			System.out.print("Select urgency: ");
            System.out.println("1: Low\n2: Medium\n3:High");
			String choice = reader.readLine();
			Urgency u = Urgency.LOW;
			if (choice == "1"){
                u = Urgency.LOW;
            } else if (choice == "2") {
                u = Urgency.MEDIUM;
            } else if(choice == "3"){
                u = Urgency.HIGH;
            }

            employeeController.createRequest(text, desc, u);
			
			
    	}
    }

}
