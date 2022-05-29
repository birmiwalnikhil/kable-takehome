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
    packageManager.depend(TELNET, TCPIP);

  }
}
