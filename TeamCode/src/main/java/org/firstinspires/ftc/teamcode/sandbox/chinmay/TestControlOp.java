package org.firstinspires.ftc.teamcode.sandbox.chinmay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Test Control Op", group="Linear Opmode")
/*
Test of org.firstinspires.ftc.teamcode.sandbox.chinmay.control
 */
public class TestControlOp extends LinearOpMode {

    private chinmaydriver ctdrive = null;
    private ElapsedTime runtime = new ElapsedTime();
    private control Control  = null;

    @Override
    public void runOpMode() {

        ctdrive = new chinmaydriver(hardwareMap);
        Control = new control();
        ctdrive.setPower(0.5);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while(opModeIsActive()){
            Control.controlmovment(ctdrive);
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }

    }}