#version 400 core

in vec3 position;
in vec2 textCoords;

out vec2 out_textCoods;

void main(void){
	gl_Position = vec4(position,1.0);
	out_textCoods = textCoords;
}
