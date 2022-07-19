package ru.svyat.pdapp.utils;

import java.util.function.Function;

public final class NullityCheck {
	public static<T, R> R let(T value, Function<T, R> supply){
		if(value != null)
			return supply.apply(value);
		else return null;
	}
}
