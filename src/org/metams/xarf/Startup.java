package org.metams.xarf;

/**
 * Created with IntelliJ IDEA.
 * User: flake
 * Date: 4/15/12
 * Time: 8:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Startup
{
    static public void main(String[] args)
    {
        Container container = new Container();
        Mailer mailer = new Mailer("USERNAME", "PASSWORD", "SMTPSERVER");


        String messageForAbuse = "Hi Abuse team, This is a standard XARF report, please check the attachments...";
        String messageStructure = container.getDataStructure("COMPLAININGIP", "DATE", "NAME_OF_COMPLAINING_PERSON", "SERVICE", "SERVICEPORT");
        String messageLog = "Dummy Kippo log";

        mailer.sendARF("TARGET", messageForAbuse, messageStructure, messageLog, "COMPLAININGIP", "DATE", "web");

    }
}
