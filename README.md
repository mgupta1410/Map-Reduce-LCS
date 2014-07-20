
PROBLEM STATEMENT : 
- Given a collection of N text documents, an intersection of content has to be obtained as defined below:

- Assume each document (i.e. file) is a sequence of lines (i.e. each line is a text string that is treated as one entry in the sequence.).

- Compute the pair-wise length of LCS (longest common-sub-sequence) for the N documents.

- Select the top K pairs with largest lengths. Assume K << N.

- Compute the pair-wise LCS (longest common-sub-sequence) for the K' documents involved in the top K pairs. Note that  K' <= 2*K.

- Compute an intersection of the content in the K' documents i.e. the items that occur in all K' documents if any.


INSTRUCTIONS :

- Import the Folder "trial1" to Eclipse with Hadoop Add-On.
- Give the path of Input Directory containing files as the first argument.
	Eclipse - Run Configuration -> Arguments -> PATH_INPUT_FOLDER k
	1.) First argument should be the absolute path of the folder in which contains the input files.
	2.) Second argument will be k. Please make sure that k <= NC2 (where N is the number of files in the path of Input 		folder)
	3.) There should be no folder named "FirstReduce" and "Out" prior to running the code, in the current Directory.
	4.) The final output will be printed in Out-> part-00000
