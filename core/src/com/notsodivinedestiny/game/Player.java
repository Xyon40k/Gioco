package com.notsodivinedestiny.game;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends DynamicElement{
	public boolean landed;

  	public Player(float posX, float posY, World world, String pathtoasset) {
		super(posX, posY, 23, 32, world, pathtoasset);
		
		landed = true;
  	}

  	public void move(float x) {
		Vector2 vel = body.getLinearVelocity();
  		body.setLinearVelocity(new Vector2(x, vel.y));
  	}

	public void jump(float f){
		if(landed) {
			body.applyLinearImpulse(new Vector2(0,f*body.getMass()),new Vector2(0,-sprite.getHeight()/2), false);
		}
	}

  
}