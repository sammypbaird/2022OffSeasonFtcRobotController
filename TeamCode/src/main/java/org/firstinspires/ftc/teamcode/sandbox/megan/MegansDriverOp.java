package org.firstinspires.ftc.teamcode.sandbox.megan;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Megan's Driver", group="Linear Opmode")
@Disabled
public class MegansDriverOp extends LinearOpMode  {

    //adding a comment
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftBackDrive = null;
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        //set the direction
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        double power = 0;
        while (opModeIsActive()) {

            //turn on turbo mode while "A" button is pressed
            if (gamepad1.a)
                power=1;
            else
                power=0.5;

            //zero point rotation if right joystick is engaged left or right
            if (gamepad1.right_stick_x > 0.5)
            {
                leftBackDrive.setPower(-power);
                leftFrontDrive.setPower(-power);
                rightBackDrive.setPower(power);
                rightFrontDrive.setPower(power);
            }
            else if (gamepad1.right_stick_x < -0.5) {
                leftBackDrive.setPower(power);
                leftFrontDrive.setPower(power);
                //Negative power!
                rightBackDrive.setPower(-power);
                rightFrontDrive.setPower(-power);
            }

            //move or strafe if dpad is engaged
            if (gamepad1.dpad_up) {
                leftBackDrive.setPower(power);
                leftFrontDrive.setPower(power);
                rightBackDrive.setPower(power);
                rightFrontDrive.setPower(power);
            }
            else if (gamepad1.dpad_down) {
                leftBackDrive.setPower(-power);
                leftFrontDrive.setPower(-power);
                rightBackDrive.setPower(-power);
                rightFrontDrive.setPower(-power);
            }
            else if (gamepad1.dpad_left) {
                leftBackDrive.setPower(power);
                leftFrontDrive.setPower(-power);
                rightBackDrive.setPower(-power);
                rightFrontDrive.setPower(power);
            }
            else if (gamepad1.dpad_right) {
                leftBackDrive.setPower(-power);
                leftFrontDrive.setPower(power);
                rightBackDrive.setPower(power);
                rightFrontDrive.setPower(-power);
            }
            else
            {
                leftBackDrive.setPower(0);
                leftFrontDrive.setPower(0);
                rightBackDrive.setPower(0);
                rightFrontDrive.setPower(0);
            }

            telemetry.addData("Target Power", power);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
