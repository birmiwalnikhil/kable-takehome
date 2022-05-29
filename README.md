# kable-takehome
#
# A take home interview for Kable, implementing a basic PackageManager in Java.
# There are four top level Commands we support:
#   - `DEPEND <package> <dependency1> [other dependencies..]`, indicating that
#   `dependency1` is needed to install `package`.
#     - Note: As per the README, once set of dependencies has been declared for
#     a package, they won't be modified.
#   - `INSTALL <package>`, which will 1) install any depdenencies of A, followed by 2)
#   installing A itself. Echoes back the feedback if A is already installed.
#   - `REMOVE <package>` attempts to remove an installed package.
#     - If the package is not installed, communicate that to the user..
#     - If the package is installed but required by another package,
#     communicate that to the user.
#     - If, after the removal of the package, no dependency of `package` is
#     required for any other installed package, remove the dependency.
#   - `LIST`. List all the installed programs. 
