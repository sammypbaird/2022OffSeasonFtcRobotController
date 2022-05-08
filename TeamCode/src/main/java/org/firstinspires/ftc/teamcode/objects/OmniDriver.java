package org.firstinspires.ftc.teamcode.objects;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * OmniDriver can receive basic driving commands, and it sets the motors power appropriately.
 * This driver has 3 degrees of movement:
 *  - axial: movement forwards and backwards
 *  - lateral: movement left and right
 *  - yaw: rotation left and right
 */
public class OmniDriver {

    //store the DcMotors as class variables

    //store the speed

    public OmniDriver(HardwareMap hardwareMap)
    {
        // Initialize the DcMotors by looking them up in the hardwareMap


        // Set the motor directions (reverse the left motors)
    }

    /**
     * Sets the speed of the driver.
     * @param speed Must be between 0 and 1
     */
    public void setSpeed(double speed)
    {
        //assign the class variable
    }

    /**
     * Stops the driver (which sets the speed to zero)
     */
    public void stop()
    {
        //set the speed to zero

        //stop the motors by calling "setDirection"
    }

    /**
     * Set the direction of the driver, by providing 3 degrees of movement. All numbers
     * will be combined to generate an aggregate movement.
     * @param axial Backward/Forward movement, number must be between -1 and 1
     * @param lateral Left/Right movement, number must be between -1 and 1
     * @param yaw Rotate Left/Rotate Right, number must be between -1 and 1
     */
    public void setDirection(double axial, double lateral, double yaw)
    {
        //calculate the power to each motor

        //normalize the values

        //multiple the values by the speed

        //set the power to each motor
    }

    public void forward(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
    }

    public void backward(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
    }

    public void strafeLeft(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
    }

    public void strafeRight(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
    }

    public void rotateLeft(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
    }

    public void rotateRight(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
    }

    private void waitMs(long milliseconds)
    {
        //wait until milliseconds
    }

}
