package simulationPhase2;

public enum injuries {
	Brokenbonel(7, 0, 3, padRight("Broken Bone", 20)), 
	burn(10, 2, 1, padRight("Burn", 20)),
	toxicexposure(9, 3, 2,padRight("Toxic exposure", 20)),
	electricshock(10, 1, 5,padRight("Electric Shock", 20)),
	Spinalcordinjurie(12, 0, 6,padRight("Spinal Cord Injury", 20)),
	Skindisorders(9, 2, 1,padRight("Skin injury", 20)),
	Poisonings(9, 3, 2,padRight("Poisioning", 20)),
	Hearingloss(9, 0, 3,padRight("Ear injury", 20)),
	Visionloss(10, 0, 4, padRight("Eye injury", 20)),
	Wound(10, 3, 1,padRight("Wound", 20)),
	Amputation(12, 1, 6, padRight("Amputation", 20)),
	None(0,0,0, padRight("Nothing", 20));
	int progressionfactor;
	int catogery;
	int level;
	String name;
	
	injuries(int pf, int cat, int lvl, String nameof){
		progressionfactor=pf;
		catogery=cat;
		level=lvl;
		name=nameof;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String padRight(String s, int n) {
	    return String.format("%-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	   return String.format("%" + n + "s", s);  
	}
}

