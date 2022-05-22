package org.firstinspires.ftc.teamcode.opsmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.objects.GamePadSpeedRetriever;
import org.firstinspires.ftc.teamcode.roadrunner.objects.OmniDriver;

@Autonomous(name="Test Omni Op", group="")
public class TestOmniOp extends LinearOpMode {

    private OmniDriver driver = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        GamePadSpeedRetriever gamePadSpeedRetriever = new GamePadSpeedRetriever(gamepad1);
        driver = new OmniDriver(hardwareMap, gamePadSpeedRetriever);


        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        driver.forward(1000);
        driver.backward(1000);
        driver.strafeLeft(1000);
        driver.strafeRight(1000);

        for (int i=0;i<10;i++) {
            driver.rotateRight(500);
            driver.forward(500);
        }

        driver.stop();

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}