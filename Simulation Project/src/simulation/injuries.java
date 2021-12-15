package simulation;

public enum injuries {
	Brokenbonel(,20, 1), 
	toxicexposure(10, 3),
	Spinalcordinjurie(20, 4),
	Skindisorders(30,3),
	Poisonings(10,2),
	Hearingloss(20,4),
	Visionloss(10,4),
	Chocking(10,4),
	Amputation(10,4);
	
	int healingtime;
	int permenanttime;
	int lvl;
	injuries(int ht, int pt, int level){
		ht=healingtime;
		pt=permenanttime;
		lvl=level;
	}
}
