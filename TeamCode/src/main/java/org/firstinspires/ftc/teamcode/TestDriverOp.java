package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.objects.Driver;

@TeleOp(name="Test Driver Op", group="Linear Opmode")
public class TestDriverOp extends LinearOpMode {

    private Driver driver = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        driver = new Driver(hardwareMap);
        driver.setPower(0.5);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            driver.rotate(0,2000);
            driver.rotate(1,2000);
            driver.driveForwardForDurationMs(2000);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}