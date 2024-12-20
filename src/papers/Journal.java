package papers;

import users.models.User;

import java.util.Objects;
import java.util.Vector;

/**
 * Класс, представляющий научный журнал.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Journal implements Comparable<Journal>{
	/**
	 * Название журнала.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public String uuid;
	public String title;

	/**
	 * Список статей, опубликованных в журнале.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	private Vector<ResearchPaper> articles;

	/**
	 * Список подписчиков журнала.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	private Vector<User> subscribers;

	/**
	 * Конструктор класса Journal.
	 * Инициализирует название журнала и создаёт пустые списки статей и подписчиков.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Journal(String uuid, String title){
		this.uuid = uuid;
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty.");
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
			throw new IllegalArgumentException("Subscriber cannot be null.");
		}
		if (!subscribers.contains(subscriber)) {
			subscribers.add(subscriber);
			System.out.println("User " + subscriber.getFirstName() + " has successfully subscribed to the journal " + this.title + ".");
		} else {
			System.out.println("User " + subscriber.getFirstName() + " is already subscribed to the journal " + this.title + ".");
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
			throw new IllegalArgumentException("Subscriber cannot be null.");
		}
		if (subscribers.remove(subscriber)) {
			System.out.println("User " + subscriber.getFirstName() + " has successfully unsubscribed from the journal " + this.title + ".");
		} else {
			System.out.println("User " + subscriber.getFirstName() + " was not found among the subscribers of the journal " + this.title + ".");
		}
	}

	/**
	 * Добавляет статью в журнал.
	 * @param paper Объект ResearchPaper, который добавляется.
	 */
	public void addArticle(ResearchPaper paper) {
		if (paper == null) {
			throw new IllegalArgumentException("Research paper cannot be null.");
		}
		articles.add(paper);
		System.out.println("Research paper \"" + paper.getTitle() + "\" has been added to the journal " + this.title + ".");
	}

	/**
	 * Удаляет статью из журнала.
	 * @param paper Объект ResearchPaper, который удаляется.
	 */
	public void removeArticle(ResearchPaper paper) {
		if (paper == null) {
			throw new IllegalArgumentException("Research paper cannot be null.");
		}
		if (articles.remove(paper)) {
			System.out.println("Research paper \"" + paper.getTitle() + "\" has been removed from the journal " + this.title + ".");
		} else {
			System.out.println("Research paper \"" + paper.getTitle() + "\" was not found in the journal " + this.title + ".");
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
			throw new IllegalArgumentException("Title cannot be null or empty.");
		}
		this.title = title;
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
		return "Journal{" +
				"title='" + title + '\'' +
				", articles=" + articles.size() +
				", subscribers=" + subscribers.size() +
				'}';
	}

	@Override
	public int compareTo(Journal o) {
		return this.getTitle().compareTo(o.getTitle());
	}
}
