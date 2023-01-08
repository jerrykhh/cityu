# MA2185 Quiz

## Question 1
Construct a truth table for the following compound proposition $(p\vee q)\to (p\wedge q)$

## Question 2
Let $Q(x)$ be the statement $x+1> 2x$. If the domain consists of all integers, which are these truth values?
1. $Q(-1)$
2. $\forall xQ(x)$

## Question 3
Find the negation of the quantification: 
```math
\forall x(C(x)\to (D(x)\wedge E(x)))
```

## Solution
### Q1
| p | q | $(p \vee q)$ | $(p \wedge q)$ | $(p\vee q)\to (p\wedge q)$
| -- | -- | -- | -- | -- |
| T | T | T | T | T |
| T | F | T | F | F |
| F | T | T | F | F |
| F | F | F | F | T |

### Q2

(1) True, statement Q(-1) by setting the x = -1, -1 + 1 &gt; 2 (-1) is True

(2) False, assume the statement to Q(1) by setting x = 1, 1 + 1 is not &gt; 2(1)

### Q3

$\equiv \exists x \neg(C(x)\rightarrow (D(x)\wedge E(x)))$
$\equiv \exists x \neg(\neg C(x) \vee (D(x)\wedge E(x)))$
$\equiv \exists x (\neg \neg C(x))\wedge  (\neg (D(x)\wedge E(x)))$
$\equiv \exists x (C(x)\wedge  (\neg (D(x)\wedge E(x))))$
$\equiv \exists x (C(x)\wedge  (\neg D(x)\vee \neg E(x)))$
$\equiv \exists x (C(x)\wedge  (D(x)\rightarrow \neg E(x)))$