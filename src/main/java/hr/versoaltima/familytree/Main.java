package hr.versoaltima.familytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Specify path to input file");
			System.exit(0);
		}
		
		FamilyTree tree = loadFamilyTree(Path.of(args[0]));

		System.out.println(tree.display());
	}

	private static FamilyTree loadFamilyTree(Path path) {
		FamilyTree tree = new FamilyTree();
		
		try (BufferedReader br = Files.newBufferedReader(path)) {
			String line;
			
			while ((line = br.readLine()) != null) {
				
				if (line.isBlank()) continue;
				
				String[] parts = line.trim().split("\\s+");
				
				try {
					String childName = parts[0];
					String parentName = parts[1];
					
					tree.insertParentAndChild(childName, parentName);
				} catch (ArrayIndexOutOfBoundsException ex) {
					System.out.println("Error on line: " + line);
					System.out.println("There must be two names on each line");
				}
			}
			
		} catch (IOException ex) {
			System.out.println("Error reading file");
			System.exit(-1);
		} catch (CyclicException ex) {
			System.out.println("This family tree contains a cycle");
			System.exit(-1);
		}
		
		return tree;
	}
	
}
