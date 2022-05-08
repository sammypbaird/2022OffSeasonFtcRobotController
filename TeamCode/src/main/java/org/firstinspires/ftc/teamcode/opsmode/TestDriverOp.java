package org.firstinspires.ftc.teamcode.opsmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.objects.Driver;

@Autonomous(name="Test Driver Op", group="")
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
        driver.driveForward(1000);
        driver.strafeRight(1000);
        driver.driveBackwards(1000);
        driver.strafeLeft(1000);

        for (int i=0;i<10;i++) {
            driver.rotateRight(500);
            driver.driveForward(500);
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}