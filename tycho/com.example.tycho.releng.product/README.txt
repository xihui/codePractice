README
------
   The RTI_AdminConsole.product file should, generally, not be edited with the
builtin Eclipse editor for it. The problem with doing this is that the 
attributes 'os', 'ws', and 'arch' are removed from this file since the editor 
doesn't need them. Our Maven/Tycho build system, however, relies on these 
attributes to filter out the applicable bundles for a given platform's build.
Below are examples of how these work with the initial (5.0.0) build of Admin
Console.


      <plugin id="org.eclipse.swt.win32.win32.x86" fragment="true" os="win32" ws="win32" arch="x86"/>
      <plugin id="org.eclipse.equinox.security.win32.x86" fragment="true" os="win32" ws="win32" arch="x86"/>
      <plugin id="org.eclipse.core.filesystem.win32.x86" fragment="true" os="win32" ws="win32" arch="x86"/>
      <plugin id="org.eclipse.ui.win32" fragment="true" os="win32" ws="win32" arch="x86"/>
      <plugin id="org.eclipse.swt.win32.win32.x86_64" fragment="true" os="win32" ws="win32" arch="x86_64"/>
      <plugin id="org.eclipse.equinox.security.win32.x86_64" fragment="true" os="win32" ws="win32" arch="x86_64"/>
      <plugin id="org.eclipse.core.filesystem.win32.x86_64" fragment="true" os="win32" ws="win32" arch="x86_64"/>
      <plugin id="org.eclipse.ui.win32" fragment="true" os="win32" ws="win32" arch="x86_64"/>
      <plugin id="org.eclipse.swt.gtk.linux.x86" fragment="true" os="linux" ws="gtk" arch="x86"/>
      <plugin id="org.eclipse.swt.gtk.linux.x86_64" fragment="true" os="linux" ws="gtk" arch="x86_64"/>

   The splash screen...oh, was that fun. The tycho build insist on creating its
very own config.ini file even if you tell it to use yours. So, in the build.xml
file there's a rule to update each config.ini file (since they will contain a
different set of plugins for each platform). The update is to the 
osgi.splashPath property to that it will look in the console bundle for the
splash.bmp file.
