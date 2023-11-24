package solution2100to2200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution2115 {

	// Fails as there could be a cyclic dependency, gets stuck in infinite loop
	/*public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
		Set<String> possibleRecipes = new HashSet<>();
		Set<String> suppliesSet = new HashSet<>();
		Set<String> recipesSet = new HashSet<>();
		Map<String, Set<String>> recipeDependencies = new HashMap<>();

		for (String supply : supplies) {
			suppliesSet.add(supply);
		}

		for (String recipe : recipes) {
			recipesSet.add(recipe);
		}

		for (int i = 0; i < recipes.length; i++) {
			String recipe = recipes[i];
			List<String> ingredientsList = ingredients.get(i);
			Set<String> ingredientSet = new HashSet<>();
			for (String s : ingredientsList) {
				ingredientSet.add(s);
			}
			recipeDependencies.put(recipe, ingredientSet);
		}

		for (String recipe : recipes) {
			if (fullFillDependencies(possibleRecipes, suppliesSet, recipe, recipeDependencies)) {
				possibleRecipes.add(recipe);
			}
		}

		List<String> pr = new ArrayList<String>();
		pr.addAll(possibleRecipes);
		return null;
	}

	private static boolean fullFillDependencies(Set<String> possibleRecipes, Set<String> suppliesSet, String recipe,
			Map<String, Set<String>> recipeDependencies) {
		Set<String> dependencies = recipeDependencies.get(recipe);

		// Missing Supply
		if (dependencies == null) {
			return false;
		}

		// Fulfilled by supply or another recipe
		if (dependencies.size() == 0 || possibleRecipes.contains(recipe)) {
			return true;
		}

		// Check for recipe dependencies
		boolean possible = true;
		for (String dependency : dependencies) {
			if (suppliesSet.contains(dependency) || possibleRecipes.contains(dependency)) {
				continue;
			}
			possible &= fullFillDependencies(possibleRecipes, suppliesSet, dependency, recipeDependencies);
		}
		return possible;
	}*/
	
	// Solving using Topological Sorting, used Kahn's Algorithm
	public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
		List<String> possibleRecipes = new ArrayList<>();
		List<String> tempPossibleRecipes = new ArrayList<>();
		Set<String> suppliesSet = new HashSet<>();
		Set<String> recipesSet = new HashSet<>();
		Map<String, Set<String>> recipeDependenciesMap = new HashMap<>();

		for (String supply : supplies) {
			suppliesSet.add(supply);
		}

		for (String recipe : recipes) {
			recipesSet.add(recipe);
		}

		//Get all recipes possible from supplies
		for (int i = 0; i < recipes.length; i++) {
			String recipe = recipes[i];
			List<String> ingredientsList = ingredients.get(i);
			Set<String> ingredientSet = new HashSet<>();
			boolean possible = true;
			for (String s : ingredientsList) {
				if (!suppliesSet.contains(s)) {
					ingredientSet.add(s);
					possible = false;
				}
			}
			if (possible) {
				tempPossibleRecipes.add(recipe);
			} else {
				recipeDependenciesMap.put(recipe, ingredientSet);
			}
		}
		
		//Get dependent recipes
		while (!tempPossibleRecipes.isEmpty()) {
			String recipe = tempPossibleRecipes.remove(0);
			possibleRecipes.add(recipe);
			for (String key : recipeDependenciesMap.keySet()) {
				Set<String> dependecies = recipeDependenciesMap.get(key);
				if (dependecies.size() > 0) {
					dependecies.remove(recipe);
					if (dependecies.size() == 0) {
						tempPossibleRecipes.add(key);
					}
				}
			}
		}
		
		
		return possibleRecipes;
	}
}
