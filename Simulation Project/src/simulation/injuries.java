package simulation;

public enum injuries {
	Brokenbonel(10, 0, 1), 
	burn(20, 2, 1),
	toxicexposure(15, 3, 1),
	electricshock(15, 1, 5),
	Spinalcordinjurie(20, 0, 6),
	Skindisorders(15, 2, 1),
	Poisonings(15, 3, 2),
	Hearingloss(10, 0, 2),
	Visionloss(15, 0, 3),
	Chocking(20, 3, 1),
	Amputation(20, 1, 6),
	None(0,0,0);
	int progressionfactor;
	int catogery;
	int level;
	
	injuries(int pf, int cat, int lvl){
		progressionfactor=pf;
		catogery=cat;
		level=lvl;
	}

}
