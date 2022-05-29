package kable.packages;

import java.util.Set;
import java.util.HashSet;

/** Define a package that is provided by one of the top level commands. */
class Package {
  
  /** The name of the package, e.g. `vim`. */
  final String name;

  /** All packages that this package depends on. */
  final Set<Package> dependencies;

  /** All packages needed by this one. */
  final Set<Package> neededBy;

  /** Whether this package is installed. */
  boolean isInstalled;

  Package(String name) {
    this.name = name;
    this.dependencies = new HashSet<>();
    this.neededBy = new HashSet<>();
    this.isInstalled = false;
  }

  void addDependency(Package dependency) {
    this.dependencies.add(dependency);
  }

  void addNeededBy(Package upstream) {
    this.neededBy.add(upstream);
  }

  void setInstall(boolean isInstalled) {
    this.isInstalled = isInstalled;
  }

  // Return all installed packages actively needed by this one.
  Set<Package> activeNeededByPackages() {
    Set<Package> installedPackages = new HashSet<>();
    for (Package p : neededBy) {
      if (p.isInstalled) {
        installedPackages.add(p);
      }
    }

    return installedPackages;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.name).append(this.isInstalled ? "[x]" : "[]").append("[");
    for (Package dep : this.dependencies) {
      sb.append(dep.name).append(",");
    }
    sb.append("]");
    return sb.toString();
 }
}
