
# Experiments

## Introduction

This document describes how to invoke anATLyzer programmatically. 
There are two 

## Standalone

Project anatlyzer.experiments.performance uses AspectJ to add measuring capabilities
to the different parts of AnATLyzer. Setup steps, following:
http://wiki.eclipse.org/Equinox_Weaving_QuickStart
http://www.programering.com/a/MjM2MDMwATE.html

 1. Create Plug-in project. Then, add Configure -> AspectJ nature

 2. Create an Aspect. Errors of kind "advice defined in TheAspect has not been applied". This is because AspectJ cannot
   find the adviced class, even if the plug-in is properly configured.

 3. Add the required plug-ins to the plug-in Dependencies.

 4. Add the following lines to indicate the Aspects that are exported and which bundles (plug-ins) 
    are to be modified by the aspect (anatlyzer.atl.typing) in this case, where the Analyser class resides.
 
 	Export-Package: anatlyzer.experiments.perf;aspects="MeasurePathComputationTime"
	Eclipse-SupplementBundle: anatlyzer.atl.typing
 
Run with the following VM arguments:

  -Dosgi.framework.extensions=org.eclipse.equinox.weaving.hook
  -Daj.weaving.verbose=true -Dorg.aspectj.weaver.showWeaveInfo=true -Dorg.aspectj.osgi.verbose=true

Configure the Eclipse launcher so that the plug-in org.eclipse.equinox.weaving.aspect has auto-start = true, 
and level = 2.

### Issues

* Be careful with the difference between pointcuts call and execution. With call, you need to include every 
  plug-in that calls the required method and you want to advice. On the contrary, for execution it is enough
  to include the plug-in that contains the class whose method is to be advised. 
