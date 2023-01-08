# CS3481 Midterm

## Section A (50 marks)

1. We consider the following data points: (0, 5), (2, 3), (3, -6), (5, -2), (6, -4), (8, -11). 
   1. Calculate the covariance matrix of this set of data points. (14 marks)
   2. Calculate the correlation coefficient between the two attributes. ( 6   marks)
2. Compared  with  the  case  of  a  discrete  attribute,  what  additional  steps  are  required  to  calculate the information gain associated with a continuous attribute when constructing a decision tree? (14 marks)
3. For a decision tree, how is the gain ratio defined?  What is the main reason of adopting this measure for attribute selection? (16 marks)

## Section B (50 marks)

#### Question 1
Suppose we need to construct a decision tree for predicting whether a staff will obtain a salary  raise.    The  decision  is  made  based  on  the  following  attributes:  the  academic  qualification of the staff (**ACAD**), the current position of the staff (**POS**), and his/her job performance (**PERFORM**).  The following table shows the corresponding attribute values of 8 data records.

| Staff | ACAC | POS | PERFORM | Salary raise? |
| -- | -- | -- | -- | -- |
| 1 | Master's degree | Junior | Good | Yes |
| 2 | Master's degree | Junior | Good | Yes |
| 3 | Bachelor's degree | Junior | Good | Yes |
| 4 | Bachelor's degree | Junior | Good | Yes |
| 5 | Master's degree | Junior | Poor | No |
| 6 | Associate's degree | Junior | Poor | No |
| 7 | Associate's degree | Senior | Good | No |
| 8 | Bachelor's degree | Senior | Good | No |

(a). Which  attribute  will  be  first  chosen  according  to  the  information  gain  measure? (18 marks)

(b). Complete  the  construction  of  the  tree  so  that  all  the  above  records  are  classified  correctly.  Show the structure of the final tree. (16 marks)

#### Question 2
We consider the following set of data records, each of which is associated with a class of Large or Small.

| Record | P | Q | R | Class |
| -- | -- | -- | -- | -- |
| 1 | Good | Low | Yes | Large |
| 2 | Good | Low | No | Large |
| 3 | Average | Medium | Yes | Large |
| 4 | Average | Low | No | Small |
| 5 | Good | High | Yes | Small |
| 6 | Average | High | Yes | Small |
| 7 | Poor | Medium | No | Small |
| 8 | Poor | Medium | Yes | Small |

Predict the class label for a test example (P=Average, Q=Medium, R=No) using the naive Bayes approach.