Example Project showing the use of Karaf CLI and MBeans 

The MBean and the Karaf CLI command interact with the same unerlying resource showing how values can be set, retrived and operations executed within the container.

The underlying Role Based Access Control (RBAC) can then be used to control which user have access to the unerlying MBean / command by setting these up in the /etc/auth folder of the container.

The Mean is accessable via the Hawtio console (JBoss Fuse and deploably into Karaf) and the same ACL is applicable

The project can simple be buit and deployed either directly into the /deployment folder of Karaf (hot deploy), using the osgi:install mvn:co.uk.sussexsoftware/example-control/<vserion>  or indirectly onto a Fabric profile or via a Karaf fature file.

The Karaf commands include an example of using the ShellTable class for printing neat table output.  This class was not included until version 3 of Karaf and has to be installed into the container separately using mvn:org.apache.karaf.shell/org.apache.karaf.shell.table/3.0.6

Once deployed and started (use osgi:start <bundle-id>) the example:<commands> will be available depending on the user and ACL's that have been included.

The resource folder contains two access control files for example purposes.  These should be copied to the /etc/auth folder of the Karaf container and modified as required - by default the admin role has access to all commands and the viewer has access to read only commands#
