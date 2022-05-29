package kable.packages;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/** 
 * A PackageManager that supports installing, removing, and listing packages.
 */
public class PackageManager {

  /**
   * A mapping from PackageName -> Package for all packages that 
   * the PackageManager is aware of. A package may not be installed, e.g.
   * it was introduced via a {@link #depend} call.
   */
  Map<String, Package> packages;

  public PackageManager() {
    this.packages = new HashMap<>();
  }

  /**
   * Introduce a list of dependencies required for {@code packageA}.
   */
  public void depend(String packageA, String dependency, String...
otherDependencies) {
    Package primaryPackage = getPackage(packageA);
    storePackage(primaryPackage);

    // For each dependency B, indicate that A is dependent on B,
    // and that B is needed for A.
    Set<String> dependencies = new HashSet<>();
    dependencies.add(dependency);
    for (String otherDependency : otherDependencies) {
      dependencies.add(otherDependency);
    }

   for (String b : dependencies) {
      Package dep = getPackage(b); 
      primaryPackage.addDependency(dep);
      dep.addNeededBy(primaryPackage);
      storePackage(dep);
    }
    
    // Echo the output, as per spec.
    StringBuilder sb = new StringBuilder();
    sb.append("DEPEND ").append(packageA).append(" ").append(dependency);
    for (String d : otherDependencies) {
      sb.append(" ").append(d);
    }
    echo(sb.toString());
    return;
  }

  /** Install {@code packageA}, as well as any uninstalled dependencies. */
  public void install(String packageA) {
    echo("INSTALL %s", packageA); 
    installPackage(getPackage(packageA));
  }

  private void installPackage(Package packageA) {
    // Do nothing; the requested package is already installed..
    if (packageA.isInstalled) {
      return;
    }
    // Recursively install any of the dependencies.
    for (Package dep : packageA.dependencies) {
      installPackage(dep);
    }

    // Mark this package as installed, and echo back to the user.
    packageA.setInstall(true);
    storePackage(packageA);
    echo("\tInstalling %s", packageA.name); 
  }
 
  /** 
   * Attempt to uninstall {@code packageA}, cleaning up
   * any unused dependencies in the process.
   */  
  public void remove(String packageA) {
    Package p = getPackage(packageA);    

    // Echo the command back to the user.
    echo("REMOVE %s", packageA);
    if (!p.isInstalled) {
      echo("\t%s is not installed.", packageA);
      return;
    }
    
    if (!p.activeNeededByPackages().isEmpty()) {
      echo("\t%s is still needed.", packageA);
      return;
    }

    removePackage(p); 
  }

  private void removePackage(Package p) {
    // Mark this package as uninstalled.
    p.setInstall(false);
    
    echo("\tRemoving %s", p.name);

    // Attempt to uninstall any dependencies if they are only used
    // by p.
    for (Package dep : p.dependencies) {
      if (dep.activeNeededByPackages().isEmpty()) {
        removePackage(dep);
      } 
    }
  }

  
  /** List all installed packages. */
  public void list()  {
    echo("LIST");
    for (Package p : this.packages.values()) {
      if (p.isInstalled) {
        echo("\t%s", p.name);
      }
    }
  }

  public void debugLog() {
    for (Package p : this.packages.values()) {
      System.out.println(p.toString());
    }
  }

  /** Echo some feedback to the user. */
  private void echo(String formatLog, Object... objects) {
    System.out.println(String.format(formatLog, objects));
  }

  /** Retrieve a package given it's name, or create one if need be. */
  private Package getPackage(String name) {
    return this.packages.getOrDefault(name, new Package(name));
  }
  
  /** Store a package with its given name. */
  private void storePackage(Package p) {
    this.packages.put(p.name, p);
  }
}
