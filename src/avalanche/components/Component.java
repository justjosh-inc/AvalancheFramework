package avalanche.components;

public class Component {
	
	public static String TYPE_TEXTURE = "TEXTURE";
	public static String TYPE_MESH = "MESH";
	public static String TYPE_TRANSFORM = "TRANSFORM";
	
	
	protected String ID;
	
	public Component(String type) {
		ID = type;
	}
	
	public String getComponentID() {
		return this.ID;
	}

	public void init() {
		
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	public void cleanUp() {
		
	}
}
