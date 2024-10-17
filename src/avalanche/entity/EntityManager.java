package avalanche.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

	List<Entity> entities = new ArrayList<>();
	
	public void update() {
		for (Entity entity : entities) {
			entity.update();
		}
	}
	
	public void render() {
		for (Entity entity : entities) {
			entity.render();
		}
	}
	
	public void cleanUp() {
		for (Entity entity : entities) {
			entity.cleanUp();
		}
	}
}
