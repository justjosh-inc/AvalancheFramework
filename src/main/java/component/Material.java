package component;

public class Material {

	private float[] diffuseColor = {1.0f, 1.0f, 1.0f};  // Default white
    private float[] specularColor = {1.0f, 1.0f, 1.0f}; // Default white
    private float[] ambientColor = {0.2f, 0.2f, 0.2f};  // Default low ambient
    private float shininess = 32.0f;  // Default shininess
    private float opacity = 1.0f;     // Fully opaque
    private String diffuseTexturePath;
    private String normalMapPath;
    private String specularMapPath;
    private float reflectivity = 0.5f;  // Default reflectivity
    private float fresnelFactor = 0.04f; // Default Fresnel effect factor
    private float metallic = 0.0f;  // Default non-metallic
    private float roughness = 0.5f; // Default roughness
    
    
	public float[] getDiffuseColor() {
		return diffuseColor;
	}
	public void setDiffuseColor(float[] diffuseColor) {
		this.diffuseColor = diffuseColor;
	}
	public float[] getSpecularColor() {
		return specularColor;
	}
	public void setSpecularColor(float[] specularColor) {
		this.specularColor = specularColor;
	}
	public float[] getAmbientColor() {
		return ambientColor;
	}
	public void setAmbientColor(float[] ambientColor) {
		this.ambientColor = ambientColor;
	}
	public float getShininess() {
		return shininess;
	}
	public void setShininess(float shininess) {
		this.shininess = shininess;
	}
	public float getOpacity() {
		return opacity;
	}
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	public String getDiffuseTexturePath() {
		return diffuseTexturePath;
	}
	public void setDiffuseTexturePath(String diffuseTexturePath) {
		this.diffuseTexturePath = diffuseTexturePath;
	}
	public String getNormalMapPath() {
		return normalMapPath;
	}
	public void setNormalMapPath(String normalMapPath) {
		this.normalMapPath = normalMapPath;
	}
	public String getSpecularMapPath() {
		return specularMapPath;
	}
	public void setSpecularMapPath(String specularMapPath) {
		this.specularMapPath = specularMapPath;
	}
	public float getReflectivity() {
		return reflectivity;
	}
	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
	public float getFresnelFactor() {
		return fresnelFactor;
	}
	public void setFresnelFactor(float fresnelFactor) {
		this.fresnelFactor = fresnelFactor;
	}
	public float getMetallic() {
		return metallic;
	}
	public void setMetallic(float metallic) {
		this.metallic = metallic;
	}
	public float getRoughness() {
		return roughness;
	}
	public void setRoughness(float roughness) {
		this.roughness = roughness;
	}
    
    
}
