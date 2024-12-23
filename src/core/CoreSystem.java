package core;

public class CoreSystem {

	private static CoreSystem instance;
	public static String systemName = "WSP System";

	private static Language languageMode = Language.ENG;

	private CoreSystem() {
		// Приватный конструктор для реализации Singleton
	}

	public static synchronized CoreSystem getInstance() {
		if (instance == null) {
			instance = new CoreSystem();
		}
		return instance;
	}

	public static Language getLanguageMode() {
		return languageMode;
	}

	public static void setLanguageMode(Language languageMode) {
		CoreSystem.languageMode = languageMode;
	}
}
