package avalanche.engine.time;

public class Clock {

	private double drawInterval = 0.0;
	private double delta = 0.0;
	private long lastTime = System.nanoTime();
	private long currentTime = 0;
	private long timer = 0;
	private double deltaTime = 0.0;
	
	public Clock() {
		this.deltaTime = 1.0;
	}
	
	public int getFPS() {
		return (int) (1.0 / deltaTime); // Calculate FPS
	}
	
	public float getDeltaTime() {
        long currentTime = System.nanoTime(); // Get the current time
        deltaTime = (currentTime - lastTime) / 1_000_000_000.0; // Calculate delta time in seconds
        lastTime = currentTime; // Update lastTime to the current time for the next call
        return (float) deltaTime;
    }
	
	public void tick(int fps) {
		drawInterval = 1000000000 / fps;
		deltaTime = getDeltaTime();
		while (true) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			if (delta >= 1) {
				delta--;
				break;
				
			}
			
			if (timer >= 1000000000) {
				timer = 0;
			}
		}
		lastTime = currentTime;
	}
}