# Midterm Prepare Question

## Q1
####Case 1: The file profits.txt has 12 numbers:
```
113890000
101200000
98040000
97670000
94740000
102990000
114920000
113180000
82950000
119060000
80620000
93560000
```

Program Output:
```
Total: 1212820000
From main(): Reporting is OK. 
```

#### Case 2: The file profits.txt does not exist.
Program Output:
```
Cannot open input file!
```

#### Case 3: The file profits.txt has less than 12 numbers.
Program Output:
```
Insufficient data!
Total: [cannot be calculated]
```

#### Case 4: The file profits.txt has non-integer like "oneMillion"
Program Output:
```
Wrong data!
Total: [cannot be calculated]
```


### Given Program for answer the question

```
public static void main(String[] args) {
        try {
            String report = analyze();
            System.out.println(report);




}

---------------------------------
private static String analyze() throws FileNotFoundException {
        int total = 0;
       
        Scanner f = null;
      
}
```


## Q2
```
public class Q2 {

    public static void main(String[] args) {

        Student a = new Student("Ada", 'F', "CS"); //name, gender, programme
        Student b = new Student("Billy", 'M', "Math");
        Student c = new Student("Candy", 'F', "CS");
        Student d = new Student("Daisy", 'F', "CS");
        Student e = new Student("Emily", 'F', "Math");

        Rule rG = new RuleSameGender();
        Rule rBk = new RuleSimilarBackground();

        a.listTargets(); //Output [Ada: Billy Candy Daisy Emily] 
        b.listTargets(); //Output [Billy: Ada Candy Daisy Emily]

        a.addRule(rG);
        a.listTargets(); //Output [Ada: Candy Daisy Emily]
        a.addRule(rBk);
        a.listTargets(); //Output [Ada: Candy Daisy] 

        Student f = new Student("Fanny", 'F', "CS");
        a.listTargets(); //Output [Ada: Candy Daisy Fanny]
        f.listTargets(); //Output [Fanny: Ada Billy Candy Daisy Emily]	
    }
}
```

## Q3

1. Complete the following program by implementing required methods/ classes / interfaces. The expected outputs of the program are given as underlined contents in the inline comments in **main()**
2. Using any code in part (1), explain the terms Up-casting and Dynamic Binding.

```
public class Q3 {
    public static void main(String[] args){
        Decider d1 = new WeatherDecider();
        Decider d2 = new BudgetDecider();
        goHiking(d1);
        goHiking(d2);
        goShopping(d1);
        goShopping(d2);
    }
    
    // Add the methods: goHiking and goShopping (5 marks)
    
    
    
}

// add the code for Decider and WeatherDecider (8 marks)
// (The code for BudgetDecider is NOT needed)
```

## Q4
1. Complete the given program by implementing all required classes / interfaces. The expected outputs of the program are given as underlined contents in the inline comments in **main()**. Write all remaining classes/interfaces.

Given:
```
public class Q4 {
    public static void main(String[] args){
        Cook c1 = new SteamCook();
        Cook c2 = new CurryCook();
        c1.cook(new Fish());
        c2.cook(new Chicken());
        c1.cook(new Chicken());
        c2.cook(new Fish());
    }
}
interface Cook {
    void cook(Food f);
}
```

2. Mary thinks that the Cook interface could be turned to a more useful class as on the right: Briefly **discuss** how Mary's thought satisfies or violates the OCP (Open-Closed Principle)
   
```
public class Cook {
    private int cooking_type; /* cooking type: 1- steam, 2- curry */
    public void cook(..){
        /* depends on the cooking type, output the result */
    }
}
```

## Q5
Complete the program below by writing the classes "Do", "Re", "Mi", "Fa".
[Question borrwoed form Dr Sam Ng (CS2332/2010-11 Sem B)]
*Notes:* you cannot modify the given code. You may write additional hepler classes.

```
public class Sentence {
    public void print(){
        System.out.println("!!!");
    }
}

---------------------------
public class Q5 {
    public static void main(String[] args){
        Sentence[] s = new Sentence[3];
        s[0] = new Do(new Mi(new Sentence()));
        s[1] = new Do(new Mi(new Do(new Mi( new Sentence()))));
        s[2] = new Re(new Mi(new Fa(new Fa(new Mi(new Re(new Fa(new Sentence())))))));
        
        for(int i = 0; i < 3; i++){
            s[i].print();
        }
    }
}

```
Program Output:
```
do re mi !!!
do mi do mi !!!
re mi fa fa mi re fa !!!
```

## Q6
Complete the program below by writing all necessary classes.
[Question borrwoed form Dr Sam Ng (CS2332/2010-11 Sem B)]

*Notes:*
1. You cannot modify the given code.
2. You may write additional helper classes wherever appropiate.
3. You cannot check the types of objects(eg using *instanceof*); otherwise you will get 0 marks.

```
public class Animal {
    private AgeGroup group;
    private Type type;
    
    public Animal(Type type, AgeGroup group){
        this.type = type;
        this.group = group;
    }
    
    public void call(){
        group.call(type);
    }
}

--------------------------
public class Q6 {
    public static void main(String[] args){
        Animal[] pets = new Animal[6];
        pets[0] = new Animal(new Dog(), new Baby());
        pets[1] = new Animal(new Dog(), new Adult());
        pets[2] = new Animal(new Cat(), new Baby());
        pets[3] = new Animal(new Cat(), new Adult());
        pets[4] = new Animal(new Rabbit(), new Baby());
        pets[5] = new Animal(new Rabbit(), new Adult());
        
        for(int i = 0; i < 6; i++){
            pets[i].call();
        }
    }
}
```

Program Output:
```
Puppy
Dog
Kitten
Cat
Bunny
Rabbit
```