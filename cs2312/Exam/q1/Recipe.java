import java.util.ArrayList;
public class Recipe {
    private String name;
    private ArrayList<Dish> dishs;
    

    public Recipe(String name) {
        this.name = name;
        dishs = new ArrayList<Dish>();
    }
    
    public void addDish(Dish dish){
        dishs.add(dish);
    }
    
    public void report(){
        if(dishs.isEmpty()){
            System.out.println(name + ": (No data)");
            return;
        }
        System.out.print(name + ": The best cook is ");
        int maxScore = 0;
        Cook bestCook = null;
        for(Dish dish: dishs){
            if(dish.getRecipe() == this){
                if(maxScore < dish.getScore()){
                    maxScore = dish.getScore();
                    bestCook = dish.getCook();
                }
            }            
        }
        System.out.println(bestCook.getName());
    }
}
