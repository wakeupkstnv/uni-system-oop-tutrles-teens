package users.view;


import Core.CoreSystem;
import Core.Language;
import papers.Format;
import papers.ResearchPaper;
import users.models.Researcher;


@SuppressWarnings("rawtypes")
public class ResearcherView extends UserView
{
	
	public ResearcherView(){
		super();
	}

	public void printPapers(Researcher r, Format format) {
		if (CoreSystem.getLanguageMode()==Language.ENG) {
			System.out.println("Researcher"+r.getUserInstance().getFirstName()+"'s articles citations:" );
		for(ResearchPaper paper: r.getArticles()){
			System.out.println(paper.getCitations(format));
		}
		}
		else if(CoreSystem.getLanguageMode()==Language.RUS) {
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
	
}

