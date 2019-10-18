import java.net.InetAddress;
/**
 * This classes searches all available IPs in the LAN subnet
 * @author Asif Khan
 * @version 1.0
 * @since 18/10/2019
 */

public class ReachableIPs {
	/**
	 * Search the IPs in LAN of connected nodes
	 * @param timeout time for searching a single IP, IP is not reachable if timeouts and still not fetched any information
	 * @param LocalHostIP current machine private IP address
	 * @param subnet Fixed part of Private IP address
	 * @param IPClass Class of private IP (A, B, C)
	 */
	public static void SearchAllIPs(String localHostIP, String subnet, char IPClass, short timeout) {
		try {
			String host;
			if(IPClass == 'A') {
				for(int i = 0; i < 255; i++)
					for(int j = 0; j < 255; j++)
						for(int k = 4; k < 255; k++)
							{
								host = subnet + i + "." + j + "." + k;
								System.out.print(".");										// For Test
								if(InetAddress.getByName(host).isReachable(timeout)) {
									System.out.println(host + "is reachable");				// For Test
								}
							}
			}
			else if(IPClass == 'B') {
				for(int i = 0; i < 255; i++)
					for(int j = 4; j < 255; j++) {
						host = subnet + i + "." + j;
						System.out.print(".");										// For Test
						if(InetAddress.getByName(host).isReachable(timeout)) {
							System.out.println(host + "is reachable");
						}
					}
			}
			else {
				for(int i = 0; i < 255; i++) {
					host = subnet + i;
					System.out.print(".");										// For Test
					if(InetAddress.getByName(host).isReachable(timeout)) {
						System.out.println(host + "is reachable");
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("Could't search LAN IPs");
		}
	}
	/**
	 * Find the part of the IP address that remains same throughout the LAN for all devices.
	 * @param LocalHostIP IP address of current Local machine
	 * @param IPClass Class of LAN IP address (A, B, C)
	 * @return Part of the IP address that remains same on all nodes in the LAN
	 */
	public static String Subnets(String LocalHostIP, char IPClass) {
		if(IPClass == 'A')
			return LocalHostIP.substring(0, LocalHostIP.indexOf(".") + 1);
		else if(IPClass == 'B')
			return LocalHostIP.substring(0, LocalHostIP.indexOf(".", 5) + 1);
		else
			return LocalHostIP.substring(0, LocalHostIP.lastIndexOf(".")+ 1);
	}
	/**
	 * @param LocalHostIP CurrentIP address of the local machine
	 * @return Private IPClass character (A,B or C)
	 */
	public static char IPClass(String LocalHostIP) {
		short ipClass = Short.parseShort(LocalHostIP.substring(0, LocalHostIP.indexOf(".")));
		if(ipClass == 10)
			return 'A';
		else if(ipClass == 172)
			return 'B';
		else
			return 'C';
	}
	
	public static void main(String[] args) {
		try {
		String localHostIP = InetAddress.getLocalHost().getHostAddress();
		char IPClass = IPClass(localHostIP);
		String subnet = Subnets(localHostIP, IPClass);
		System.out.println("Subnet: "+ subnet);				// For testing
		System.out.println("IPClass: "+ IPClass);
		System.out.println("LocalHostIP: "+ localHostIP);
		System.out.println("Keep patience, Searching is started... It will take some time... \n");
		SearchAllIPs(localHostIP, subnet, IPClass, (short) 500);
		}
		catch(Exception e) {
			System.out.println("Error Occurred in the Main Program...");
		}

	}

}
