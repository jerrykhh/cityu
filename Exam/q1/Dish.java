public class Dish {
	
	private Cook cook;
	private Recipe recipe;
        private int score;
	
	public Dish(Cook c, Recipe r) {
        cook = c;
        recipe = r;
	}

        public void obtainScore(int score){
            cook.addScore(score);
            this.score = score;
        }
        
        public Recipe getRecipe(){
            return recipe;
        }
        
        public Cook getCook(){
            return cook;
        }
        
        public int getScore(){
            return score;
        }
}
