#version 400 core

in vec3 position;

out vec3 out_colour;

void main(void){
	gl_Position = vec4(position,1.0);
	out_colour = vec3(position.x + 0.5,1.0,position.z + 0.5);
}