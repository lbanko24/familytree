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
java -jar ./target/familytree-1.0.jar ./data.txt

Excpected output for data.txt:
Ivan
  Adam
    Stjepan
			Marko
			Robert
	Fran
Luka
	Leopold
