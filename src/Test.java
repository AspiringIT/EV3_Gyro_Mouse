import lejos.hardware.port.SensorPort; // Importing a class for hardware sensor port.
import lejos.hardware.Battery; // Importing a class for handling the robot's battery.
import lejos.hardware.BrickFinder; // Importing a class for finding the robot's brick.
import lejos.hardware.Button; // Importing a class for handling button input.
import lejos.hardware.sensor.EV3GyroSensor; // Importing a class for an EV3 gyro sensor.
import lejos.robotics.SampleProvider; // Importing a class for handling sample providers.
import lejos.utility.Delay; // Importing a class for handling time delays.

public class Test {

	// Robot Configuration
	private static EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S2); // Initializing a gyro sensor on SensorPort.S2.

	// Configuration
	private static int HALF_SECOND = 500; // Defining a constant value for half a second in milliseconds.

	public static void main(String[] args) {
		gyroSensor.reset();
		System.out.println("Gyro reset");
		System.out.println("Waiting 5 seconds");

		Delay.msDelay(5000);//waits 5 seconds before continuing

		final SampleProvider sp = gyroSensor.getAngleMode(); // Getting a sample provider for the gyro sensor's angle.
		int value = 0; // Initializing a variable to store gyro angle values.

		// Control loop
		final int iteration_threshold = 20; // Defining a threshold for the control loop iterations.
		for(int i = 0; i <= iteration_threshold; i++) { // Loop for a specific number of iterations.

			float[] sample = new float[sp.sampleSize()]; // Creating an array to store sensor samples.
            sp.fetchSample(sample, 0); // Fetching a sample from the gyro sensor.
            value = (int)sample[0]; // Extracting the gyro angle value from the sample.

			System.out.println("Iteration: " + i); // Printing the current iteration number.
			System.out.println("Gyro angle: " + value); // Printing the gyro angle value.

            Delay.msDelay(HALF_SECOND); // Adding a delay of half a second.

        }
        
        gyroSensor.close(); // Closing the gyro sensor port.

		System.out.println("Battery Voltage: " + BrickFinder.getDefault().getPower().getVoltage()); // Printing the battery voltage.
		System.out.println("Full Battery = 9V"); // Printing a message about full battery voltage.
        Button.waitForAnyPress(); // Waiting for any button press.

	}

}
