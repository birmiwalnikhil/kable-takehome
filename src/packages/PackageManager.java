package kable.packages;

import java.util.Set;
import java.util.HashSet;

/** 
 * A PackageManager that supports installing, removing, and listing packages.
 */
public class PackageManager {

  /** 
   * All packages that the PackageManager is aware of. A package
   * may not be installed, e.g. it was introduced via a {@link #depend} call.
   */
  Set<Package> packages;

  public PackageManager() {
    this.packages = new HashSet<>();
  }

  // TODO: Implement.
  public void depend(Package packageA, Package dependency1, Package...
otherDepedencies) {}

  // TODO: Implement.
  public void install(Package packageA) {}

  // TODO: Implement.
  public void remove(Package packageA) {}

  // TODO: Implement.
  public void list()  {}
}
