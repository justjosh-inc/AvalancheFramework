#version 400 core

in vec3 position;

out vec3 colour;

void main(){
	gl_Position = vec4(position,1.0);
	colour = vec3(position.x + 0.18,0.8,position.y + 0.18);
}
