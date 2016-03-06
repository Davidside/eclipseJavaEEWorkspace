package com.tutorialspoint.autoByName;

public class TextEditor {
	private SpellChecker spellChecker;
	private String name;

	// a setter method to inject dependency.
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Inside setSpellChecker.");
		this.spellChecker = spellChecker;
	}

	// a getter method to return spellChecker
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
