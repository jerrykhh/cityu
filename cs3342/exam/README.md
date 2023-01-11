# CS3342 Exam
## Question 1. Software Engineering in General (10 Marks):

(a) **In your own words**, please explain what is **“Software Design Pattern”**, and why we need to use it? (4 Marks)

(b) **In your own words**, explain what are the design principles of **“Component Based Software Engineering”** (CBSE). (2 Marks)

(c) **In your own words**, please clarify why **Object-Oriented Paradigm** is good and use a simple real-world scenario to explain it (such as invoking a function of an object to achieve a task). (4 Marks)

## Question 2. Roles of Variables – CILO4 (10 Marks)

Study the following Java codes and identify the role of each variable declared in the code listing by completing the tables below:

```java
public class ConvertTemperature {

    // Fahrenheit to Celsius

    public static void main(String[] args) {

        int fah[] = { 57, 59, 64, 65, 70, 68, 67, 68, 70, 69 };
        float cel[] = new float[fah.length];
        final int n = fah.length;
        final int base = 32;
        final float ratio = 5 / 9;
        float sum = 0;
        float avg = 0;
        float conv;

        for (int i = 0; i < n; i++) {

            conv = (fah[i] - base) * ratio;
            cel[i] = conv;
            sum = sum + conv;

        }

        avg = sum / n;
        float prev = cel[0];
        float max_diff = 0;
        float tmp = 0;

        for (int i = 1; i < n; i++) {

            tmp = Math.abs(cel[i] - prev);
            if (tmp > max_diff) {
                max_diff = tmp;
            }

            prev = cel[i];

        }

        System.out.print("Celsius Temp:\t");
        for (int i = 0; i < n; i++) {
            System.out.print(cel[i] + ", ");
        }

        System.out.println();
        System.out.println("Size of Array:\t" + n);
        System.out.println("Average Temp:\t" + avg);
        System.out.println("Max Difference:\t" + max_diff);

    }

}
```

Your Answer (1 Mark each):

| Variable | Role |
| -- | -- |
| fah |  |
| cel |  |
| n |  |
| conv |  |
| sum |  |
| avg |  |
| i |  |
| base |  |
| tmp |  |
| ratio |  |

## Question 3. Software Requirements Analysis – CILO2 (20 Marks)

**HKCity-Mall Online Shopping System**

**HKCity-Mall** is the one-stop shopping platform in Hong Kong, which servers Food, Electronics, Beauty and other aspects of our daily lives. The customers can either order the items in O2O stores, or place an order through the **HKCity-Mall online shopping system** (i.e. website and APP).

A customer can check product availability and add product into shopping cart. When the customer checking out, the system will calculate total amount and then proceed to make payment. Three payment services (i.e. Credit Card Payment Service, PayPal Payment Service and PayMe Payment Service) are available on the system. If the payment is successful, the system will confirm order.

The customer can check order detail. The customer is also allowed to change delivery arrangement in the check order detail screen if needed.

The vendor can update product availability and monitor product status. If one product has been purchased, the system will make notification to the vendor. Then the vendor will arrange order delivery. If the customer changes delivery arrangement, the vendor should re-arrange the delivery.

Use cases should include: Check Product Availability, Add Product, Checkout, Calculate Total Amount, Make Payment, Pay by Credit Card, Pay by PayPal, Pay by PayMe, Confirm Order, Check Order Detail, Change Delivery Arrangement, Update Product Availability, Monitor Product Status, Make Notification and Arrange Order Delivery.

Remark: it is possible to use inheritance between use cases, and use Include/Extend when appropriate

(a) Based on the above, draw a use case diagram for the HKCity-Mall Online Shopping System. (15 Marks)

(b) Based on the same case study in (a), <u>complete Steps 4 and 5 in the following table</u> to describe the use case **Make Payment** under typical course of event (default to pay by credit card), and alternative course of events. You may use your own understanding of making payment when online shopping. (5 Marks)

<table>
    <tr>
        <td>Use Case Name:</td>
        <td>Make Payment</td>
    </tr>
    <tr>
        <td>Actor(s):</td>
        <td>Customer</td>
    </tr>
    <tr>
        <td>Description</td>
        <td>This use case describes the process of customer making payment.</td>
    </tr>
    <tr>
        <td>Reference ID:</td>
        <td>HKCity Mall Payment - 1.0</td>
    </tr>
    <tr>
        <td>Typical course of events:</td>
        <td>
            <table>
                <tr>
                    <th>Customer Action</th>
                    <th>System Response</th>
                </tr>
                <tr>
                    <td>Step 1: This Customer checks out the shopping cart</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td>Step 2: The system shows the total amount and provides three payment options.</td>
                </tr>
                <tr>
                    <td>Step 3: The Customer chooses to pay by credit card.</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td>Step 4:</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Step 5: </td>
                </tr>
                <tr>
                    <td>Step 6: The order is confirmed and the customer is able to check order details.</td>
                    <td></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>Alternative course of events:</td>
        <td>Step 3a: the customer is able to choses to pay by PayPal 
            Step 3b: the customer is able to choses to pay by PayMe 
            Step 4a: the system redirect to the PayPal Payment Service to process the payment 
            Step 4b: the system redirect to the PayMe Payment Service to process the payment
        </td>
    </tr>
    <tr>
        <td>Precondition:</td>
        <td>Make payment can only be processed when the shopping cart is non-empty and the customer checks out.</td>
    </tr>
    <tr>
        <td>Postcondition:</td>
        <td>The completed transactions will be recorded.</td>
    </tr>
</table>

## Question 4. OO Modelling and Design Principles – CILO4 (55 Marks)

