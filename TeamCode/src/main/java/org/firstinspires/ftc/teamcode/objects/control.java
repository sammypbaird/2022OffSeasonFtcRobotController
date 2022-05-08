/*
OmmniOpMode converted into a class
 */

package org.firstinspires.ftc.teamcode.objects;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import org.firstinspires.ftc.teamcode.objects.chinmaydriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
public class control{
    public control(){
        double test = 0.0;
    }
    public void controlmovment(chinmaydriver driver){
        double max;
        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        //decrease overall power by half if "a" is not pressed
        if (!gamepad1.a) {
            leftFrontPower /= 2.0;
            rightFrontPower /= 2.0;
            leftBackPower /= 2.0;
            rightBackPower /= 2.0;
        }

        // Send calculated power to wheels
        driver.leftFrontDrivesetPower(leftFrontPower);
        driver.rightFrontDrivesetPower(rightFrontPower);
        driver.leftBackDrivesetPower(leftBackPower);
        driver.rightBackDrivesetPower(rightBackPower);
    }
}
