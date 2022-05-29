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
    // PackageA must not already be defined, as per the spec.
    if (packages.containsKey(packageA)) {
      echo("DEPEND on %s invalid; package already defined.", packageA);
      return;
    } 

    Package primaryPackage = getPackage(packageA); 
    Set<String> dependencies = new HashSet<>();
    dependencies.add(dependency);
    for (String otherDependency : otherDependencies) {
      dependencies.add(otherDependency);
    }

    // For each dependency B, indicate that A is dependent on B,
    // and that B is needed for A.
    for (String b : dependencies) {
      Package dep = getPackage(b); 
      primaryPackage.addDependency(dep);
      dep.addNeededFor(primaryPackage);
    }
    
    // Echo the output, as per spec.
    String formatLog = "DEPEND %s %s";
    for (int i = 1; i <= otherDependencies.length; i++) {
      formatLog += " %s";
    }

    echo(formatLog, packageA, dependency, otherDependencies);
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
    echo("\tInstalling %s", packageA.name); 
  }
  
  // TODO: Implement.
  public void remove(String packageA) {}

  // TODO: Implement.
  public void list()  {}

  /** Echo some feedback to the user. */
  private void echo(String formatLog, Object... objects) {
    System.out.println(String.format(formatLog, objects));
  }

  /** Retrieve a package given it's name, or create one if need be. */
  private Package getPackage(String name) {
    return this.packages.getOrDefault(name, new Package(name));
  }
}
