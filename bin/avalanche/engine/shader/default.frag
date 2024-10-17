#version 400 core

in vec3 out_colour;

out vec4 FragColour;

void main(void) {
    FragColour = vec4(out_colour,1.0);
}