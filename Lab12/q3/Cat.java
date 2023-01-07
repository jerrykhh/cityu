public class Cat extends Animal{
  

    public Cat(String name){
        super(name);
    }

    public String getMyName(){
        return super.getName();
    }

    public int getRunSpeed(){
        return 5;
    }

    public void chase(Runnable target){
        if(target.getRunSpeed() < getRunSpeed()){
            System.out.println(getMyName() + " Catches " + target.getMyName());
        }else{
            System.out.println(target.getMyName() + " Cannot Catch " + getMyName());
        }
    }

}
