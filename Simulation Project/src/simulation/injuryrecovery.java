package simulation;

public class injuryrecovery {
    int InjurySevereness;
    int InjuryAdvanceTime;
    boolean NeedofHospitalTreatment;
    boolean FirstAidAdministartion;
    boolean InfirmaryTreatment;
    boolean FirstResponderTreatment;

    public injuryrecovery(injuries current,
                          FirstAidKit FA,
                          firstresponder FR,
                          infirmaryroom IR ){
        InjurySevereness= current.lvl;
        InjuryAdvanceTime=+ FA.treatinjury +
                IR.treating +
                FR.treating;
        FirstAidAdministartion= FA.cantreat;
        InfirmaryTreatment= IR.cantreat;
        FirstResponderTreatment= FR.cantreat;


    }
}
