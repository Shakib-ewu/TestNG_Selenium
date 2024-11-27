package day3;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
	
	public void onStart(ITestResult result) {
        System.out.println("Test excution Started: ");
    }
	
	
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: ");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " );
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " );
    }
}
