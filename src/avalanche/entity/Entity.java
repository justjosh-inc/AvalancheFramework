package avalanche.entity;

import java.util.HashMap;
import java.util.Map;

import avalanche.components.Component;
import avalanche.engine.time.Clock;

public class Entity {

	Map<String,Component> components = new HashMap<>();
	private double deltaTime = 0.0;
	
	public <T extends Component> void addComponent(T component) {
		components.put(component.getComponentID(),component);
	}
	
	public Component getComponent(String ID) {
        return components.getOrDefault(ID, null);
	}
	
	public void update() {
		deltaTime = Clock.getDeltaTime();
		for (Component component : components.values()) {
             component.update();
        }
	}
	
	public void render() {
		for (Component component : components.values()) {
			component.render();
		}
	}
	
}