Study the following Java code fragments and answer the following questions.

```java
class Person {
    private String name;
    private int yearOfBirth;
    private Address address;

    Person(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public void setAddress(String street, String town, String country) {
        address = new Address(street, town, country);
    }

    public Address getAddress() {
        return address;
    }
}

class Staff extends Person {

    private String room; // Staff Room number

    Staff(String name, int yearOfBirth, String roomNumber) {
        super(name, yearOfBirth);
        room = roomNumber;
    }

    public void setRoom(String newRoom) {
        room = newRoom;
    }

    public String getRoom() {
        return room;
    }
}

class Student extends Person {
    private String SID; // student ID number

    public Student(String name, int yearOfBirth, String studentID) {
        super(name, yearOfBirth);
        SID = studentID;
    }

    public String getStudentID() {
        return SID;
    }
}

class Address {
    private String street;
    private String town;
    private String country;

    public Address(String street, String town, String country) {
        this.street = street;
        this.town = town;
        this.country = country;
    }

    public String toString() {
        return street + "\n" + town + " " + "\n" + country + "\n";
    }
}
```

(a) Based on your observation of the above code fragments, draw a complete **Class Diagram**. (8 Marks)

(b). Further Extending your class diagram in **(a)**. Assuming that there are a number of University Events (Hint: Class Event) at the University which are organized by Staffs and Students within the University, meaning that there are many Events organized by many People (Staff/Student). **Extend** the existing class diagram design to correctly show classes **EventAllocation** and **Event**. (20 Marks Total)
- Class **Event** should contain important attributes (in String) of Venue, Date, and Event_Name, and be initialized by its constructor. (5 Marks)
- Class **EventAllocation** should record/associate which **Person** is Responsible for the **Event**, and initialized by its constructor. more importantly it must contain an attribute for the person’s **Role (of responsibility)** in the allocated event, typically x3 roles **Manager**, **Assistant** and **Technician** (5 Marks)
- Different Roles (**Manager**, **Assistant** and **Technician**) are paid differently, and we need an operation/function in class EventAllocation called int **getPaidRate()**, use a suitable Design Pattern allowing the design to comply with OCP Design Principle, so additional Roles could be easily added/removed in the future maintenance. (10 Marks)

(c) Which Design Pattern was applied in your design in (b)? (1 Marks)

(d) Which Design Principle was complied to in your design in (b)? (1 Marks)

(e) Do you consider your design (design pattern) memory efficient in your design in (B)? Why/Why Not? And What Design Pattern would you add to that so to address the potential issue? (2 Marks) Show your Design (Please also update your final design in the full class diagram section below, to include this solution or design pattern. (3 Marks)

(f) **Sequence Diagram** - Continue from the previous sections. There is an **AllocationManager** class keeps a List of **EventAllocations** using ArrayList. Based on the following function draw a complete sequence diagram to describe the execution sequence of **AllocatePersonToEvent()**. (10 Marks)

```java
class AllocationManger {
    private List<EventAllocation> events_alloc_list = new ArrayList<EventAllocation>();

    public void AllocatePersonToEvent (Person person, Event event, String role) {
        EventAllocation alloc = new EventAllocation (person, event);
        alloc.setRole (role); 
        events_alloc_list.add (alloc);
    }
}
```

(g) Class Associations (10 Marks)

```java
// Code 1:
public class Address {
    public void showDetail() { };
}

// Code 2:
class Person {
    private Address address;
    public void getAddress (){
        address.showDetail();
    }
}

// Code 3:
class Person {
    public void getAddress (Address address){
        address.showDetail();
    }
}
// Code 4:
class Person {

    private final Address address = new Address ();
    public void getAddress (){
        address.showDetail();
    }
}

// Code 5:
class Person {
    private Person friend; 
    public void changeFriend(Person _friend){ 
        friend = _friend; 
    } 
}

```

| Code | Class Diagram (attributes/operations not required) + Association Name |
| -- | -- |
| Code 1  | Provide the following x4 class diagrams to use the correct UML notation and indicate their correct linkage relationship (Associations / Aggregation / Composition). |
| With class Address above + **Code 2** | \<class diagram\> <br>This exhibit is <your_answer> relationship. |
| With class Address above + **Code 3** | \<class diagram\> <br>This exhibit is <your_answer> relationship. |
| With class Address above + **Code 4** | \<class diagram\> <br>This exhibit is <your_answer> relationship. |
| With class Address above + **Code 5** | \<class diagram\> <br>This exhibit is <your_answer> relationship. |

## Question 5. Software Engineering Professional Ethics (5 Marks):

**KillWorm Company** develops software applications and has a supporting website for all the users, aiming to collecting the feedbacks of their software products from users. Alison is the Software Engineer and Manager Kelly supervises Alison.
- Alison is responsible for collecting feedbacks and solving the problems in the software product within 3 days. One day, Alison has received a feedback report that contains a minor bug but the process of patching the software is very complex. He knew that it would take a longer period (more than 3 days) to fix and test it.
- Alison evaluated the severity of the bug, and there is only 0.1% chance that the bug would affect the normal use of the software, so the bug report was deliberately ignored.
- Kelly uses their software products regularly to check whether there is any bug, and she discovered the bug by herself, which has not been fixed within 3 days after collecting the report on this issue.
- Kelly therefore coaches Alison about professionalism and requires Alison to fix the bug and sends an email to all the affected users to explain the situation and provides the estimated repair completion time.

Show at least two codes of ethics Alison violates with detailed explanations; and show at least three codes of ethics Kelly follows with detailed explanations.

(a) Alison violates (2 marks)
(b) Kelly follows (3 marks)