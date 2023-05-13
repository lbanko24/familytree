# Family Tree
Verso Altima test assignment (family tree containing hierarchically organized members)

# Useful commands for running this application

Run these commands in project root directory.

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

Excpected output for data.txt:<br>
Ivan<br>
&nbsp;&nbsp;&nbsp;&nbsp;Adam<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Stjepan<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Marko<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Robert<br>
&nbsp;&nbsp;&nbsp;&nbsp;Fran<br>
Luka<br>
&nbsp;&nbsp;&nbsp;&nbsp;Leopold<br>
