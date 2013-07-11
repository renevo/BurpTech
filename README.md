BurpTech
========

Basically a bunch of random features and tweaks used by Burpcraft Server.


[Website](http://renevo.github.io/BurpTech)

[Feedback/Support](http://burptech.uservoice.com/)

***

Tools for the code
==================
 
 - [JRE](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
 - [Java](http://www.java.com/en/download/manual.jsp)
 - [Eclipse](http://www.eclipse.org/downloads/)
 - [GIT](http://git-scm.com/downloads)
  
  
  *NOTE: You should run this command in GIT to prevent eclipse workspace commits*
  
  <pre><code>
  git update-index --assume-unchanged eclipse\.metadata\.plugins\org.eclipse.core.resources\.root\7.tree
  git update-index --assume-unchanged eclipse\.metadata\.plugins\org.eclipse.core.resources\.safetable\org.eclipse.core.resources
  git update-index --assume-unchanged eclipse\.metadata\.plugins\org.eclipse.core.runtime\.settings\org.eclipse.debug.ui.prefs
  git update-index --assume-unchanged eclipse\.metadata\.plugins\org.eclipse.debug.ui\launchConfigurationHistory.xml 

  </code></pre>
  
***

Installing the code
===================

####Automatic####

Run setup.cmd *(Requires powershell at the moment, someone is more than welcome to create a linux version of this in a pull request)*



####Manual####

 - 1. Create Directory (/mcp/)
 - 2. Download Forge (minecraftforge-src-1.5.2-7.8.1.737)
 - 3. Extract zip into /mcp/
 - 4. Download MCP (mcp751)
 - 5. Extract zip into /mcp/
 - 6. Run /mcp/forge/install.cmd
 - 7. Wait for days
 - 8. Open Eclipse to /eclipse/ folder
 
   
***
 
 Building the code
===================

####Ants####

 - 1. Download `Apache Ant` (found [here](http://ant.apache.org/))
 - 2. Run ant on the `/` directory.
 - 3. Outputs will be in the `bin` directory
 

