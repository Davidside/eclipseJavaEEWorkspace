package com.javacodegeeks.ultimate.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, Integer> {

	public Integer convertToDatabaseColumn(Boolean aBoolean) {
		if (Boolean.TRUE.equals(aBoolean)) {
			return 1;
		} else {
			return -1;
		}
	}

	public Boolean convertToEntityAttribute(Integer value) {
		if (value == null) {
			return Boolean.FALSE;
		} else {
			if (value == 1) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}
	}

}
