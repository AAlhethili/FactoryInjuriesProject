package simulation;

public class medicallytrainedworker extends worker implements medicaltreatmant {
	public boolean medicaltrining;
	
	public medicallytrainedworker(int id, double avg, double avgcheck) {
		super(id,avg, avgcheck);
		medicaltrining=true;
	}

}
