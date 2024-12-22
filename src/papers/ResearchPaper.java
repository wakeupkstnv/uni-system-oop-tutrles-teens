package papers;

import users.models.Researcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Класс, представляющий научную статью.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ResearchPaper {
    /**
     * Количество страниц в статье.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    private Vector<String> pages;

    /**
     * Количество цитирований статьи.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    private int citations;

    /**
     * Дата публикации статьи.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    private Date published;


    /**
     * Заголовок статьи.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    public String title;

    /**
     * Список авторов статьи.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    private List<Researcher> authors;

    /**
     * Цифровой объектный идентификатор (DOI) статьи.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    private String doi;

    /**
     * Конструктор класса ResearchPaper.
     * Инициализирует список авторов.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResearchPaper() {
        super();
        this.authors = new ArrayList<>();
    }

    /**
     * Конструктор класса ResearchPaper с параметрами.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param title Заголовок статьи.
     * @param pages Количество страниц.
     * @param published Дата публикации.
     * @param doi Цифровой объектный идентификатор.
     */
    public ResearchPaper(String title, Vector<String> pages, Date published, String doi) {
        this();
        setTitle(title);
        setPages(pages);
        setPublished(published);
        setDoi(doi);
        this.citations = 0;
    }

    /**
     * Добавляет автора к статье.
     * @param researcher Объект Researcher, который добавляется в качестве автора.
     */

    public void addAuthor(Researcher researcher) {
        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null.");
        }
        if (!authors.contains(researcher)) {
            authors.add(researcher);
            System.out.println("Researcher " + researcher + " has been added as an author to the paper \"" + this.title + "\".");
        } else {
            System.out.println("Researcher " + researcher + " is already an author of the paper \"" + this.title + "\".");
        }
    }

    /**
     * Удаляет автора из статьи.
     * @param researcher Объект Researcher, который удаляется из авторов.
     */
    public void removeAuthor(Researcher researcher) {
        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null.");
        }
        if (authors.remove(researcher)) {
            System.out.println("Researcher " + researcher + " has been removed from the authors of the paper \"" + this.title + "\".");
        } else {
            System.out.println("Researcher " + researcher + " was not found among the authors of the paper \"" + this.title + "\".");
        }
    }

    /**
     * Получает количество страниц в статье.
     * @return Количество страниц.
     */
    public Vector<String> getPages() {
        return pages;
    }

    /**
     * Устанавливает количество страниц в статье.
     * @param pages Количество страниц.
     */
    public void setPages(Vector<String> pages) {
        this.pages = pages;
    }

    /**
     * Получает количество цитирований статьи.
     * @return Количество цитирований.
     */
    public int getCitations() {
        return citations;
    }

    /**
     * Устанавливает количество цитирований статьи.
     * @param citations Количество цитирований.
     */
    public void setCitations(int citations) {
        if (citations < 0) {
            throw new IllegalArgumentException("Number of citations cannot be negative.");
        }
        this.citations = citations;
    }

    /**
     * Получает дату публикации статьи.
     * @return Дата публикации.
     */
    public Date getPublished() {
        return new Date(published.getTime());
    }

    /**
     * Устанавливает дату публикации статьи.
     * @param published Дата публикации.
     */
    public void setPublished(Date published) {
        if (published == null) {
            throw new IllegalArgumentException("Published date cannot be null.");
        }
        this.published = new Date(published.getTime());
    }

    /**
     * Получает заголовок статьи.
     * @return Заголовок статьи.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок статьи.
     * @param title Заголовок статьи.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    /**
     * Получает список авторов статьи.
     * @return Список авторов.
     */
    public List<Researcher> getAuthors() {
        return new ArrayList<>(authors);
    }

    /**
     * Устанавливает список авторов статьи.
     * @param authors Список авторов.
     */
    public void setAuthors(List<Researcher> authors) {
        if (authors == null) {
            throw new IllegalArgumentException("Authors list cannot be null.");
        }
        this.authors = new ArrayList<>(authors);
    }

    /**
     * Получает DOI статьи.
     * @return DOI статьи.
     */
    public String getDoi() {
        return doi;
    }

    /**
     * Устанавливает DOI статьи.
     * @param doi DOI статьи.
     */
    public void setDoi(String doi) {
        if (doi == null || doi.trim().isEmpty()) {
            throw new IllegalArgumentException("DOI cannot be null or empty.");
        }
        this.doi = doi;
    }

    /**
     * Выводит информацию о статье в заданном формате.
     * @return Строка с информацией о статье.
     */
    public <SimpleDateFormat> String printPapers() {
        StringBuilder authorsList = new StringBuilder();
        for (Researcher author : authors) {
            authorsList.append(author.getUserInstance().getFirstName()).append(" ").
                    append(author.getUserInstance().getLastName()).append(", ");
        }
        if (authorsList.length() > 0) {
            authorsList.setLength(authorsList.length() - 2); // Убираем последнюю запятую
        }
        return "Title: " + title +
                "\nDOI: " + doi +
                "\nPages: " + pages +
                "\nCitations: " + citations +
                "\nAuthors: " + authorsList.toString();
    }
    public String getCitations(Format f) {
        String citation = "";
        switch (f) {
            case PLAINTEXT:
                citation += "author={";
                citation += authors.stream().map(t -> t.toString()).
                        collect(Collectors.joining(","));
                citation += citation += "},\ntitle={" + title + "},\ndoi={" + doi + "}";
                incrementCitations();
            case BIBTEXT:
                citation += authors.stream().map(n->n.toString())
                        .collect(Collectors.joining(", "));
                citation+=", " + title + ", "+ published 	+ ", " + doi;
        }
        return citation;
    }

    /**
     * Увеличивает количество цитирований статьи на 1.
     */
    private void incrementCitations() {
        this.citations++;
        System.out.println("Citations for the paper \"" + this.title + "\" have been incremented to " + this.citations + ".");
    }

    /**
     * Уменьшает количество цитирований статьи на 1.
     */
    public void decrementCitations() {
        if (this.citations > 0) {
            this.citations--;
            System.out.println("Citations for the paper \"" + this.title + "\" have been decremented to " + this.citations + ".");
        } else {
            System.out.println("Citations cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", authors=" + authors.size() +
                ", published=" + published +
                ", pages=" + pages +
                ", citations=" + citations +
                ", doi='" + doi + '\'' +
                '}';
    }

}