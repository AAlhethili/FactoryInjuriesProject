package simulation;

public enum medicalcondition {
	cancer(30), 
	obesity(40),
	respitoryissue(20),
	baddiet(10),
	deficincy(20),
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
