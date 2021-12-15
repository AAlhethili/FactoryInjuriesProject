package simulation;

public enum injuries {
	Brokenbonel(40, 50, 1), 
	toxicexposure(30, 20, 1),
	Spinalcordinjurie(100, 30, 3),
	Skindisorders(30, 30, 1),
	Poisonings(60, 40, 2),
	Hearingloss(40, 40, 2),
	Visionloss(50, 40, 3),
	Chocking(10, 10, 1),
	Amputation(120, 10, 3),
	None(0,0,0);
	int healingtime;
	int permenanttime;
	int lvl;
	
	injuries(int ht, int pt, int level){
		ht=healingtime;
		pt=permenanttime;
		lvl=level;
	}
}
