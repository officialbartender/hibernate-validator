// $Id$
/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.validation.eg.constraint;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.ConstraintValidator;
import javax.validation.OverridesParameter;
import javax.validation.OverridesParameters;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validation.constraints.Pattern;
import org.hibernate.validation.constraints.Patterns;

/**
 * @author Hardy Ferentschik
 */
@NotNull
@Size
@Patterns({ @Pattern(regex = "....."), @Pattern(regex = "bar") })
@ConstraintValidator(FrenchZipcodeConstraint.class)
@Documented
@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface FrenchZipcode {
	String message() default "Wrong zipcode";

	Class<?>[] groups() default { };

	@OverridesParameters({
			@OverridesParameter(constraint = Size.class, parameter = "min"),
			@OverridesParameter(constraint = Size.class, parameter = "max")
	})
	int size() default 5;

	@OverridesParameter(constraint = Size.class, parameter = "message")
    String sizeMessage() default "A french zip code has a length of 5";


	@OverridesParameter(constraint = Pattern.class, parameter = "regex", index=2)
	String regex() default "\\d*";
}