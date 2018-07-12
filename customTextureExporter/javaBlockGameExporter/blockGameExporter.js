package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class {{settings.tpsName}} {
	
	private static GLGame glGame;
	
	public static Texture {{texture.trimmedName}};
	{% for sprite in allSprites %}
	public static TextureRegion {{sprite.trimmedName}};{% endfor %}
	
	public static void load(GLGame game) {
		glGame = game;
		load();
	}
	
	public static void load() {
		{{texture.trimmedName}} = new Texture (glGame, "{{texture.fullName}}");
		{% for sprite in allSprites %}
		{{sprite.trimmedName}} = new TextureRegion({{texture.trimmedName}}, {{sprite.frameRect.x}}, {{sprite.frameRect.y}}, {{sprite.size.width}}, {{sprite.size.height}});{% endfor %}
	}
	
	public static void reload() {
		if ({{texture.trimmedName}} != null) {
			{{texture.trimmedName}}.reload();
		}
	}
	
	public static void dispose() {
		if ({{texture.trimmedName}} != null) {
			{{texture.trimmedName}}.dispose();
		}
	}
	
	public static void clear() {
		{{texture.trimmedName}} = null;
		{% for sprite in allSprites %}
		{{sprite.trimmedName}} = null;{% endfor %}
	}
}