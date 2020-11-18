public class Person {
    public Football getFootball(String toyName) {
        if (toyName.equals("Football"))
            return new Football();
        else
            return null;
    }

    public Playable getPlay(String toyName) {
        if (toyName.equals("Piano"))
            return new Piano();
        else if (toyName.equals("Football"))
            return new Football();
        else 
            return new Chess();
    }
}