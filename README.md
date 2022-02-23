# How to use it

## Building app/jar and run tests

Open sbt shell for fsater exectuion.

```
sbt
project main
clean
test
assembly
```

or run it all in one go:

```
sbt clean test assembly
```

run unit tests only:
```
sbt "testOnly **.ut.**"
```

Or run the tests from your IDE (e.g. IntelliJ).

## Local spark setup for Windows users

For Windows it might be required to install hadoop libraries locally:

```
a) download hadoop binaries: http://archive.apache.org/dist/hadoop/core/hadoop-2.8.1/hadoop-2.8.1.tar.gz
b) unpack it, eg. C:\Tools\hadoop-2.8.1
c) configure system environment variable:
- Create new env variable: HADOOP_HOME="C:\Tools\hadoop-2.8.1"
- Append the following to the PATH variable: %HADOOP_HOME%\bin
```

3. Download and copy the following 2 files to the %HADOOP_HOME%\bin directory:

```
https://github.com/steveloughran/winutils/blob/master/hadoop-2.8.1/hadoop.dll
https://github.com/steveloughran/winutils/blob/master/hadoop-2.8.1/winutils.exe
```
