Example Project showing the use of Karaf CLI and MBeans 

The MBean and the Karaf CLI command interact with the same unerlying resource showing how values can be set, retrived and operations executed within the container.

The underlying Role Based Access Control (RBAC) can then be used to control which user have access to the unerlying MBean / command by setting these up in the /etc/auth folder of the container.

The Mean is accessable via the Hawtio console (JBoss Fuse and deploably into Karaf) and the same ACL is applicable

The project can simple be buit and deployed either directly into the /deployment folder of Karaf (hot deploy), using the osgi:install mvn:co.uk.sussexsoftware/example-control/<vserion>  or indirectly onto a Fabric profile or via a Karaf fature file.

Once deployed and started (use osgi:start <bundle-id>) the example:<commands> will be available depending on the user and ACL's that have been included.
