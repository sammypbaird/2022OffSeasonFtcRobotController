package org.firstinspires.ftc.teamcode.roadrunner.objects;

import com.qualcomm.robotcore.hardware.DcMotor;
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
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private SpeedRetriever speedRetriever;
    //store the speed

    public OmniDriver(HardwareMap hardwareMap, SpeedRetriever speedRetriever)
    {
        this.speedRetriever = speedRetriever;

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
     * Stops the driver (which sets the speed to zero)
     */
    public void stop()
    {
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
        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        double max = getMaxValue(leftBackPower,leftFrontPower,rightBackPower,rightFrontPower);

        //normalize the values
        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        //multiple the values by the speed
        double speed = speedRetriever.getSpeed();
        leftBackPower*=speed;
        leftFrontPower*=speed;
        rightBackPower*=speed;
        rightFrontPower*=speed;

        //set the power to each motor\
        // Send calculated power to wheels
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);
    }

    private double getMaxValue(double lb,double lf,double rb,double rf){
        double max;
        max = Math.max(Math.abs(lf), Math.abs(rf));
        max = Math.max(max, Math.abs(lb));
        max = Math.max(max, Math.abs(rb));
        return max;
    };

    public void forward(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(1, 0, 0);
        waitMs(milliseconds);
    }

    public void backward(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(-1, 0, 0);
        waitMs(milliseconds);
    }

    public void strafeLeft(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0,-1,0);
        waitMs(milliseconds);
    }

    public void strafeRight(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0,1,0);
        waitMs(milliseconds);
    }

    public void rotateLeft(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0,0,-1);
        waitMs(milliseconds);
    }

    public void rotateRight(long milliseconds)
    {
        //use "setDirection" and "waitMs" to accomplish this
        setDirection(0,0,1);
        waitMs(milliseconds);
    }

    private void waitMs(long milliseconds)
    {
        //wait until milliseconds
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
