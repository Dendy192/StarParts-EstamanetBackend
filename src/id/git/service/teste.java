package id.git.service;

import java.net.URISyntaxException;
import java.util.Set;

import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

public class teste {

	public static void main(String[] args)
			throws URISyntaxException, MonitorException {
		// TODO Auto-generated method stub
		String processName = "id.git.main.Main";

		boolean running = false;
		HostIdentifier hostIdentifier = new HostIdentifier("local://localhost");

		MonitoredHost monitoredHost;
		monitoredHost = MonitoredHost.getMonitoredHost(hostIdentifier);

		Set activeVms = monitoredHost.activeVms();
		for (Object activeVmId : activeVms) {
			VmIdentifier vmIdentifier = new VmIdentifier(
					"//" + String.valueOf(activeVmId) + "?mode=r");
			MonitoredVm monitoredVm = monitoredHost
					.getMonitoredVm(vmIdentifier);
			if (monitoredVm != null) {
				String mainClass = MonitoredVmUtil.mainClass(monitoredVm, true);
				if (mainClass.toLowerCase().equals(processName.toLowerCase())) {
					running = true;
					break;
				}
			}
		}

		System.out.print(running);
	}

}
