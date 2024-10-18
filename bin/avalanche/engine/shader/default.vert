#version 400 core

in vec3 position;

out vec3 out_colour;

uniform mat4 transformationMatrix;

void main(void){
	gl_Position = transformationMatrix * vec4(position,1.0);
	out_colour = position;
}