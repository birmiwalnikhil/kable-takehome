package kable.packages;

import java.util.Set;
import java.util.HashSet;

/** Define a package that is provided by one of the top level commands. */
public class Package {
  
  /** The name of the package, e.g. `vim`. */
  private final String name;

  /** All packages that this package depends on. */
  private final Set<Package> dependencies;

  /** All packages that this package is needed for. */
  private final Set<Package> neededFor;

  /** Whether this package is installed. */
  private boolean isInstalled;

  public Package(String name) {
    this.name = name;
    this.dependencies = new HashSet<>();
    this.neededFor = new HashSet<>();
    this.isInstalled = false;
  }

  public void addDependency(Package dependency) {
    this.dependencies.add(dependency);
  }

  public void addNeededFor(Package upstream) {
    this.neededFor.add(upstream);
  }

  public void install() {
    // TODO: Implement.
  }

  public void uninstall() {
    // TODO: Implement.
  }
}
