package org.firstinspires.ftc.teamcode.sandbox.megan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * OmniDriver can receive basic driving commands, and it sets the motors power appropriately.
 * This driver has 3 degrees of movement:
 *  - axial: movement forwards and backwards
 *  - lateral: movement left and right
 *  - yaw: rotation left and right
 */
public class MegansOmniDriver {

    //store the DcMotors as class variables
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    //store the speed
    private double speed = 0;


    public MegansOmniDriver(HardwareMap hardwareMap)
    {
        // Initialize the DcMotors by looking them up in the hardwareMap
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        // Set the motor directions (reverse the left motors)
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     * Sets the speed of the driver.
     * @param speed Must be between 0 and 1
     */
    public void setSpeed(double speed)
    {
        //assign the class variable
        this.speed = speed;
    }

    /**
     * Stops the driver (which sets the speed to zero)
     */
    public void stop()
    {
       //set the speed to zero
       speed = 0;

        //stop the motors by calling "setDirection"
        setDirection(0,0,0);

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
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        //normalize the values
        double max;

        max=Math.max(Math.abs(leftBackPower), Math.abs(rightBackPower));
        max = Math.max(max, Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftFrontPower));

        //multiple the values by the speed
        if (max>1) {
            leftBackPower /= max;
            rightBackPower /= max;
            leftFrontPower /= max;
            rightFrontPower /= max;
        }

        //adjust power according to speed
        leftBackPower *= speed;
        leftFrontPower *= speed;
        rightBackPower *= speed;
        rightFrontPower *= speed;

        //set the power to each motor
        leftFrontDrive.setPower(leftFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightFrontDrive.setPower(rightFrontPower);
        rightBackDrive.setPower(rightBackPower);
    }

    public void forward(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(1, 0, 0);
        waitMs(milliseconds);
        stop();
    }

    public void backward(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(-1,0,0);
        waitMs(milliseconds);
        stop();
    }

    public void strafeLeft(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0,-1,0);
        waitMs(milliseconds);
        stop();
    }

    public void strafeRight(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0, 1, 0);
        waitMs(milliseconds);
        stop();
    }

    public void rotateLeft(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0,0, -1);
        waitMs(milliseconds);
        stop();
    }

    public void rotateRight(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0, 0, 1);
        waitMs(milliseconds);
        stop();
    }

    private void waitMs(long milliseconds)
    {
        //wait until milliseconds
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + milliseconds) {
        }
    }

}
