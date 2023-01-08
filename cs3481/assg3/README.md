# CS3481 Assignment 3

## Objective
In  this  assignment,  you  will  study  the  hierarchical  clustering approach introduced  in  the  classusing Python. 

## Detailed Requirement
We have introduced the hierarchical clustering approach in the class. In this assignment, you will apply this approach to the Vertebral Column data set from the UCI Machine Learning Repository. 
You   can   perform   hierarchical   clustering   using   the   methodlinkage from   the   module   scipy.cluster.hierarchy. 

After performing hierarchical clustering, you could visualize the clustering result in the form of a dendrogram by using the method dendrogram.   You could also study the clustering solution for a specific number of clusters in the hierarchy by using the method fcluster. 

To perform clustering, you should only use the input attributes but not the class label. To improve the  clustering  results,  you  could  consider  removing  outliers  from  the  data  set,  and  applying  a  suitable normalization operation to the input attributes.

## Report 
You should submit a report to summarize your work. The following tasks are to be performed:

1. Compare  the  hierarchical  structures  generated  using  single  link,  complete  link  and  group  average for the Vertebral Column data set.  (30%)
2. For some of these hierarchical structures, observe the set of distance values at which cluster merge occurs, and identify possible patterns from these values.    (20%)
3. Select different clustering solutions from the hierarchical structures, and compare the cluster groupings  with  (a) the  corresponding  K-means  clustering  solutions  (using  the  method KMeans from the module sklearn.cluster) and (b) the actual groupings of the data pointsbased on their class labels. (30%) 
4. Select different subsets of attributes from the data sets and re-perform hierarchical clustering. Compare the resulting hierarchical structures based on the selected attribute subsets with the original hierarchical structures. (20%)

Please provide a detailed description of the results of the above tasks in your report