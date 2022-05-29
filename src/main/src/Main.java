package main.src;

import kable.packages.PackageManager;

/** Testing utility for the {@link PackageManager}. */
public class Main {

  // TODO: Move to a utilities class.
  private static final String TCPIP = "TCPIP";
  private static final String NETCARD = "NETCARD";
  private static final String TELNET = "TELNET";
  private static final String SOCKET = "SOCKET";
  private static final String DNS = "DNS";
  private static final String HTML = "HTML";
  private static final String REGEX = "REGEX";  
  private static final String XML = "XML";
  private static final String PARSING = "PARSING";
  private static final String BROWSER = "BROWSER";
  private static final String CSS = "CSS";

  public static void main(String[] args) {
    PackageManager packageManager = new PackageManager();
    packageManager.depend(TCPIP, NETCARD);
    packageManager.depend(TELNET, TCPIP, SOCKET);
    packageManager.depend(DNS, TCPIP);
    packageManager.depend(HTML, REGEX, XML);
    packageManager.depend(REGEX, PARSING);
    packageManager.depend(BROWSER, DNS, TCPIP, HTML, CSS);

    packageManager.install(TCPIP);
    packageManager.remove(NETCARD);
    packageManager.remove(TCPIP);
    packageManager.remove(NETCARD);
    packageManager.install(TCPIP);
    packageManager.list();
    packageManager.install(TCPIP);
    packageManager.install("foo");
    packageManager.remove(TCPIP); 
    packageManager.install(NETCARD);
    packageManager.install(TCPIP);
    packageManager.remove(TCPIP);
    packageManager.list();
    packageManager.install(TCPIP);
    packageManager.install(NETCARD);
    packageManager.remove(TCPIP);
    packageManager.list();
    packageManager.remove(NETCARD);
    packageManager.install(BROWSER);
    packageManager.list();
    packageManager.remove(BROWSER);
    packageManager.list();
    packageManager.install(HTML);
    packageManager.install(TELNET);
    packageManager.remove(SOCKET);
    packageManager.install(DNS);
    packageManager.install(BROWSER);
    packageManager.remove(NETCARD);
    packageManager.list();
    packageManager.remove(BROWSER);
    packageManager.list();
    packageManager.end();
  }
}
