package simulation;

public class medicallytrainedworker extends worker implements medicaltreatmant {
	public boolean medicaltrining;
	
	public medicallytrainedworker(int id, double avg) {
		super(id,avg);
		medicaltrining=true;
	}

}
