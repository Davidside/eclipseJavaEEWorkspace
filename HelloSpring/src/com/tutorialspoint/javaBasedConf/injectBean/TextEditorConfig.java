package com.tutorialspoint.javaBasedConf.injectBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @Import(SpellCheckerConfig.class)   - can be used to config bean spellChecker in another class
public class TextEditorConfig {

	@Bean
	public TextEditor textEditor() {
		return new TextEditor(spellChecker());
	}
	
	@Bean
	public SpellChecker spellChecker() {
		return new SpellChecker();
	}
}
