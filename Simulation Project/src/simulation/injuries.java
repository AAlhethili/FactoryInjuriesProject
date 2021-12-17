package simulation;

public enum injuries {
	Brokenbonel(120, 30, 1), 
	toxicexposure(60, 30, 1),
	Spinalcordinjurie(150, 30, 3),
	Skindisorders(50, 50, 1),
	Poisonings(60, 30, 2),
	Hearingloss(40, 40, 2),
	Visionloss(50, 40, 3),
	Chocking(10, 10, 1),
	Amputation(240, 10, 3),
	None(0,0,0);
	int healingtime;
	int permenanttime;
	int lvl;
	
	injuries(int ht, int pt, int level){
		ht=healingtime;
		pt=permenanttime;
		lvl=level;
	}

	public int getHealingtime() {
		return healingtime;
	}

	public void setHealingtime(int healingtime) {
		this.healingtime = healingtime;
	}

	public int getPermenanttime() {
		return permenanttime;
	}

	public void setPermenanttime(int permenanttime) {
		this.permenanttime = permenanttime;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	
}
