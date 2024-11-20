#version 400 core

in vec2 out_textureCoords;

out vec4 fragColour;

uniform sampler2D textureSampler;

void main(){
	fragColour = texture(textureSampler,out_textureCoords);
}
