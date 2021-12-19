package simulation;

public enum injuries {
	Brokenbonel(10, 0, 1, padRight("Broken Bone", 20)), 
	burn(20, 2, 1, padRight("Burn", 20)),
	toxicexposure(15, 3, 1,padRight("Toxic exposure", 20)),
	electricshock(15, 1, 5,padRight("Electric Shock", 20)),
	Spinalcordinjurie(20, 0, 6,padRight("Spinal Cord Injury", 20)),
	Skindisorders(15, 2, 1,padRight("Skin injury", 20)),
	Poisonings(15, 3, 2,padRight("Poisioning", 20)),
	Hearingloss(10, 0, 2,padRight("Ear injury", 20)),
	Visionloss(15, 0, 4, padRight("Eye injury", 20)),
	Chocking(20, 3, 1,padRight("Chocking", 20)),
	Amputation(15, 1, 6, padRight("Amputation", 20)),
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

