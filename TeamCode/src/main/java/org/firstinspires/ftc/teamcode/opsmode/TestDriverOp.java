package org.firstinspires.ftc.teamcode.opsmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.objects.Driver;
import org.firstinspires.ftc.teamcode.objects.OmniDriver;
import org.firstinspires.ftc.teamcode.sandbox.megan.MegansOmniDriver;

@Autonomous(name="Test Driver Op", group="")
public class TestDriverOp extends LinearOpMode {

    private MegansOmniDriver driver = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        driver = new MegansOmniDriver(hardwareMap);
        driver.setSpeed(0.5);


        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        driver.forward(1000);
        driver.strafeRight(1000);
        driver.backward(1000);
        driver.strafeLeft(1000);

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