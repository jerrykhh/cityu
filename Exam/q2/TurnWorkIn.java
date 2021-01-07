import java.util.ArrayList;
public class TurnWorkIn {
    private static TurnWorkIn instance = new TurnWorkIn();
    private static ArrayList<Submission> ans;
    private TurnWorkIn(){
        ans = new ArrayList<Submission>();
    }
    
    public static TurnWorkIn getInstance(){
        return instance;
    }
    
    public static void receiveSubmission(Submission sumb){
        String plagiarStudent = "";
        
        for(Submission submission: ans){
            if(sumb.getWork().equals(submission.getWork())){
                plagiarStudent += sumb.getStudent().getName() + "'s work (submitted by " + submission.getStudent().getName() + "), " ;
                 plagiarStudent += submission.getStudent().getName()+ "'s work (submitted by " + submission.getStudent().getName() + ")";
            }
        }
        ans.add(sumb);
        
        if(plagiarStudent != ""){
            System.out.println("Plagiarism: " + plagiarStudent);
        }else{
            System.out.println("OK");
        }
        
    }
}
