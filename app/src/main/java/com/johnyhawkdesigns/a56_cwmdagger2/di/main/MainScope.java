package com.johnyhawkdesigns.a56_cwmdagger2.di.main;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This defines MainScope for Main module so components are only visible for Main Module life time after user is logged in.
 * It will be defined in 2 places:
 * 1: ActivityBuildersModule -> for MainActivity contributeMainActivity()
 * 2: MainModule -> for MainApi provideMainApi + PostRecyclerAdapter provideAdapter()
 *
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface MainScope {
}
