package org.firstinspires.ftc.teamcode.sandbox.chinmay;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
Created this so I can modify without conflict  issues, and to make contriversial changes.
 */
public class chinmaydriver {

    public DcMotor leftFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor rightBackDrive = null;

    public double power = 0;

    public chinmaydriver(HardwareMap hardwareMap)
    {
        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        // Most robots need the motors on one side to be reversed to drive forward.
        // When you first test your robot, push the left joystick forward
        // and flip the direction ( FORWARD <-> REVERSE ) of any wheel that runs backwards
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        motorsStop();
    }

    /**
     * Power must be between -1.0 and 1.0
     */
    public void setPower(double p)
    {
        this.power = p;
    }

    public void driveForwardForDurationMs(long duration)
    {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration) {
            motorsForward();
        }

        motorsStop();
    }

    public void strafeRightForDurationMs(long duration)
    {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration) {
            motorsStrafeRight();
        }
    }

    public void strafeLeftForDurationMs(long duration)
    {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration) {
            motorsStrafeLeft();
        }
    }

    //Creating method to strafe right
    private void motorsStrafeRight()
    {
        setPower(power, -power, -power, power);
    }

    //Creating method to strafe left
    private void motorsStrafeLeft()
    {
        setPower(-power, power, -power, power);
    }

    private void motorsForward()
    {
        setPower(power, power, power, power);
    }

    private void motorsStop()
    {
        setPower(0,0,0,0);
    }

    private void setPower(double leftFront, double rightFront, double leftBack, double rightBack)
    {
        leftFrontDrive.setPower(leftFront);
        rightFrontDrive.setPower(rightFront);
        leftBackDrive.setPower(leftBack);
        rightBackDrive.setPower(rightBack);
    }

    /**
     * Function for rotating right. <br>
     * Time in milliseconds.
     */
    public void rotateRight(long time){
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + time) {
            setPower(power,-power,power,-power);
        }
    }
    /**
     * Function for rotating left. <br>
     * Time in milliseconds
     */
    public void rotateLeft(long time) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + time) {
            setPower(-power, power, -power, power);
        }
    }
    public void leftFrontDrivesetPower(double power){
        leftFrontDrive.setPower(power);
    }
    public void rightFrontDrivesetPower(double power){
        rightFrontDrive.setPower(power);
    }
    public void leftBackDrivesetPower(double power){
        leftBackDrive.setPower(power);
    }
    public void rightBackDrivesetPower(double power){
        rightBackDrive.setPower(power);
    }
}
