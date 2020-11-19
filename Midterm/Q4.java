
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q4 {

    public static void main(String[] args) {
 
        Member h = SocialNetwork.createChildMember("Harry");
        Member j = SocialNetwork.createTeanagerMember("Jay");
        Member g = SocialNetwork.createTeanagerMember("Grace");
        Member f = SocialNetwork.createElderMember("Fred");

        h.makeFriend(j);
        h.makeFriend(g);
        j.makeFriend(g);
        j.makeFriend(f);

        h.displayScoreAndFriends(); //output [Harry (20) is a friend of Jay Grace]
        j.displayScoreAndFriends(); //output [Jay (21) is a friend of Harry Grace Fred]
        g.displayScoreAndFriends(); //output [Grace (11) is a friend of Harry Jay]
        f.displayScoreAndFriends(); //output [Fred (10) is a friend of Jay]
    }

}

class SocialNetwork {

    public static Member createChildMember(String name) {
        return new Member(name, new Child());
    }

    public static Member createTeanagerMember(String name) {
        return new Member(name, new Teenager());
    }

    public static Member createElderMember(String name) {
        return new Member(name, new Elderly());
    }

}


class Member {

    private String name;
    private AgeGroup ageGroup;
    private ArrayList<Member> friends;

    public Member(String name, AgeGroup ageGroup) {
        this.name = name;
        this.ageGroup = ageGroup;
        friends = new ArrayList();
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void makeFriend(Member member) {
        friends.add(member);
        member.connectFriend(this);
    }

    public void connectFriend(Member member) {
        friends.add(member);
    }

    public ArrayList<Member> getFriends() {
        return friends;
    }

    public String getName() {
        return name;
    }

    public String getFriendString() {

        String result = "";
        for (Member friend : friends) {
            result += " " + friend.getName();
        }
        return result;
    }

    public void displayScoreAndFriends() {

        int score = 0;
        for (Member firend : friends) {

          score += (firend.getAgeGroup().getClass() 
                  != this.getAgeGroup().getClass())? 10: 1;

        }

        System.out.println(name + " (" + score + ") is a friend of" + getFriendString());

    }

}

interface AgeGroup {
}

class Child implements AgeGroup {
}

class Teenager implements AgeGroup {
}

class Adult implements AgeGroup {
}

class Elderly implements AgeGroup {
}
