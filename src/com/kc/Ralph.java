package com.kc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import java.util.Vector;


import lib.AnimControl;
import lib.GameObject;

enum RalphState{
	Play, Transition;
}
public class Ralph extends GameObject{

	Vector<Image>vImage;
	int xPos = 2;
	int yPos = 4;
	float targetX;
	float targetY;
	Vector<Brick>vBrick;
	float shooterCoolDown = 5f;
	float moveCoolDown = 0f;
	Random rand = new Random();
	Vector<Floor>vFloor;
	int phase;
	RalphState ralphState = RalphState.Transition;
	AnimControl animControl = new AnimControl();
	
	public Ralph(float x, float y, float width, float height,Vector<Brick>vBrick, Vector<Floor>vFloor) {
		super(x, y, width, height);
		vImage = AssetManager.getInstance().vRalph;
		this.vBrick = vBrick;
		this.vFloor = vFloor;
		
		animControl.addAnim("StraightLoop", "Idle", 0, 1, 0.5f);
		animControl.addAnim("StraightLoop", "Attack", 3, 4, 0.5f);
		animControl.addAnim("StraightLoop", "Climb", 5, 6, 0.5f);
		animControl.addAnim("StraightLoop", "WalkLeft", 7, 8, 0.5f);
		animControl.addAnim("StraightLoop", "WalkRight", 9, 10, 0.5f);
	}

	@Override
	public void init() {
		x = 800;
		y = 480;
		targetX = SceneGame.gridPoint.get(xPos).x - 16;
		targetY = 480 - 56*2*yPos;
	}
	@Override
	public void update(double tpf) {
		animControl.updateAnim((float)tpf);
		if(SceneGame.currGameState == GameState.PlayState)
		{
			//tembak2 klo misalnya lagi play
			shooterCoolDown -= tpf;
			if(shooterCoolDown <= 0 && ralphState == RalphState.Play){
				vBrick.add(new Brick(x+rand.nextInt(100), y, 20, 16));
				shooterCoolDown = 3;
			}
		}
		
		boolean increasePhase = true;
		for(int i=0;i<phase*5 && phase*5<=vFloor.size();i++){
			for(int j=0;j<vFloor.get(i).vWindow.size();j++){
				if(vFloor.get(i).vWindow.get(j).status != WindowState.REPAIRED && vFloor.get(i).vWindow.get(j).status != WindowState.CLOSED){
					increasePhase = false;
					break;
				}
			}
			if(!increasePhase)break;
		}
		if(increasePhase && phase*5<vFloor.size())
		{
			phase++;
			moveCoolDown = 0;
			ralphState = RalphState.Play;
		}
		
		moveCoolDown -= tpf;
		if(moveCoolDown <= 0 && ralphState == RalphState.Play){
			xPos = rand.nextInt(5);
			yPos = phase * 5 - 1;
			targetX = SceneGame.gridPoint.get(xPos).x - 16;
			targetY = 480-(56*2)*yPos;
			moveCoolDown = 5f;
		}
		if(Math.abs(targetX-x)>2 || Math.abs(targetY-y)>3)
		{
			ralphState = RalphState.Transition;
			if(Math.abs(targetX-x)>2){
				if(targetX>x){
					if(animControl.getActiveName().equals("WalkRight")==false)
						animControl.setActiveAnim("WalkRight");
					x += 100*tpf;
				}
				else if (targetX<x){
					if(animControl.getActiveName().equals("WalkLeft")==false)
						animControl.setActiveAnim("WalkLeft");
					x -= 100*tpf;
				}
			}else if(Math.abs(targetY-y)>3){
				if(targetY>y){
					if(animControl.getActiveName().equals("Climb")==false)
						animControl.setActiveAnim("Climb");
					y += 200*tpf;
				}
				else if (targetY<y){
					if(animControl.getActiveName().equals("Climb")==false)
						animControl.setActiveAnim("Climb");
					y -= 200*tpf;
				}
			}
		}else{
			if(animControl.getActiveName().equals("Attack")==false)
				animControl.setActiveAnim("Attack");
			ralphState = RalphState.Play;
		}
	}
	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(vImage.get(animControl.getIndex()), (int)x, (int)y, (int)width, (int)height, null);
	}
}
