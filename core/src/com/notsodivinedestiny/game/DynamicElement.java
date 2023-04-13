package com.notsodivinedestiny.game;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.*;

public class DynamicElement {
    public Body body;
    public Sprite sprite;
    public float width;
    public float height;
    public float posx;
    public float posy;

    public DynamicElement(float posX, float posY, float width, float height, World world, String pathtoasset) {
        sprite = new Sprite(new Texture(pathtoasset));
    	sprite.setPosition(posX, posY);
    	sprite.setSize(width, height);

        this.posx = posX;
        this.posy = posY;
        this.width = width;
        this.height = height;

    	body = createBody(world);
    }

    public Body createBody(World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(posx, posy);
		Body body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.0f;

		body.createFixture(fixtureDef);

		shape.dispose();
		return body;
	}

    public Vector2 getCenterPos() {
        Vector2 pos = body.getPosition();
        return new Vector2(pos.x + width*0.5f, pos.y + height*0.5f);
    }

    public void update() {
		Vector2 pos = body.getPosition();
		sprite.setPosition(pos.x, pos.y);
    }

    public void draw(Batch batch) {
        batch.draw(sprite, posx, posy);
    }
}

