#version 400 core

in vec2 out_textureCoords;

out vec4 fragColour;

uniform sampler2D textureSampler;
uniform float has_transparency;
uniform vec4 baseColour;

void main(){
	vec4 textColour = texture(textureSampler,out_textureCoords);
	
	if (has_transparency == 1){
		if (textColour.a < 0.5){
			discard;
		}
	}
	
	textColour.rgb += baseColour.rgb;
	textColour.a = 0.1;
	fragColour = textColour;
}
