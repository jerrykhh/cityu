# CS3481 Assignment 2

## Objective
In this assignment, you will implement different predictive modeling approaches based on the random forest classifier and naïve Bayes classifier using Python.

## Detailed Requirement
Random  forest  is  an  ensemble  predictive  modeling  approach  which  combines  multiple  decision  trees,  with  each  tree  modeling  a  different  aspect  of  the  data  set.    Specifically,  each  component tree is constructed by sampling the original training set with replacement to create  a  new  training  set,  based  on  which  the  tree  structure  is  determined.    In  addition,  a  random  subset  of  attributes,  instead  of  the  complete  set  of  attributes,  is  used  for  evaluating  the  best attribute  for  splitting  the  data  records  at  each  node. A  random  forest  model  can  be  constructed by using theensemble module in the Python package scikit-learn. In   this   assignment,   you will   implement   and   evaluate   different   predictive   modeling   approaches using theVertebral Column data set in Assignment 1. You may visualize the  component  trees  of  the  random  forest  model  using  the  package python-graphviz. 

## Report
You  should  submit  a  report  to  summarize  your  work.  For  the  above data  set,  the  following  tasks are to be performed:
1. Construct random forest models using different numbers of component trees based on a   specific training   set/test   set   partition,   and   analyze   the   resulting   change   in   classification performance.  (25%)
2. For  the  random  forest  model  corresponding  to  the  best  classification  performance, select different component decision trees in the model, and compare the classification performances of these trees with that of the original random forest model. (25%)
3. For a random forest classifier (or one of its component trees), the relative importance of the attributes can be measured through the feature_importances_ field of the classifier.    For  selected  component  trees  in  (b),  compare  their  associated  lists  of  relative attribute importance values.    (25%)
4. Construct  a  naïve  Bayes  classifier  model  based  on  our  data  set,  and  compare  the  classification performance with that of the random forest model.  (25%)

Please provide a detailed description of the results of the above tasks in your report