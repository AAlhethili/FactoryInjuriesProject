package simulation;

public enum injuries {
	Brokenbonel(20, 1), 
	toxicexposure(10, 3),
	Spinalcordinjurie(20, 4),
	Skindisorders(10,3),
	Poisonings(10,2),
	Hearingloss(20,4),
	Visionloss(15,4),
	Chocking(15,4),
	Amputation(10,4);
	
	int t;
	int lvl;
	injuries(int time, int level){
		t=time;
		lvl=level;
	}
}
