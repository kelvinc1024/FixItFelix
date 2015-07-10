package com.kc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Vector;

import lib.AnimControl;
import lib.GameObject;
import lib.SimpleApplication;

public class Felix extends GameObject{
	
	Vector<Image>vImage;
	int xPos = 3;
	int yPos = 0;
	float freezeTime = 0f;
	Vector<Floor>vFloor;
	int phase;
	SimpleApplication simpleApplication;
	AnimControl animControl = new AnimControl();
	
	public Felix(SimpleApplication simpleApplication,float x, float y, float width, float height, Vector<Floor>vFloor) {
		super(x, y, width, height);
		vImage = AssetManager.getInstance().vFelix;
		this.vFloor = vFloor;
		phase = 1;
		this.simpleApplication = simpleApplication;
		
		
		animControl.addAnim("StraightLoop", "HammerDown", 0, 0, 0.5f);
		animControl.addAnim("StraightLoop", "HammerUp", 1, 1, 0.5f);
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public void update(double tpf) {
		animControl.updateAnim((float)tpf);
		freezeTime -= tpf;
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
		if(increasePhase && phase*5<=vFloor.size())phase++;
		else if(phase*5>vFloor.size()){
			SimpleApplication.faceEffect = 0.1f;
			simpleApplication.changeScene(new NextStageScene(simpleApplication));
		}
	}
	
	@Override
	public void render(Graphics2D g2d) {
		x = SceneGame.gridPoint.get(xPos).x;
		y = 515 - 56*2*yPos;
		g2d.drawImage(vImage.get(animControl.getIndex()), (int)x, (int)y, (int)width, (int)height, null);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(SceneGame.currGameState == GameState.PlayState)
		{
			if(e.getKeyCode()==KeyEvent.VK_LEFT && freezeTime <= 0){
				if(xPos>0)xPos--;
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT && freezeTime <= 0){
				if(xPos<SceneGame.gridPoint.size()-1)xPos++;
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN && freezeTime <= 0 && yPos>(phase-1)*5){
				if(yPos>0)yPos--;
			}
			if(e.getKeyCode()==KeyEvent.VK_UP && freezeTime <= 0 && yPos<(phase)*5-1){
				if(yPos<SceneGame.totalFloor-1)yPos++;
			}
			if(e.getKeyCode()==KeyEvent.VK_SPACE && freezeTime <= 0){
				freezeTime = .5f;
				if(vFloor.get(yPos).vWindow.get(xPos).status==WindowState.BROKEN_BOTH)
				{
					if(animControl.getActiveName().equals("HammerDown")==false)
						animControl.setActiveAnim("HammerDown");
					SceneGame.score++;
					vFloor.get(yPos).vWindow.get(xPos).status = WindowState.BROKEN_BOTTOM;
				}
				else if(vFloor.get(yPos).vWindow.get(xPos).status==WindowState.BROKEN_TOP)
				{
					if(animControl.getActiveName().equals("HammerDown")==false)
						animControl.setActiveAnim("HammerDown");
					SceneGame.score++;
					vFloor.get(yPos).vWindow.get(xPos).status = WindowState.REPAIRED;
				}
				else if(vFloor.get(yPos).vWindow.get(xPos).status==WindowState.BROKEN_BOTTOM)
				{
					if(animControl.getActiveName().equals("HammerDown")==false)
						animControl.setActiveAnim("HammerDown");
					SceneGame.score++;
					vFloor.get(yPos).vWindow.get(xPos).status = WindowState.REPAIRED;
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			if(animControl.getActiveName().equals("HammerUp")==false)
				animControl.setActiveAnim("HammerUp");
		}
	}
}
