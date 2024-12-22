package users.view;

import core.CoreSystem;
import core.Language;
import database.Database;
import papers.ResearchPaper;
import post.Message;
import post.News;
import users.controller.UserController;
import users.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class UserView<Model extends User> {
    protected final static Database database = Database.getInstance();


    public boolean authMenu(UserController userController, BufferedReader reader) throws IOException {
        UserView uv = new UserView();
        System.out.println("Введите email:");
        String email = reader.readLine();
        System.out.println("Введите пароль:");
        String password = reader.readLine();

        Boolean result = userController.login(email, password);
        return result;
    }
    


    public void showLoginResult(boolean success) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println(success ? "Вход успешен!" : "Не удалось войти. Проверьте ваши данные.");
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println(success ? "Login successful!" : "Login failed. Please check your credentials.");
        } else {
            System.out.println(success ? "Кіру сәтті!" : "Кіру сәтсіз. Деректеріңізді тексеріңіз.");
        }
    }

    public void showProfile(Model user) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Профиль пользователя:");
            System.out.println("Имя: " + user.getFirstName() + " " + user.getLastName());
            System.out.println("Электронная почта: " + user.getEmail());
            System.out.println("Логин: " + user.getLogin());
            System.out.println("Дата рождения: " + user.getBirthDate());
            System.out.println("Уведомлений: " + user.getAllNotifications().size());
            System.out.println("Заблокирован: " + (user.isBanned() ? "Да" : "Нет"));
        } else if (	CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("User Profile:");
            System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Login: " + user.getLogin());
            System.out.println("Date of Birth: " + user.getBirthDate());
            System.out.println("Notifications: " + user.getAllNotifications().size());
            System.out.println("Banned: " + (user.isBanned() ? "Yes" : "No"));
        } else {
            System.out.println("Пайдаланушының профилі:");
            System.out.println("Аты: " + user.getFirstName() + " " + user.getLastName());
            System.out.println("Электрондық пошта: " + user.getEmail());
            System.out.println("Логин: " + user.getLogin());
            System.out.println("Туған күні: " + user.getBirthDate());
            System.out.println("Хабарламалар саны: " + user.getAllNotifications().size());
            System.out.println("Бұғатталған: " + (user.isBanned() ? "Иә" : "Жоқ"));
        }
    }

    public void showLogoutMessage() {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Вы успешно вышли.");
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("You have successfully logged out.");
        } else {
            System.out.println("Сіз сәтті шықтыңыз.");
        }
    }

	public void showMessage(Vector<Message> messages) {
		if (messages.isEmpty()) {
			if (CoreSystem.getLanguageMode() == Language.RUS) {
				System.out.println("Нет сообщений.");
			} else if (CoreSystem.getLanguageMode() == Language.ENG) {
				System.out.println("No messages.");
			} else  {
				System.out.println("Хабарламалар жоқ.");
			}
		} else {
			if (CoreSystem.getLanguageMode() == Language.RUS) {
				System.out.println("Сообщения:");
			} else if (	CoreSystem.getLanguageMode() == Language.ENG) {
				System.out.println("Messages:");
			} else  {
				System.out.println("Хабарламалар:");
			}
	
			for (Message message : messages) {
				System.out.println(message);
			}
		}
	}
	

    public void showSomeText(String s, Object o) {
        System.out.println(o.getClass() + ": " + s);
    }

    public void showSubscriptionResult(boolean subscribed) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println(subscribed ? "Вы успешно подписались!" : "Не удалось подписаться.");
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println(subscribed ? "You have successfully subscribed!" : "Subscription failed.");
        } else {
            System.out.println(subscribed ? "Сіз сәтті жазылдыңыз!" : "Жазылу сәтсіз аяқталды.");
        }
    }

    public void showNotifications(Vector<News> notifications) {
		if (notifications.isEmpty()) {
			if (CoreSystem.getLanguageMode() == Language.RUS) {
				System.out.println("Нет новых уведомлений.");
			} else if (CoreSystem.getLanguageMode() == Language.ENG) {
				System.out.println("No new notifications.");
			} else {
				System.out.println("Жаңа хабарламалар жоқ.");
			}
		} else {
			if (	CoreSystem.getLanguageMode() == Language.RUS) {
				System.out.println("Уведомления:");
			} else if (	CoreSystem.getLanguageMode() == Language.ENG) {
				System.out.println("Notifications:");
			} else  {
				System.out.println("Хабарламалар:");
			}
	
			for (News news : notifications) {
				System.out.println(news);
			}
		}
	}
	

    public void showPapers(Vector<ResearchPaper> rp) {
		if (rp.isEmpty()) {
			if (	CoreSystem.getLanguageMode() == Language.RUS) {
				System.out.println("Нет научных статей.");
			} else if (	CoreSystem.getLanguageMode() == Language.ENG) {
				System.out.println("No research papers available.");
			} else  {
				System.out.println("Ғылыми мақалалар жоқ.");
			}
		} else {
			if (	CoreSystem.getLanguageMode() == Language.RUS) {
				System.out.println("Научные статьи:");
			} else if (	CoreSystem.getLanguageMode() == Language.ENG) {
				System.out.println("Research Papers:");
			} else {
				System.out.println("Ғылыми мақалалар:");
			}
			for (ResearchPaper paper : rp) {
				System.out.println(paper);
			}
		}
	}
	
	

    public void showError(String msg) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("ОШИБКА: " + msg);
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("ERROR MESSAGE: " + msg);
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println("ҚАТЕ МӘЛІМДЕМЕ: " + msg);
        }
    }
	
}
