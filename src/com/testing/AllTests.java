package com.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)	//run a test 
@SuiteClasses({BrickCalTesting.class,CementCalTesting.class,CommitTesting.class,SandCalTesting.class})
public class AllTests {

}
