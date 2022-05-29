load("@rules_java//java:defs.bzl", "java_library")

java_binary(
  name = "Main",
  visibility = ["//visibility:public"],
  srcs = glob(["src/*.java"]),
  deps = ["//src/packages"]
)
