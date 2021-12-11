package simulation;

public enum medicalcondition {
	cancer(3), 
	flu(3),
	respitoryissue(2),
	baddiet(2),
	deficincy(3);
	int complication;
	medicalcondition(int cl){
		complication=cl;
	}

}
