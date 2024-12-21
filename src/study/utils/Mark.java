package study.utils;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Mark
{
	GPA gpa;
	private float firstAttestation;
	private float secondAttestation;
	private float finalExam;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Mark(){
		this.firstAttestation = 0;
		this.secondAttestation = 0;
		this.finalExam = 0;
	}

	public void addPointsForFirstAttestation(float points){
		if(firstAttestation + points < 30){
			this.firstAttestation += points;
		};
	}

	public void addPointsForSecondAttestation(float points){
		if(secondAttestation + points < 30){
			this.secondAttestation += points;
		}
	}

	public int calculateAttestation(){
		return Math.ceil(Math.min(this.firstAttestation + this.secondAttestation, 60)) + finalExam;
	}

	public float getFirstAttestation(){
		return this.firstAttestation;
	}

	public float getSecondAttestation(){
		return this.secondAttestation;
	}

	public float getFinal(){
		return this.finalExam;
	}

	public void putPointToFinal(float point){
		if (calculateAttestation() < 30){
			this.finalExam = 0;
		}
		this.finalExam = Math.min(40, point);
	}

}

