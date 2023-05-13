# Family Tree
Verso Altima test assignment (Family tree containing hierarchically organized members)

# Useful commands

Generate jar file:
```console
mvn package
```

Run tests:
```console
mvn test
```

Execute jar file with data.txt as input file:
```console
java -jar ./target/familytree-1.0.jar ./data.txt
```

Excpected output for data.txt:
Ivan
&nbsp;&nbsp;&nbsp;&nbsp;Adam
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Stjepan
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Marko
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Robert
&nbsp;&nbsp;&nbsp;&nbsp;Fran
Luka
&nbsp;&nbsp;&nbsp;&nbsp;Leopold
