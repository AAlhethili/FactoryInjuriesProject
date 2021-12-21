package simulationPhase2;
import java.util.ArrayList;
import java.security.SecureRandom;


public class factory {
	
SecureRandom generator = new SecureRandom();
ToolsofStrings tof = new ToolsofStrings();
private int ID;
private int noOfWorkers;
private int noOfFirstResponders;
private int density;
int counterInjuries;//any variable that starts with counter is used to calculate average data at the end of simulation
int counterNoOfWorkerWhenInjuryHappened;
int counterInjuryLevel;
int counterPeopleAroundInjury;
int counterInitialProgressionOfInjury;
int counterTreatmentByFirstAidKit;
int counterTreatmentByCapableInfermaryRoom;

private boolean imopsesRegularCheckUpRate;
private boolean hasSmartFirstAidkit;
private double avgCheckUpRate;
private double avgAge;
private hospital factoryToHosptial;
private medicallytrainedworker MedicalWorker = new medicallytrainedworker();
private int avgTimeToHospital;
private int MaxProgressedInjury;
private int MinProgressedInjury;
private int TotalnoOfPermanantInjuried;
public ArrayList<worker> workerList = new ArrayList<worker>();
public ArrayList<medicallytrainedworker> FirstResponders = new ArrayList<medicallytrainedworker>();
public ArrayList<worker> Injuriedlist = new ArrayList<worker>();
public ArrayList<medicalintervention> listOfMedicalIntervention = new ArrayList<medicalintervention>();
private infirmaryroom factoryInfermaryRoom;
private medicalintervention factoryKit;


public factory(int iD) {

	ID = iD;
	factoryToHosptial=new hospital();
	avgAge = generator.nextInt(20)+25;//lower average age
	avgCheckUpRate= generator.nextInt(1)+3;//higer check up at this phase
	imopsesRegularCheckUpRate = true;//all employs have set checkup based on the company rules
	MaxProgressedInjury=0;
	MinProgressedInjury=999999;
	createworkers(generator.nextInt(401)+100);
	noOfWorkers=workerList.size();
	createFirstAidKits();
	createRooms();
	density=generator.nextInt(4)+1;//lower at this phase
	avgTimeToHospital = (generator.nextInt(2)+1)*10;
	avgAge=Math.round(calculateAvgAge());
	counterPeopleAroundInjury=0;
	counterInitialProgressionOfInjury=0;
	counterTreatmentByFirstAidKit=0;
	counterTreatmentByCapableInfermaryRoom=0;
	counterTreatmentByMedicalWorker=0;
	counterTreatmentByHospital=0;
	
	avgCheckUpRate=Math.round(calculateAvgcheckup());
	for(int i=0; i<=noOfWorkers-1; i++) {
		if(workerList.get(i) instanceof medicallytrainedworker) {
			FirstResponders.add((medicallytrainedworker) workerList.get(i));
		}
	}
	noOfFirstResponders=FirstResponders.size();
	
}
public boolean checksmartkit() {
	for(int i=0; i<listOfMedicalIntervention.size();i++) {
		if (listOfMedicalIntervention.get(i) instanceof smartFirstAidkit) {
			return true;
		}
	}
	return false;
}
public boolean checkkit() {
	for(int i=0; i<listOfMedicalIntervention.size();i++) {
		if (listOfMedicalIntervention.get(i) instanceof FirstAidKit) {
			return true;
		}
	}
	return false;
}
public void createworkers(int noworkers) {
	
	for(int i=1; i<=noworkers; i++) {
		if(generator.nextInt(6)==0) {
		worker a = new medicallytrainedworker(i,avgAge,avgCheckUpRate);
		workerList.add(a);
		}
		else {
		worker a = new worker(i,avgAge,avgCheckUpRate);
		workerList.add(a);
		}
	}
	if(imopsesRegularCheckUpRate==false) {
		for(int i=0; i<=noworkers-1; i++) {
			
			double above0 = generator.nextGaussian()*0.7;
			while(above0<0||above0>4)
			{
			above0 = generator.nextGaussian()*0.7;	
			}
			workerList.get(i).setRegularcheckuprate((int) Math.round(above0));
		}
	}
		
}
public void createRooms() {
	
		infirmaryroom room = new infirmaryroom();
		factoryInfermaryRoom=room;
		listOfMedicalIntervention.add(room);
}
public void createFirstAidKits() {//all implement smart aid kit
		if(true) {
			medicalintervention smartkit = new smartFirstAidkit();
			factoryKit=smartkit;
			listOfMedicalIntervention.add(smartkit);
			 hasSmartFirstAidkit=true;
		}

	
		
}

public double calculateAvgAge() {
	double sum=0;
	double avgage;
	for(int i=0; i<=noOfWorkers-1; i++) {
		sum+=workerList.get(i).getAge();
	}
	avgage=sum/(double)noOfWorkers;
	return avgage;
}
public double calculateAvgcheckup() {
	double sum=0;
	double avgcheckuprate;
	for(int i=0; i<=noOfWorkers-1; i++) {
		sum+=workerList.get(i).getRegularCheckUprate();
	}
	avgcheckuprate=sum/(double)noOfWorkers;
	return avgcheckuprate;
}
public void calculateNumofPermaInjuries() {

	for(int i=0; i<=noOfWorkers-1; i++) {
		if(workerList.get(i).isPermenatlyInjured())
			TotalnoOfPermanantInjuried+=1;
		;
	}
}
public int CalculatWhocanuseMK() {
	int total=0;
	for(int i=0; i<=noOfWorkers-1; i++) {
		if(workerList.get(i).isKnowsHowtoUseMedikit())
			total++;
	}
	return total;
}
public double calculateprecent() {
	double precent;
	precent=((double)getTotalnoOfPermanantInjuried()/(double)getNoOfWorkers())*100;
	return precent;
}
public worker chooseRandomworker(){
	worker randomSelect = workerList.get(generator.nextInt(workerList.size()));
		randomSelect = workerList.get(generator.nextInt(workerList.size()));	
		if(randomSelect.isPermenatlyInjured()||randomSelect.isInjuired()) {
			randomSelect = workerList.get(generator.nextInt(workerList.size()));	
		}
	return randomSelect;
	
}
public void LoopTreating(int simTime,int simDay) {
	for(int i = 0; i<getInjuriedlist().size(); i++) {
	if(!getInjuriedlist().get(i).isInjuired()
			||getInjuriedlist().get(i).isPermenatlyInjured()){
		getInjuriedlist().remove(getInjuriedlist().get(i));}
	if(!Injuriedlist.isEmpty()) {
	for(int j = 0; j<getInjuriedlist().size(); j++) {
		treatWorker(getInjuriedlist().get(j),simDay, simTime);
		}
	}
	}
}
public void treatWorker(worker injuried,int simDay, int simTime) {
	if(!injuried.isTreated()) {
		for(int i=0; i<listOfMedicalIntervention.size();i++) {
			if (!(listOfMedicalIntervention.get(i) instanceof smartFirstAidkit)
					&&!(listOfMedicalIntervention.get(i) instanceof infirmaryroom)) {
				listOfMedicalIntervention.get(i).treatmentadminstraition(injuried,simDay, simTime, this);
			}
			if (listOfMedicalIntervention.get(i) instanceof smartFirstAidkit) {
				listOfMedicalIntervention.get(i).treatmentadminstraition(injuried,simDay, simTime, this);
			}
		}
	
		MedicalWorker.treatmentadminstraition(injuried,simDay, simTime, this);
		
			for(int i=0; i<listOfMedicalIntervention.size();i++) {
				if (listOfMedicalIntervention.get(i) instanceof infirmaryroom) {
					listOfMedicalIntervention.get(i).treatmentadminstraition(injuried,simDay, simTime, this);
				}
			}
			
		factoryToHosptial.treatmentadminstraition(injuried,simDay, simTime, this);		
//		printInjuried(simTime);
				

	}

}

public void showWorkerList() {
	System.out.println("ID		Age		Checkup");
	for(int i=0; i<=noOfWorkers-1; i++) {
		System.out.println(workerList.get(i).id+"		"+workerList.get(i).getAge()+"		"+workerList.get(i).getRegularCheckUprate());
	}
}
public void printInjuried(int simAlltime, int simDay) {
	for(int in=0; in<getInjuriedlist().size();in++) {
		if(simAlltime%simDay!=0) {

		if(!getInjuriedlist().get(in).isTreated()) {
			System.out.printf("%-10d|%-9d|%-3d|%-16s|%18s|%-14s|%-16d|%-11d|%-2d %-17s|%-3d \n", ID, 
					getInjuriedlist().get(in).getId(),
					(int)getInjuriedlist().get(in).getAge(),
					getInjuriedlist().get(in).condsNames(),
					getInjuriedlist().get(in).getCurrent().getName(),
					getInjuriedlist().get(in).getTimeOfInjury(),
					getInjuriedlist().get(in).getProgressionRate(),
					getInjuriedlist().get(in).getProgressionofInjury(),
					getInjuriedlist().get(in).getHospitalarraivalTimeCountdown(),"minutes",
					getInjuriedlist().get(in).getCurrent().progressionfactor );
		}
		}
		
	}
}

public void injuryandtreatment(worker injuried,int simDay, int simAlltime, int simMaxtime, String timeofinjury) {
	if(simAlltime!=simMaxtime) {
	if(generator.nextInt((10-getDensity())*20)==0) {
		if(generator.nextInt((10-getDensity())*3)==0) {
			if(getInjuriedlist().isEmpty()) {
				for(int k = 0; k<generator.nextInt((getDensity())+1); k++) {
				injuried=chooseRandomworker();
					injuried.addinjury(this, timeofinjury);
					Injuriedlist.add(injuried);
					}
			}
			
		}
		else {
		if(getInjuriedlist().isEmpty()) {
				injuried=chooseRandomworker();
					injuried.addinjury(this, timeofinjury);
					Injuriedlist.add(injuried);
			}
		}
	}
}
		if(!Injuriedlist.isEmpty()) {
			LoopTreating(simAlltime,simDay);
		}
}
public double calculateNumofPerma() {
double number=0;
		for(int i=0; i<=noOfWorkers-1; i++) {
			if(workerList.get(i).isPermenatlyInjured())
				number+=1;
			
		}
		return number;
}
public String calcAveragePerInjury(int numberOfTimes) {
	double average=(double)numberOfTimes/(double)counterInjuries;
	return String.format("%.1f", average);
	
}
public String calcPrecentagePerInjury(int numberOfTimes) {
	double average=(double)Double.parseDouble(calcAveragePerInjury(numberOfTimes));
	double f =average*100;
	return String.format("%.1f%%", f);
	
}

public void showFactoryInfo(int Days, int hours) {
	calculateNumofPermaInjuries();
	System.out.printf("%-3d|%-2d|%-4d|%-4d|%-3s|%-3s|%-1d|%-1d|%-4s|%-4s|%-4s|%-3d|%-4s|%-3d|%4s|%-4s|\n",
			getID(),
			(int)Math.round(avgAge),
			counterInjuries,
			TotalnoOfPermanantInjuried,
			tof.yesorno(hasSmartFirstAidkit),
			tof.yesorno(imopsesRegularCheckUpRate),
			(int)Math.round(avgCheckUpRate),
			factoryInfermaryRoom.getRangeofcaplbility(),
			calcAveragePerInjury(counterTreatmentByCapableInfermaryRoom),
			calcAveragePerInjury(counterInjuryLevel),
			calcAveragePerInjury(counterNoOfWorkerWhenInjuryHappened),
			CalculatWhocanuseMK(),
			calcAveragePerInjury(counterTreatmentByFirstAidKit),
			noOfFirstResponders,
			calcAveragePerInjury(counterTreatmentByMedicalWorker),
		    calcAveragePerInjury(counterTreatmentByHospital));
}
public ArrayList<String> stringOfFactroyInfo() {
	ArrayList<String> columnsArraylist = new ArrayList<String>();
	calculateNumofPermaInjuries();
	columnsArraylist.add(String.valueOf(getID()));
	columnsArraylist.add(String.valueOf((int)Math.round(avgAge)));
	columnsArraylist.add(String.valueOf(counterInjuries));
	columnsArraylist.add(calcAveragePerInjury(counterInjuryLevel));
	columnsArraylist.add(String.valueOf(TotalnoOfPermanantInjuried));
	columnsArraylist.add(tof.yesorno(hasSmartFirstAidkit));
	columnsArraylist.add(tof.yesorno(imopsesRegularCheckUpRate));
	columnsArraylist.add(String.valueOf((int)Math.round(avgCheckUpRate)));
	columnsArraylist.add(String.valueOf(factoryInfermaryRoom.getRangeofcaplbility()));
	columnsArraylist.add(String.valueOf(counterTreatmentByCapableInfermaryRoom));
	columnsArraylist.add(calcAveragePerInjury(counterNoOfWorkerWhenInjuryHappened));
	columnsArraylist.add(String.valueOf(CalculatWhocanuseMK()));
	columnsArraylist.add(String.valueOf(counterTreatmentByFirstAidKit));
	columnsArraylist.add(String.valueOf(noOfFirstResponders));
	columnsArraylist.add(String.valueOf(counterTreatmentByMedicalWorker));
	columnsArraylist.add(String.valueOf(MaxProgressedInjury));
	columnsArraylist.add(String.valueOf(MinProgressedInjury));
	return columnsArraylist;
}
public int getNoOfWorkers() {
	return noOfWorkers;
}
public void setNoOfWorkers(int noworkers) {
	this.noOfWorkers = noworkers;
}

public int getAvgTimeToHospital() {
	return avgTimeToHospital;
}
public void setAvgTimeToHospital(int timetohospital) {
	this.avgTimeToHospital = timetohospital;
}
public int getTotalnoOfPermanantInjuried() {
	return TotalnoOfPermanantInjuried;
}
public void setTotalnoOfPermanantInjuried(int totalnoOfPermanantInjuried) {
	TotalnoOfPermanantInjuried = totalnoOfPermanantInjuried;
}


public boolean isImopsesRegularCheckUpRate() {
	return imopsesRegularCheckUpRate;
}
public void setImopsesRegularCheckUpRate(boolean regularcheckup) {
	this.imopsesRegularCheckUpRate = regularcheckup;
}
public ArrayList<worker> getWorkerList() {
	return workerList;
}
public void setWorkerList(ArrayList<worker> wlist) {
	this.workerList = wlist;
}
public ArrayList<worker> getInjuriedlist() {
	return Injuriedlist;
}
public void setInjuriedlist(ArrayList<worker> injuriedlist) {
	Injuriedlist = injuriedlist;
}
public ArrayList<medicalintervention> getListOfMedicalIntervention() {
	return listOfMedicalIntervention;
}
public void setListOfMedicalIntervention(ArrayList<medicalintervention> medicalIntervention) {
	listOfMedicalIntervention = medicalIntervention;
}

public boolean isHasSmartFirstAidkit() {
	return hasSmartFirstAidkit;
}
public void setHasSmartFirstAidkit(boolean hasSmartFirstAidkit) {
	this.hasSmartFirstAidkit = hasSmartFirstAidkit;
}
public int getDensity() {
	return density;
}
public void setDensity(int density) {
	this.density = density;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getMaxProgressedInjury() {
	return MaxProgressedInjury;
}
public void setMaxProgressedInjury(int maxProgressedInjury) {
	MaxProgressedInjury = maxProgressedInjury;
}
public int getMinProgressedInjury() {
	return MinProgressedInjury;
}
public void setMinProgressedInjury(int minProgressedInjury) {
	MinProgressedInjury = minProgressedInjury;
}
public int getNoOfFirstResponders() {
	return noOfFirstResponders;
}
public void setNoOfFirstResponders(int noOfFirstResponders) {
	this.noOfFirstResponders = noOfFirstResponders;
}
public int getCounterInjuries() {
	return counterInjuries;
}
public void setCounterInjuries(int counterInjuries) {
	this.counterInjuries = counterInjuries;
}
public int getCounterNoOfWorkerWhenInjuryHappened() {
	return counterNoOfWorkerWhenInjuryHappened;
}
public void setCounterNoOfWorkerWhenInjuryHappened(int counterNoOfWorkerWhenInjuryHappened) {
	this.counterNoOfWorkerWhenInjuryHappened = counterNoOfWorkerWhenInjuryHappened;
}
public int getCounterInjuryLevel() {
	return counterInjuryLevel;
}
public void setCounterInjuryLevel(int counterInjuryLevel) {
	this.counterInjuryLevel = counterInjuryLevel;
}
public int getCounterPeopleAroundInjury() {
	return counterPeopleAroundInjury;
}
public void setCounterPeopleAroundInjury(int counterPeopleAroundInjury) {
	this.counterPeopleAroundInjury = counterPeopleAroundInjury;
}
public int getCounterInitialProgressionOfInjury() {
	return counterInitialProgressionOfInjury;
}
public void setCounterInitialProgressionOfInjury(int counterInitialProgressionOfInjury) {
	this.counterInitialProgressionOfInjury = counterInitialProgressionOfInjury;
}
public int getCounterTreatmentByFirstAidKit() {
	return counterTreatmentByFirstAidKit;
}
public void setCounterTreatmentByFirstAidKit(int counterTreatmentByFirstAidKit) {
	this.counterTreatmentByFirstAidKit = counterTreatmentByFirstAidKit;
}
public int getCounterTreatmentByCapableInfermaryRoom() {
	return counterTreatmentByCapableInfermaryRoom;
}
public void setCounterTreatmentByCapableInfermaryRoom(int counterTreatmentByCapableInfermaryRoom) {
	this.counterTreatmentByCapableInfermaryRoom = counterTreatmentByCapableInfermaryRoom;
}
public int getCounterTreatmentByMedicalWorker() {
	return counterTreatmentByMedicalWorker;
}
public void setCounterTreatmentByMedicalWorker(int counterTreatmentByMedicalWorker) {
	this.counterTreatmentByMedicalWorker = counterTreatmentByMedicalWorker;
}
public int getCounterTreatmentByHospital() {
	return counterTreatmentByHospital;
}
public void setCounterTreatmentByHospital(int counterTreatmentByHospital) {
	this.counterTreatmentByHospital = counterTreatmentByHospital;
}
public double getAvgCheckUpRate() {
	return avgCheckUpRate;
}
public void setAvgCheckUpRate(double avgCheckUpRate) {
	this.avgCheckUpRate = avgCheckUpRate;
}
public double getAvgAge() {
	return avgAge;
}
public void setAvgAge(double avgAge) {
	this.avgAge = avgAge;
}
public hospital getFactoryToHosptial() {
	return factoryToHosptial;
}
public void setFactoryToHosptial(hospital factoryToHosptial) {
	this.factoryToHosptial = factoryToHosptial;
}
public medicallytrainedworker getMedicalWorker() {
	return MedicalWorker;
}
public void setMedicalWorker(medicallytrainedworker medicalWorker) {
	MedicalWorker = medicalWorker;
}
public ArrayList<medicallytrainedworker> getFirstResponders() {
	return FirstResponders;
}
public void setFirstResponders(ArrayList<medicallytrainedworker> firstResponders) {
	FirstResponders = firstResponders;
}
public infirmaryroom getFactoryInfermaryRoom() {
	return factoryInfermaryRoom;
}
public void setFactoryInfermaryRoom(infirmaryroom factoryInfermaryRoom) {
	this.factoryInfermaryRoom = factoryInfermaryRoom;
}
public medicalintervention getFactoryKit() {
	return factoryKit;
}
public void setFactoryKit(medicalintervention factoryKit) {
	this.factoryKit = factoryKit;
}
int counterTreatmentByMedicalWorker;
int counterTreatmentByHospital;

}
