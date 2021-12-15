package simulation;

public enum injuries {
	Brokenbonel(30, 50, 1), 
	toxicexposure(30, 20, 1),
	Spinalcordinjurie(60, 30, 3),
	Skindisorders(20, 30, 1),
	Poisonings(20, 40, 2),
	Hearingloss(40, 40, 2),
	Visionloss(50, 40, 3),
	Chocking(10, 10, 1),
	Amputation(60, 10, 3);
	
	int healingtime;
	int permenanttime;
	int lvl;
	
	injuries(int ht, int pt, int level){
		ht=healingtime;
		pt=permenanttime;
		lvl=level;
	}
}
