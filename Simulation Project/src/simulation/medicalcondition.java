package simulation;

public enum medicalcondition {
	cancer(120), 
	obesity(90),
	respitoryissue(120),
	baddiet(70),
	deficincy(90),
	none(0);
	int complication;
	medicalcondition(int cl){
		complication=cl;
	}
	public int getComplication() {
		return complication;
	}
	public void setComplication(int complication) {
		this.complication = complication;
	}
	

}
