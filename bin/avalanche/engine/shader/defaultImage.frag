#version 400 core

in vec2 out_textCoods;

out vec4 FragColour;

uniform sampler2D textureSampler;

void main(void) {
    FragColour = texture(textureSampler,out_textCoods);
}
