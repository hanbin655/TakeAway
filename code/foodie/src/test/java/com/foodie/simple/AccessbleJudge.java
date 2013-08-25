package com.foodie.simple;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface AccessbleJudge {
	public String xmlPath();
}
