package test;

import core.Window;

public class Main {

	public static void main(String[] args) {
		Window window = Window.get();
		window.init();
		
		while (!window.shouldClose()) {
			window.update();
		}
		
		window.close();
	}

}
