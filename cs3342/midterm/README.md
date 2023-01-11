# CS3342 Midterm

## Question 1 (MC) – Software Engineering in General (10 Marks):

1. System maintenance is necessary because:

    a. Humans never get it right the first time.
    b. The deployment platform may change over time.
    c. The user's needs may change over time.
    d. All of the above.
    e. None of the above.

2. The work products produced during requirement elicitation will vary depending on the

    a. size of the budget.
    b. software process being used.
    c. size of the product being built.
    d. stakeholder’s needs.

3. The Waterfall Model is inadequate because:

    a. Water is a continuous medium whereas code comes in discrete chunks (i.e. functions, objects, etc.), so all water-based analogies for software development are doomed to failure.
    b. it incorrectly suggests that the sequence of development is a random process of rising and falling from stage to stage, with backwards progress just as likely as forwards.
    c. it incorrectly suggests that the sequence of software development is susceptible to uncontrollable external and internal forces (analogous to gravity and surface tension).
    d. it incorrectly suggests that the sequence of development is a process unpredictable in the details, but predictable in its overall effect, like a waterfall.
    e. it incorrectly suggests that the sequence of development is a progression from stage to stage, with no backwards steps.

4. An important aspect of prototyping is that

    a. it is comprehensive.
    b. it is built for change.
    c. aspects such as error-checking capabilities, I/O and complex calculations are handled.
    d. it evolves into the final product.
    e. All of the above.

5. Software deteriorates rather than wears out because

    a. Software spare parts become harder to order.
    b. Software suffers from exposure to hostile environments.
    c. Defects are more likely to arise after software has been used often.
    d. Multiple change requests introduce errors in component interactions.

6. The Waterfall model of software development

    a. Incorporates risk management.
    b. Involves developing a series of prototypes.
    c. Suggests that one should perform the steps in a sequential manner without iterating.
    d. Is considered the best way to develop software.
    e. Does not allow one to correct any mistakes.

7. Which of the following is not captured by software requirements?

    a. Resource estimates.
    b. Interface with other systems.
    c. System constraints.
    d. System services.

8. Methods of requirements gathering include

    a. interviews using structured techniques and close-ended questions.
    b. interviews using structured techniques and open-ended questions.
    c. questionnaires.
    d. an analysis of forms used by clients.
    e. All of the above.

9. The system specification describes the

    a. function, performance and constraints of a software system.
    b. implementation of each allocated system.
    c. element of software architecture.
    d. time required for system simulation.

10. Software Engineering is best described as:

    a. the practice of designing, building and maintaining ad-hoc software without the use of formal methods.
    b. the practice of designing, building, and maintaining off-the-shelf software from prefabricated parts.
    c. the practice of designing, building and maintaining fast and flexible software specifically for Engineering applications.
    d. the practice of designing, building and maintaining reliable and cost-effective software using standard techniques.
    e. the practice of designing, building and maintaining flashy, cheap and buggy software engineered to generate large initially sales and an on-going market for updates.

## Question 2 (30 Marks)
The book store is developing a new software system, which can be used to manage their books and keep track of the inventory of each book, with the following functions:

1. Add a new book (function: **addNewBook**)

2. Sell book with an amount (function: **sellBook**)

3. Get the maximum selling income among all books (function: **bestSellingBookIncome**)

Please study the following code listings for class **Book** and class **BookStore**.

```java
import java.util.*;

public class BookStore {
    private final List<Book> book_list = new ArrayList<Book>();

    public void addNewBook(String name, int stock, double price) {
        Book book = new Book();
        book.setBookName(name);
        book.setBookStock(stock);
        book.setBookPrice(price);
        book_list.add(book);
    }

    public boolean sellBook(String bk_name, int bk_num) {
        int stk = 0;
        int sell = 0;
        boolean success = false;
        for (int i = 0; i < book_list.size(); i++) {
            if (book_list.get(i).getBookName() == bk_name) {
                stk = book_list.get(i).getBookStock();
                if (stk >= bk_num) {
                    stk = stk - bk_num;
                    sell = book_list.get(i).getBookSell() + bk_num;
                    book_list.get(i).setBookStock(stk);
                    book_list.get(i).setBookSell(sell);
                    success = true;
                    return success;
                } else
                    System.out.println("Stock is not enough.");
            }
        }
        return success;
    }

    public double bestSellingBookIncome() {
        double current = 0;
        double best = 0;
        for (Book book : book_list) {
            current = book.getBookSell() * book.getBookPrice();
            if (current > best)
                best = current;
        }
        return best;
    }
}

public class Book {
    private String book_name;
    private int book_stock;
    private int book_sell;
    private double book_price;

    public String getBookName() {
        return book_name;
    }

    public int getBookStock() {
        return book_stock;
    }

    public int getBookSell() {
        return book_sell;
    }

    public double getBookPrice() {
        return book_price;
    }

    public void setBookName(String _name) {
        book_name = _name;
    }

    public void setBookStock(int _stock) {
        book_stock = _stock;
    }

    public void setBookSell(int _sell) {
        book_sell = _sell;
    }

    public void setBookPrice(double _price) {
        book_price = _price;
    }
}
```

