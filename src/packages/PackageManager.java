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
      echo(
        String.format("DEPEND on %s invalid; package already defined.",
packageA));
      return;
    } 

    Package primaryPackage = new Package(packageA);
    Set<String> dependencies = new HashSet<>();
    dependencies.add(dependency);
    for (String otherDependency : otherDependencies) {
      dependencies.add(otherDependency);
    }

    // For each dependency B, indicate that A is dependent on B,
    // and that B is needed for A.
    for (String b : dependencies) {
      Package dep = packages.getOrDefault(b, new Package(b));
      primaryPackage.addDependency(dep);
      dep.addNeededFor(primaryPackage);
    }

    return;
  }

  // TODO: Implement.
  public void install(String packageA) {}

  // TODO: Implement.
  public void remove(String packageA) {}

  // TODO: Implement.
  public void list()  {}

  /** Echo some feedback to the user. */
  private void echo(String log) {
    System.out.println(log);
  }
}
