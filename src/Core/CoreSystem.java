package Core;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class CoreSystem
{

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static String systemName;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	@SuppressWarnings("unused")
	private static CoreSystem SYSTEM_INSTANCE;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private static Language languageMode=Language.ENG;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */

	private CoreSystem(){
		super();
	}

	public static Language getLanguageMode() {
		return languageMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */


	@SuppressWarnings("static-access")
	public void setLanguageMode(Core.Language languageMode) {
		this.languageMode = languageMode;
	}
	
}

