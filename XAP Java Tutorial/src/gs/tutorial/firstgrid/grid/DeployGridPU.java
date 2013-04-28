package gs.tutorial.firstgrid.grid;

import java.util.concurrent.TimeUnit;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.space.SpaceDeployment;
import org.openspaces.core.GigaSpace;

public class DeployGridPU {

	public static void main(String[] args) {


		try {
			// create an admin instance to interact with the cluster
			Admin admin = new AdminFactory().createAdmin();

			// locate a grid service manager and deploy a partioned data grid
			// with 1 primary and one backup 
			GridServiceManager esm = admin.getGridServiceManagers()
					.waitForAtLeastOne();

			ProcessingUnit pu = esm.deploy(new SpaceDeployment("mySpace").partitioned(1, 1));

			// Once your data grid has been deployed, wait for 2 instances (1
			// primaries and 1 backups)
			pu.waitFor(2, 30, TimeUnit.SECONDS);

			// and finally, obtain a reference to it
			@SuppressWarnings("unused")
			GigaSpace gigaSpace = pu.waitForSpace().getGigaSpace();
			
			System.out.println("Deployment done");

		} catch (Exception e) {
			
			e.printStackTrace();
			// already deployed, do nothing
		}
	}
}
