package simulationPhase2;

public enum medicalcondition {
	heartissues(4, 1),
	skinissues(2,2),
	obesity(3,0),
	respitoryissue(3,3),
	baddiet(1,0),
	deficincy(1,0),
	none(0,0);
	int complication;
	int category;
	medicalcondition(int cl, int cat){
		complication=cl;
		category=cat;
	}
	public int getComplication() {
		return complication;
	}
	public void setComplication(int complication) {
		this.complication = complication;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	

}
