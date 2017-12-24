### diffparser

[![Build Status](https://travis-ci.org/thombergs/diffparser.svg?branch=master)](https://travis-ci.org/thombergs/diffparser)

Parse unified diffs with Java.

### Code example
```
DiffParser parser = new UnifiedDiffParser();
InputStream in = new FileInputStream("/path/to/file.diff");
List<Diff> diff = parser.parse(in);
```

### What Diff formats can be parsed?
Currently, the only implementation of the DiffParser interface is UnifiedDiffParser, which supports parsing of diffs like the following:
```
Modified: trunk/test1.txt
===================================================================
--- /trunk/test1.txt	2013-10-23 19:41:56 UTC (rev 46)
+++ /trunk/test1.txt	2013-10-23 19:44:39 UTC (rev 47)
@@ -1,4 +1,3 @@
 test1
-test1
+test234

-test1
\ No newline at end of file
@@ -5,9 +6,10 @@
-test1
-test1
+aösdhasd
+asdasd
```

An input stream may contain several sections like the above, delimited by an empty line. Each such section will be parsed into an object
of class Diff.

### Latest Stable Release

#### Download

[ ![Download](https://api.bintray.com/packages/reflectoring/maven-releases/diffparser/images/download.svg) ](https://bintray.com/reflectoring/maven-releases/diffparser/_latestVersion)

#### Maven
```xml
...
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com/</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>io.reflectoring.diffparser</groupId>
    <artifactId>diffparser</artifactId>
    <version>1.4</version>
  </dependency>
</dependencies>
...
```

#### Gradle
```groovy
repositories {
  jcenter()
}

dependencies {
  compile('io.reflectoring.diffparser:diffparser:1.4')
}
```

### Snapshots

You can access the latest snapshot by adding "-SNAPSHOT" to the version number and
adding the repository `https://oss.jfrog.org/artifactory/oss-snapshot-local`
to your build.

You can also reference a specific snapshot like `1.4-20171220.195055-1`. 
Here's the [list of snapshot versions](https://oss.jfrog.org/webapp/#/artifacts/browse/tree/General/oss-snapshot-local/io/reflectoring/diffparser/diffparser).

#### Maven
```xml
...
<repositories>
  <repository>
    <id>oss-snapshot-local</id>
    <url>https://oss.jfrog.org/webapp/#/artifacts/browse/tree/General/oss-snapshot-local/io/reflectoring/diffparser/diffparser</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>io.reflectoring.diffparser</groupId>
    <artifactId>diffparser</artifactId>
    <version>1.5-SNAPSHOT</version>
  </dependency>
</dependencies>
...
```

#### Gradle
```groovy
repositories {
  maven { url 'https://oss.jfrog.org/artifactory/oss-snapshot-local' }
}

dependencies {
  compile('io.reflectoring.diffparser:diffparser:1.5-SNAPSHOT')
}
```

 
