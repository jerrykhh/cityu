public class Mouse extends Animal {
    public Mouse(String name){
        super(name);
    }

    public String getMyName(){
        return super.getName();
    }

    public int getRunSpeed(){
        return 2;
    }


}
