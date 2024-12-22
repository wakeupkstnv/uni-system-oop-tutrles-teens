package papers;

import users.models.Researcher;
import core.CoreSystem;
import core.Language;

import java.util.Vector;

public class ResearchProject {

	private Integer projectId;
	private String topic;
	private Vector<ResearchPaper> publishedPapers;
	private Researcher researchSupervisor;
	private Vector<Researcher> participants;
	public ResearchProject() {
		this.publishedPapers = new Vector<>();
		this.participants = new Vector<>();
	}
	public boolean joinProject(Researcher researcher) {
		if (researcher != null && !participants.contains(researcher)) {
			participants.add(researcher);
			return true;
		}
		return false;
	}

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
