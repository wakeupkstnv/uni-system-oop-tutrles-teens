package users.view;


<<<<<<< HEAD
import core.CoreSystem;
import core.Language;
=======
>>>>>>> 95fbc31b11f295a6a844abb908b8036b6c77aec5
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
		languageMode();
		if (l==0) {
			System.out.println("Researcher"+r.getUserInstance().getFirstName()+"'s articles citations:" );
		for(ResearchPaper paper: r.getArticles()){
			System.out.println(paper.getCitations(format));
		}
		}
		else if(l==1){
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

