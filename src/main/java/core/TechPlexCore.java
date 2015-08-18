package core;

public class TechPlexCore {
	private static TechPlexCore instance;
	
	/**
	 * Prevents initialising this class, making it a singleton
	 */
	private TechPlexCore() {}
	
	/**
	 * If nonexistent, this method creates a new instance of TechPlexCore
	 * else returns the existent one
	 * @return the instance of TechPlexCore
	 */
	public static TechPlexCore GetInstance() {
		if (instance == null)
			instance = new TechPlexCore();
		return instance;
	}
	
	public void preinit() {
		
	}
	
	public void init() {
		
	}
}
