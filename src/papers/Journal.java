package papers;

import core.CoreSystem;
import users.models.Researcher;
import users.models.User;

import java.util.Objects;
import java.util.Vector;

import core.CoreSystem;
public class Journal implements Comparable<Journal>{
	
	public String uuid;
	public String title;
	private Vector<ResearchPaper> articles;
	private Vector<User> subscribers;
	private Vector<Researcher> subscribersR;

	public Journal(String uuid, String title){
		this.uuid = uuid;
		if (title == null || title.trim().isEmpty()) {
	        String message = "";
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                message = "Заголовок не может быть null или пустым.";  // Russian message
	                break;
	            case ENG:
	                message = "Title cannot be null or empty.";  // English message
	                break;
	            case KZ:
	                message = "Атауы null немесе бос бола алмайды.";  // Kazakh message
	                break;
	            }
	        throw new IllegalArgumentException(message);
	    }
		this.title = title;
		this.articles = new Vector<>();
		this.subscribers = new Vector<>();
	}

	/**
	 * Добавляет подписчика в журнал.
	 * @param subscriber Объект пользователя, который подписывается.
	 */
	public void addSubscriber(User subscriber) {
	    if (subscriber == null) {
	        String message = "";
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                message = "Подписчик не может быть null.\n";  // Russian message
	                break;
	            case ENG:
	                message = "Subscriber cannot be null.\n";  // English message
	                break;
	            case KZ:
	                message = "Жазылушы null бола алмайды.\n";  // Kazakh message
	                break;
	             }
	        throw new IllegalArgumentException(message);
	    }

	    if (!subscribers.contains(subscriber)) {
	        subscribers.add(subscriber);
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Пользователь " + subscriber.getFirstName() + " успешно подписался на журнал " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("User " + subscriber.getFirstName() + " has successfully subscribed to the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Пайдаланушы " + subscriber.getFirstName() + " журналға сәтті жазылды " + this.title + ".");
	                break;
	            }
	    } else {
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Пользователь " + subscriber.getFirstName() + " уже подписан на журнал " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("User " + subscriber.getFirstName() + " is already subscribed to the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Пайдаланушы " + subscriber.getFirstName() + " журналға бұрыннан жазылған " + this.title + ".");
	                break;
	            }
	    }
	}

	public void addSubscriber(Researcher subscriber) {
	    if (subscriber == null) {
	        String message = "";
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                message = "Подписчик не может быть null.\n";  // Russian message
	                break;
	            case ENG:
	                message = "Subscriber cannot be null.\n";  // English message
	                break;
	            case KZ:
	                message = "Жазылушы null бола алмайды.\n";  // Kazakh message
	                break;
	            }
	        throw new IllegalArgumentException(message);
	    }

	    if (!subscribersR.contains(subscriber)) {
	        subscribersR.add(subscriber);
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Исследователь " + subscriber.getUserInstance().getFirstName() + " успешно подписался на журнал " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("Researcher " + subscriber.getUserInstance().getFirstName() + " has successfully subscribed to the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Ғалым " + subscriber.getUserInstance().getFirstName() + " журналға сәтті жазылды " + this.title + ".");
	                break;
	             }
	    } else {
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Исследователь " + subscriber.getUserInstance().getFirstName() + " уже подписан на журнал " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("Researcher " + subscriber.getUserInstance().getFirstName() + " is already subscribed to the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Ғалым " + subscriber.getUserInstance().getFirstName() + " журналға бұрыннан жазылған " + this.title + ".");
	                break;
	           }
	    }
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	/**
	 * Удаляет подписчика из журнала.
	 * @param subscriber Объект пользователя, который отписывается.
	 */
	public void removeSubscriber(User subscriber) {
		
		if (subscriber == null) {
		    String message = "";
		    switch (CoreSystem.getLanguageMode()) {
		        case RUS:
		            message = "Подписчик не может быть null.\n";  // Russian message
		            break;
		        case ENG:
		            message = "Subscriber cannot be null.\n";  // English message
		            break;
		        case KZ:
		            message = "Жазылушы null бола алмайды.\n";  // Kazakh message
		            break;
		        default:
		            message = "Subscriber cannot be null.\n";  // Default message
		    }
		    throw new IllegalArgumentException(message);
		}

		if (subscribers.remove(subscriber)) {
			switch (CoreSystem.getLanguageMode()) {
				case RUS:
					System.out.println("Пользователь " + subscriber.getFirstName() + " успешно отписался от журнала " + this.title + ".");
				case ENG:
					System.out.println("User " + subscriber.getFirstName() + " has successfully unsubscribed from the journal " + this.title + ".");
				case KZ:
					System.out.println("Пайдаланушы " + subscriber.getFirstName() + " журналдан сәтті жазылды " + this.title + ".");
				}
			} else {
			    switch (CoreSystem.getLanguageMode()) {
		        case RUS:
		            System.out.println("Пользователь " + subscriber.getFirstName() + " не найден среди подписчиков журнала " + this.title + ".");
		            break;
		        case ENG:
		            System.out.println("User " + subscriber.getFirstName() + " was not found among the subscribers of the journal " + this.title + ".");
		            break;
		        case KZ:
		            System.out.println("Пайдаланушы " + subscriber.getFirstName() + " журналдың жазылушылары арасында табылмады " + this.title + ".");
		            break;
		      }
		
}
	}
	
	public void removeSubscriber(Researcher subscriber) {
	    if (subscriber == null) {
	        String message = "";
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                message = "Подписчик не может быть null.\n";  // Russian message
	                break;
	            case ENG:
	                message = "Subscriber cannot be null.\n";  // English message
	                break;
	            case KZ:
	                message = "Жазылушы null бола алмайды.\n";  // Kazakh message
	                break;
	            }
	        throw new IllegalArgumentException(message);
	    }

	    if (subscribers.remove(subscriber)) {
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Исследователь " + subscriber.getUserInstance().getFirstName() + " успешно отписался от журнала " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("Researcher " + subscriber.getUserInstance().getFirstName() + " has successfully unsubscribed from the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Ғалым " + subscriber.getUserInstance().getFirstName() + " журналдан сәтті жазылды " + this.title + ".");
	                break;
	            }
	    } else {
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Исследователь " + subscriber.getUserInstance().getFirstName() + " не найден среди подписчиков журнала " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("Researcher " + subscriber.getUserInstance().getFirstName() + " was not found among the subscribers of the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Ғалым " + subscriber.getUserInstance().getFirstName() + " журналдың жазылушылары арасында табылмады " + this.title + ".");
	                break;
	            }
	    }
	}

	/**
	 * Добавляет статью в журнал.
	 * @param paper Объект ResearchPaper, который добавляется.
	 */
	public void addArticle(ResearchPaper paper) {
	    if (paper == null) {
	        String message = "";
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                message = "Научная статья не может быть null.";  // Russian message
	                break;
	            case ENG:
	                message = "Research paper cannot be null.";  // English message
	                break;
	            case KZ:
	                message = "Ғылыми мақала null бола алмайды.";  // Kazakh message
	                break;
	            }
	        throw new IllegalArgumentException(message);
	    }
	    articles.add(paper);

	    switch (CoreSystem.getLanguageMode()) {
	        case RUS:
	            System.out.println("Научная статья \"" + paper.getTitle() + "\" была добавлена в журнал " + this.title + ".");
	            break;
	        case ENG:
	            System.out.println("Research paper \"" + paper.getTitle() + "\" has been added to the journal " + this.title + ".");
	            break;
	        case KZ:
	            System.out.println("Ғылыми мақала \"" + paper.getTitle() + "\" журналға қосылды " + this.title + ".");
	            break;
	       }
	}

	/**
	 * Удаляет статью из журнала.
	 * @param paper Объект ResearchPaper, который удаляется.
	 */
	public void removeArticle(ResearchPaper paper) {
	    if (paper == null) {
	        String message = "";
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                message = "Научная статья не может быть null.";  // Russian message
	                break;
	            case ENG:
	                message = "Research paper cannot be null.";  // English message
	                break;
	            case KZ:
	                message = "Ғылыми мақала null бола алмайды.";  // Kazakh message
	                break;
	            }
	        throw new IllegalArgumentException(message);
	    }

	    if (articles.remove(paper)) {
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Научная статья \"" + paper.getTitle() + "\" была удалена из журнала " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("Research paper \"" + paper.getTitle() + "\" has been removed from the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Ғылыми мақала \"" + paper.getTitle() + "\" журналдан алынды " + this.title + ".");
	                break;
	             }
	    } else {
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                System.out.println("Научная статья \"" + paper.getTitle() + "\" не была найдена в журнале " + this.title + ".");
	                break;
	            case ENG:
	                System.out.println("Research paper \"" + paper.getTitle() + "\" was not found in the journal " + this.title + ".");
	                break;
	            case KZ:
	                System.out.println("Ғылыми мақала \"" + paper.getTitle() + "\" журналдан табылмады " + this.title + ".");
	                break;
	             }
	    }
	}


	/**
	 * Получает список всех подписчиков журнала.
	 * @return Вектор подписчиков.
	 */
	public Vector<User> getSubscribers() {
		return new Vector<>(subscribers);
	}
	
	
	/**
	 * Получает список всех статей журнала.
	 * @return Вектор статей.
	 */
	public Vector<ResearchPaper> getArticles() {
		return new Vector<>(articles);
	}

	/**
	 * Получает заголовок журнала.
	 * @return Заголовок журнала.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Устанавливает новый заголовок для журнала.
	 * @param title Новый заголовок.
	 */
	public void setTitle(String title) {
	    if (title == null || title.trim().isEmpty()) {
	        String message = "";
	        switch (CoreSystem.getLanguageMode()) {
	            case RUS:
	                message = "Заголовок не может быть null или пустым.";  // Russian message
	                break;
	            case ENG:
	                message = "Title cannot be null or empty.";  // English message
	                break;
	            case KZ:
	                message = "Атау null немесе бос бола алмайды.";  // Kazakh message
	                break;
	           }
	        throw new IllegalArgumentException(message);
	    }
	    this.title = title;

	    switch (CoreSystem.getLanguageMode()) {
	        case RUS:
	            System.out.println("Заголовок журнала успешно изменен на: " + this.title);
	            break;
	        case ENG:
	            System.out.println("The journal title has been successfully changed to: " + this.title);
	            break;
	        case KZ:
	            System.out.println("Журналдың атауы сәтті өзгертілді: " + this.title);
	            break;
	        }
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Journal journal = (Journal) o;
		return Objects.equals(title, journal.title) && Objects.equals(articles, journal.articles) && Objects.equals(subscribers, journal.subscribers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, articles, subscribers);
	}

	@Override
	public String toString() {
	    String journalInfo = "";
	    switch (CoreSystem.getLanguageMode()) {
	        case RUS:
	            journalInfo = "Журнал{" +
	                    "название='" + title + '\'' +
	                    ", статьи=" + articles.size() +
	                    ", подписчики=" + subscribers.size() +
	                    '}';
	            break;
	        case ENG:
	            journalInfo = "Journal{" +
	                    "title='" + title + '\'' +
	                    ", articles=" + articles.size() +
	                    ", subscribers=" + subscribers.size() +
	                    '}';
	            break;
	        case KZ:
	            journalInfo = "Журнал{" +
	                    "атауы='" + title + '\'' +
	                    ", мақалалар=" + articles.size() +
	                    ", жазылушылар=" + subscribers.size() +
	                    '}';
	            break;
	        }
	    return journalInfo;
	}


	@Override
	public int compareTo(Journal o) {
		return this.getTitle().compareTo(o.getTitle());
	}
}
