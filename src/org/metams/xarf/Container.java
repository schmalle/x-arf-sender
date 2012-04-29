package org.metams.xarf;

/**
 * Created with IntelliJ IDEA.
 * User: flake
 * Date: 4/7/12
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Container
{


    /**
     *
     * @param ip
     * @param date
     * @param sender
     * @param service
     * @param servicePort
     * @return
     */
    public String getDataStructure(String ip, String date, String sender, String service, String servicePort)
    {
        return
                         "Reported-From: " + sender + "\n" +
                        "Category: abuse\n" +
                        "Report-Type: login-attack\n" +
                        "Service: " + service + "\n" +
                        "Version: 0.1\n" +
                        "User-Agent: metams sender V0.1\n" +
                        "Date: " + date + "\n" +
                        "Source-Type: ip-address\n" +
                        "Source: " + ip + "\n" +
                        "Port: " + servicePort + "\n" +
                        "Report-ID: 12675788150797@metams\n" +
                        "Schema-URL: http://www.x-arf.org/schema/abuse_login-attack_0.1.1.json\n";

    }

}
