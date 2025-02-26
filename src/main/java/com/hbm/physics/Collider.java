package com.hbm.physics;

import com.hbm.render.amlfrom1710.Vec3;

import javax.vecmath.Matrix3f;

public abstract class Collider {

	public float mass;
	public Matrix3f localInertiaTensor;
	public Vec3 localCentroid;
	
	public abstract Vec3 support(Vec3 direction);
	
	public abstract Collider copy();
	
	public abstract void debugRender();
}
