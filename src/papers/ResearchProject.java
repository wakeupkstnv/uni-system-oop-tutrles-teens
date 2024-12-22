package papers;

import users.models.Researcher;
import core.CoreSystem;
import core.Language;

import java.util.Vector;

/**
 * Класс ResearchProject представляет исследовательский проект.
 * Содержит информацию о проекте, такие как идентификатор, тема, опубликованные статьи,
 * руководитель и список участников. Также предоставляет методы для управления проектом.
 */
public class ResearchProject {

	/**
	 * Идентификатор проекта.
	 */
	private Integer projectId;

	/**
	 * Тема проекта.
	 */
	private String topic;

	/**
	 * Список опубликованных статей по проекту.
	 */
	private Vector<ResearchPaper> publishedPapers;

	/**
	 * Руководитель исследования.
	 */
	private Researcher researchSupervisor;

	/**
	 * Список участников проекта.
	 */
	private Vector<Researcher> participants;

	/**
	 * Конструктор по умолчанию.
	 * Создаёт новый экземпляр исследовательского проекта.
	 */
	public ResearchProject() {
		this.publishedPapers = new Vector<>();
		this.participants = new Vector<>();
	}

	/**
	 * Добавляет участника к исследовательскому проекту.
	 *
	 * @param researcher Участник, добавляемый в проект.
	 * @return true, если участник был успешно добавлен; false в противном случае.
	 */
	public boolean joinProject(Researcher researcher) {
		if (researcher != null && !participants.contains(researcher)) {
			participants.add(researcher);
			return true;
		}
		return false;
	}

	/**
	 * Возвращает список всех опубликованных статей в виде строки.
	 *
	 * @return Строка с информацией о статьях.
	 */
	public String printPapers() {
		Language language = CoreSystem.getLanguageMode();

		StringBuilder papersInfo = new StringBuilder();

		if (language == Language.ENG) {
			papersInfo.append("Published Papers: \n");
			for (ResearchPaper paper : publishedPapers) {
				papersInfo.append(paper.getTitle()).append("\n");
			}

			if (researchSupervisor != null) {
				papersInfo.append("\nResearch Supervisor: ").append(researchSupervisor.getUserInstance().getFirstName()).append("\n");
			}

			if (participants.size() > 0) {
				papersInfo.append("\nParticipants: \n");
				for (Researcher participant : participants) {
					papersInfo.append(participant.getUserInstance().getFirstName()).append("\n");
				}
			} else {
				papersInfo.append("\nNo participants yet.\n");
			}
		} else if (language == Language.RUS) {
			papersInfo.append("Опубликованные статьи: \n");
			for (ResearchPaper paper : publishedPapers) {
				papersInfo.append(paper.getTitle()).append("\n");
			}

			if (researchSupervisor != null) {
				papersInfo.append("\nРуководитель исследования: ").append(researchSupervisor.getUserInstance().getFirstName()).append("\n");
			}

			if (participants.size() > 0) {
				papersInfo.append("\nУчастники: \n");
				for (Researcher participant : participants) {
					papersInfo.append(participant.getUserInstance().getFirstName()).append("\n");
				}
			} else {
				papersInfo.append("\nНет участников.\n");
			}
		} else if (language == Language.KZ) {
			papersInfo.append("Жарияланған мақалалар: \n");
			for (ResearchPaper paper : publishedPapers) {
				papersInfo.append(paper.getTitle()).append("\n");
			}

			if (researchSupervisor != null) {
				papersInfo.append("\nЗерттеу жетекшісі: ").append(researchSupervisor.getUserInstance().getFirstName()).append("\n");
			}

			if (participants.size() > 0) {
				papersInfo.append("\nҚатысушылар: \n");
				for (Researcher participant : participants) {
					papersInfo.append(participant.getUserInstance().getFirstName()).append("\n");
				}
			} else {
				papersInfo.append("\nҚатысушылар жоқ.\n");
			}
		}

		return papersInfo.toString();
	}


	// Геттеры и сеттеры

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Vector<ResearchPaper> getPublishedPapers() {
		return publishedPapers;
	}

	public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
		this.publishedPapers = publishedPapers;
	}

	public Researcher getResearchSupervisor() {
		return researchSupervisor;
	}

	public void setResearchSupervisor(Researcher researchSupervisor) {
		this.researchSupervisor = researchSupervisor;
	}

	public Vector<Researcher> getParticipants() {
		return participants;
	}

	public void setParticipants(Vector<Researcher> participants) {
		this.participants = participants;
	}
}
