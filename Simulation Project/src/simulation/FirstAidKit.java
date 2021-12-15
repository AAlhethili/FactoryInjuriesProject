package simulation;

public class FirstAidKit extends medicaltreatment implements medicaltreatmant{
    boolean cantreat;
    int treatinjury=0;
    public FirstAidKit(injuries current){
        CanTreat();
       if(cantreat) {
           Treatinjury(current);
       }
       }
    private void CanTreat(){
        if(WorkersthatknowFA != 0) {
            cantreat = true;
        }
        cantreat = false;
    }
    private void Treatinjury(injuries current) {
        switch (current.lvl) {
            case 1:
                treatinjury=30;
            case 2:
                treatinjury= 20;
            case 3:
                treatinjury= 10;
            case 4:
                treatinjury= 5;
            default:
                treatinjury= 0;
        }

    }

}
