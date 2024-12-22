package users.view;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import core.CoreSystem;
import core.Language;
import papers.Format;
import papers.ResearchPaper;
import post.Message;
import post.News;
import users.controller.ResearcherController;
import users.controller.UserController;
import users.models.Researcher;
import users.models.User;


@SuppressWarnings("rawtypes")
public class ResearcherView<Model extends Researcher>
{
	
	public ResearcherView(){
		super();
	}
	
    public boolean authMenu(ResearcherController researcherController, BufferedReader reader) throws IOException {
    	ResearcherView rv = new ResearcherView();
        System.out.println("Введите email:");
        String email = reader.readLine();
        System.out.println("Введите пароль:");
        String password = reader.readLine();

        Boolean result = researcherController.login(email, password);
        return result;
    }

	public void printPapers(Researcher r, Format format) {
		if (CoreSystem.getLanguageMode() == Language.ENG) {
			System.out.println("Researcher"+r.getUserInstance().getFirstName()+"'s articles citations:" );
		for(ResearchPaper paper: r.getArticles()){
			System.out.println(paper.getCitations(format));
		}
		}
		else if(CoreSystem.getLanguageMode() == Language.RUS){
			System.out.println("Цитирования статей исследователя "+r.getUserInstance().getFirstName()+":" );
			for(ResearchPaper paper: r.getArticles()){
				System.out.println(paper.getCitations(format));
			}
		}
		else{
			System.out.println("Зерттеуші "+r.getUserInstance().getFirstName()+"-ның мақалаларына сілтеме:" );
			for(ResearchPaper paper: r.getArticles()){
				System.out.println(paper.getCitations(format));
			}
		}
		
		
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

    public void showProfile(Model subscriber) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Профиль пользователя:");
            System.out.println("Имя: " + subscriber.getUserInstance().getFirstName() + " " + subscriber.getUserInstance().getLastName());
            System.out.println("Электронная почта: " + subscriber.getUserInstance().getEmail());
            System.out.println("Логин: " + subscriber.getUserInstance().getLogin());
            System.out.println("Дата рождения: " + subscriber.getUserInstance().getBirthDate());
            System.out.println("Уведомлений: " + subscriber.getUserInstance().getAllNotifications().size());
            System.out.println("Заблокирован: " + (subscriber.getUserInstance().isBanned() ? "Да" : "Нет"));
        } else if (	CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("User Profile:");
            System.out.println("Name: " + subscriber.getUserInstance().getFirstName() + " " + subscriber.getUserInstance().getLastName());
            System.out.println("Email: " + subscriber.getUserInstance().getEmail());
            System.out.println("Login: " + subscriber.getUserInstance().getLogin());
            System.out.println("Date of Birth: " + subscriber.getUserInstance().getBirthDate());
            System.out.println("Notifications: " + subscriber.getUserInstance().getAllNotifications().size());
            System.out.println("Banned: " + (subscriber.getUserInstance().isBanned() ? "Yes" : "No"));
        } else {
            System.out.println("Пайдаланушының профилі:");
            System.out.println("Аты: " + subscriber.getUserInstance().getFirstName() + " " + subscriber.getUserInstance().getLastName());
            System.out.println("Электрондық пошта: " + subscriber.getUserInstance().getEmail());
            System.out.println("Логин: " + subscriber.getUserInstance().getLogin());
            System.out.println("Туған күні: " + subscriber.getUserInstance().getBirthDate());
            System.out.println("Хабарламалар саны: " + subscriber.getUserInstance().getAllNotifications().size());
            System.out.println("Бұғатталған: " + (subscriber.getUserInstance().isBanned() ? "Иә" : "Жоқ"));
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

