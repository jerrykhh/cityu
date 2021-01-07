import java.util.ArrayList;
public class Cook {
    private String name;
    private ArrayList<Integer> scores;
    
    public Cook(String name){
        this.name = name;
        scores = new ArrayList<Integer>();
    }
    
    public String getName(){
        return name;
    }

    public Dish cookDish(Recipe recipe) {
        Dish dish = new Dish(this, recipe);
        recipe.addDish(dish);
        return dish;
    }
    
    public void addScore(int score){
        scores.add(score);
    }
    
    public void report(){
        System.out.print("I got " + scores.size() +" scores: ");
        for(Integer score: scores){
            System.out.print(" " + score);
        }
        System.out.println("");
    }
    
    public ArrayList<Integer> getScores(){
        return scores;
    }
}