### Question 2a - Roles of Variables (5 Marks)

Table 1: Roles for Variables in Software Programs

| Role | Description |
| -- | -- |
| Constant/ Fixed value | A variable which is initialized without any calculation and whose value does not change thereafter. |
| Stepper | A variable stepping through values that can be predicted as soon as the succession starts. |
| Most-recent holder | A variable holding the latest value encountered in going through a succession of values. |
| Gatherer | A variable accumulating the effect of individual values in going through a succession of values. |
| Transformation | A variable that always gets its new value from the same calculation from value(s) of other variable(s). |
| One-way flag | A two-valued variable that cannot get its initial value once its value has been changed. |
| Temporary | A variable holding some value for a very short time only. |
| Organizer | A data structure, which is only used for rearranging its data and object elements after initialization. |

Based on the code listings of class **<u>BookStore</u>**, correctly classify the role of each variable in the table below. You may use Table 1 for your reference. (5 Marks)

| Variable | Role of Variable (1 Mark Each) |
| -- | -- |
| book_list |  |
| i |  |
| bk_name |  |
| success |  |
| current |  |

### Question 2b – Class Diagram (5 Marks)
Based on the codes of class **Book** and class **BookStore**, draw a complete **Class Diagram**. Correctly show all the attributes and operations, as well as correctly show their class association using correct UML notation (hint: association/aggregation/composition).

### Question 2c – Sequence Diagram (10 Marks)

Based on the code of function **BookStore.addNewBook**, provide a complete **Sequence Diagram** with full execution sequence.

### Question 2d – Design Principles and Patterns (10 Marks)

The current design of the class **Book** is restricted to a single book type. The book store is planning to classify the books into 4 different types, more precisely as in (**Literary**, **Science**, **History** and **Philosophy**), so that the store can keep track of which kind of books are most popular.

We will need the function **getBookType()** accordingly for each of the 4 different book types (Literary, Science, History and Philosophy). In addition, the type class <u>has no data member</u> to keep any value.

Please show using class diagram on how do you modify the original design to provide different book types, so that the book store could easily get this information according to their respective book types, and also allows them to review and revise these rates in the future with ease. Moreover, the modified design should also be memory-efficient.

1. Which design patterns do you think that are most suitable? (3 Marks)

2. Like your solution in 2b, draw a modified/extended class diagram here. (7 Marks)

### Question 3 – Software Requirements (20 Marks)

**Case Study: <u>Bookstore Management System</u>**

A bookstore requires customers to make a booking of his preferred books before picking up the items in the physical store according to the reservation record. So, a bookstore management system is needed to speed up its operations.

A customer can view the book list, and then make a booking after checking the availability of the selected items. The customer can also view his booking record for details. If he changes his mind, he may cancel the booking or alter it to the other available books.

When the customer picks up his booked items in the physical store, the sellers are responsible to input the order according to the availability of the items and the booking record. If available, the sellers will proceed to collecting the payment. It will also print a bill for the customer.

If a discount is requested, the manager will be notified to authorize the discount. The collect payment function will also keep the track of the profits and updates statistics. Only the manager can view the statistics.

Based on the description of this system above, the following shows the use cases and actors in the system.

| Use Case | Actor(s) |
| -- | -- |
| View book list | Customer |
| Make new booking | Customer (if need; after checking availability) |
| View the booking | Customer |
| Cancel the booking | Customer (if need) |
| Alter the booking | Customer (if need) |
| Check availability of books | Seller + Customer |
| Input order | Seller (after checking availability and booking record) |
| Check booking record | Seller |
| Collect payment | Seller |
| Authorize discount | Seller (if need) + Manager |
| Print bill | Seller (after collect payment) |
| Update statistics | Seller (after collect payment) |
| View statistics | Manager |

Based on all the given information above, draw a complete **<u>Use Case Diagram</u>** of the system. You must utilize **<\<include\>>**, **<\<extend>\>** with correct arrows when possible, clearly indicating conditions for extension points if any. (20 marks)