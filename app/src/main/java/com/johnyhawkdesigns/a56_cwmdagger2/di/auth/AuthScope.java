package com.johnyhawkdesigns.a56_cwmdagger2.di.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This defines AuthScope for Auth module so components are only visible for Auth life time.
 * It will be defined in 2 places:
 * 1: ActivityBuildersModule -> for AuthActivity contributeAuthActivity()
 * 2: AuthModule -> for AuthApi provideAuthApi
 *
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface AuthScope {
}
