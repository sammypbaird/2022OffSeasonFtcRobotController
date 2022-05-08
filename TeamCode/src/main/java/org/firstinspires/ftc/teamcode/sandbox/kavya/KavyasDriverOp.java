package org.firstinspires.ftc.teamcode.sandbox.kavya;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//0. Name the TeleOp
@Disabled
@TeleOp(name="Kavya's Driver", group="Linear Opmode")
public class KavyasDriverOp extends LinearOpMode  {

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
            power = 0.5;

            //8. when "A" button is pressed, turn on turbo mode (power is max)
            //gamepad1.a

            //9. when "A" button is not pressed, set power to 0.5
            //gamepad1.a

            if (gamepad1.right_stick_x >= 0.3 || gamepad1.right_stick_x <= -0.3) {
                leftFrontDrive.setPower(gamepad1.right_stick_x);
                rightFrontDrive.setPower(-gamepad1.right_stick_x);
                leftBackDrive.setPower(gamepad1.right_stick_x);
                rightBackDrive.setPower(-gamepad1.right_stick_x);
            } else if (gamepad1.dpad_up) {
                leftFrontDrive.setPower(power);
                rightFrontDrive.setPower(power);
                leftBackDrive.setPower(power);
                rightBackDrive.setPower(power);
            } else if (gamepad1.dpad_down) {
                leftFrontDrive.setPower(-power);
                rightFrontDrive.setPower(-power);
                leftBackDrive.setPower(-power);
                rightBackDrive.setPower(-power);
            } else if (gamepad1.dpad_left) {
                rightFrontDrive.setPower(power);
                leftFrontDrive.setPower(-power);
                rightBackDrive.setPower(-power);
                leftBackDrive.setPower(power);
            } else if (gamepad1.dpad_right) {
                rightFrontDrive.setPower(-power);
                leftFrontDrive.setPower(power);
                rightBackDrive.setPower(power);
                leftBackDrive.setPower(-power);
            } else {
                leftFrontDrive.setPower(0);
                rightFrontDrive.setPower(0);
                leftBackDrive.setPower(0);
                rightBackDrive.setPower(0);
            }

            telemetry.addData("Target Power", power);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
