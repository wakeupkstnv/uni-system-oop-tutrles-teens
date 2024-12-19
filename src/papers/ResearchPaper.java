package papers;

import users.models.Researcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Класс для представления научной статьи (ResearchPaper)
 */
public class ResearchPaper {
    private String title;
    private String journal;
    private String doi;
    private int pages;
    private int citations;
    private Date published;
    private Vector<Researcher> authors;

    /**
     * Конструктор по умолчанию
     */
    public ResearchPaper() {
        this.authors = new Vector<>();
    }

    /**
     * Полный конструктор для инициализации всех полей
     */
    public ResearchPaper(String title, String journal, String doi, int pages, int citations, Date published, Vector<Researcher> authors) {
        this.title = title;
        this.journal = journal;
        this.doi = doi;
        this.pages = pages;
        this.citations = citations;
        this.published = published;
        this.authors = authors;
    }

    /**
     * Геттеры и сеттеры для всех полей
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Vector<Researcher> getAuthors() {
        return authors;
    }

    public void setAuthors(Vector<Researcher> authors) {
        this.authors = authors;
    }

    /**
     * Метод для вывода информации о статье
     */
    public String printPapers() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder authorsList = new StringBuilder();
        for (Researcher author : authors) {
            authorsList.append(author.getFirstName()).append(" ").append(author.getLastName()).append(", ");
        }
        if (authorsList.length() > 0) {
            authorsList.setLength(authorsList.length() - 2); // Убираем последнюю запятую
        }

        return "Title: " + title +
                "\nJournal: " + journal +
                "\nDOI: " + doi +
                "\nPages: " + pages +
                "\nCitations: " + citations +
                "\nPublished: " + sdf.format(published) +
                "\nAuthors: " + authorsList.toString();
    }

    /**
     * Метод для форматированного вывода цитирований
     */
    public String getCitations(String format) {
        if (format.equalsIgnoreCase("APA")) {
            return authors.get(0).getLastName() + " et al. (" + new SimpleDateFormat("yyyy").format(published) + "). " +
                    title + ". " + journal + ". DOI: " + doi;
        } else if (format.equalsIgnoreCase("MLA")) {
            return authors.get(0).getLastName() + ", " + authors.get(0).getFirstName() +
                    ", et al. \"" + title + ".\" " + journal + ", " +
                    new SimpleDateFormat("yyyy").format(published) + ", pp. " + pages + ". DOI: " + doi;
        } else {
            return "Unknown format.";
        }
    }

    /**
     * Метод для добавления автора
     */
    public void addAuthor(Researcher author) {
        if (author != null && !authors.contains(author)) {
            authors.add(author);
        }
    }

    /**
     * Переопределение метода toString()
     */
    @Override
    public String toString() {
        return printPapers();
    }
}
