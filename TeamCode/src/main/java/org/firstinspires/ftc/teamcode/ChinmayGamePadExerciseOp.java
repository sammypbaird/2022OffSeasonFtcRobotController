package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//0. Name the TeleOp
@TeleOp(name="", group="")
public class ChinmayGamePadExerciseOp extends LinearOpMode  {

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
            if(gamepad1.a){
                power = 1;
            }else{
                power = 0;
            }

            //9. when "A" button is not pressed, set power to 0.5
            //gamepad1.a

            //6. if right joystick is engaged right, do zero point turn right
            //gamepad1.right_stick_x

            //7. if right joystick is engaged left, do zero point turn left
            //gamepad1.right_stick_x

            //1. if dpad "UP" is pressed, go forward
            //gamepad1.dpad_up
            if(gamepad1.dpad_up){
                power = 0.5;
                leftBackDrive.setPower(power);
                leftFrontDrive.setPower(power);
                rightBackDrive.setPower(power);
                rightFrontDrive.setPower(power);
            }else{
                power = 0;
            }

            //2. else if dpad "DOWN" is pressed, go backwards
            //gamepad1.dpad_down
            if(gamepad1.dpad_down){
                power = 0.5;
                leftBackDrive.setPower(-power);
                leftFrontDrive.setPower(-power);
                rightBackDrive.setPower(-power);
                rightFrontDrive.setPower(-power);
            }else{
                power = 0;
            }
            //3. else if dpad "LEFT" is pressed, strafe left
            //gamepad1.dpad_left
            if(gamepad1.dpad_left){
                    power = 0.5;
                    leftBackDrive.setPower(-power);
                    leftFrontDrive.setPower(power);
                    rightBackDrive.setPower(power);
                    rightFrontDrive.setPower(-power);
            }else{
                power = 0;
            }
            //4. else if dpad "RIGHT" is pressed, strafe right
            //gamepad1.dpad_left
            
            //5. otherwise, set power on all motors to zero


            telemetry.addData("Target Power", power);
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            telemetry.update();
        }
    }
}
