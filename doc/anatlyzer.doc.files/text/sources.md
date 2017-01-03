
# Setting up the source code

## Obtaining the source code

The source code is hosted in GitHub: [http://github.com/jesusc/anatlyzer](http://github.com/jesusc/anatlyzer "anATLyzer source code"). You can obtain the source code with:

	git clone http://github.com/jesusc/anatlyzer

## Required software

* Java 8
* Developed on Eclipse Luna (tested on Eclipse Mars as well)
* ATL 3.x
* Zest 1.5 (optional)
* UML 2.x (optional)

## Project organization

The source code is organized in the following folders:

	+ plugins
		- Plug-ins with the actual implementation of anATLyzer.
		  These are the plug-ins that you have to import in your
		  Eclipse workspace if you just want to have a look to the
		  source code or fix bug fixes.		  
	+ releng
		- Plug-ins related to deployment
	+ tasks
		- Projects that implementing some batch tasks that needs to
		  be run when the extended ATL meta-model is modified.
	+ doc
		- Projects related to automatically generated documentation
		  and regular documentation.
	+ experiment
	    - Projects to launch batch experiments
	+ evaluation
		- Projects holding the configuration of batch experiments
	+ examples
		- Official examples (intended to be documented...)
	+ examples-tests
		- Working examples
	+ tests
		- Test projects. 
	+ web
		- The tool web site.

To have a working instance of anATLyzer running with the source code, just import the 
projects in the `plugins` folder. 

	+ anatlyzer.atl.editor
		- GUI-related code: extension of the ATL editor, builder and analysis view
	+ anatlyzer.atl.editor.quickfix
		- Implementation of the catalog of quick fixes
	+ anatlyzer.atl.editor.quickfix.visualization
		- Requires Zest 1.5
		- Experimental support for visual, search-based error fixing
	+ anatlyzer.atl.standalone.api
		- Facilities to use anATLyzer in standalone mode (i.e., without running a Eclipse UI)
	+ anatlyzer.atl.typing
		- Main anATLyzer code: type inference, extended ATL meta-model, constraint solving, etc. 
	+ anatlyzer.atl.uml
		- Requires UML2
		- Extension to support UML profiles
	+ anatlyzer.ocl.emf
		- Integration of OCL invariants embedded into Ecore meta-models 
	+ anatlyzer.use.witness
		- Interface with the USE constraint solver
	+ anatlyzer.visualizer
		- Requires Zest 1.5
		- Extensions to visualize problems and rule relationships

		
## Extending anATLyzer

The `anatlyzer.atl.editor` provides a number of extension points to enhance
the anATLyzer IDE with additional capabilities. The main ones are:

	+ anatlyzer.atl.editor.additionalanalysis
	  - Allow new analysis to be added. A complete example can be found in anatlyzer.atl.uml
	+ anatlyzer.atl.editor.quickfix
	  - Used to define new quick fixes
	+ anatlyzer.atl.editor.quickassist
	  - Used to define new quick assists
	  
 	