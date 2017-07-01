
# Using AnATLyzer programatically

## Setting up the project

You need to create a new Plug-in project. Then add the following dependencies:

	+ anatlyzer.atl.typing : This is the analysis core 
	+ anatlyzer.atl.editor 


## Using AnATLyzer as an interface to the constraint solver

It uses internally a model finder (USE Validator) to improve the precision to 
detect certain kinds of errors.  
