/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.objects;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

//import org.firstinspires.ftc.teamcode.sandbox.sam.SamsOmniDriver;

/*
 *
 * This is an example LinearOpMode that shows how to use
 * a Modern Robotics Color Sensor.
 *
 * The op mode assumes that the color sensor
 * is configured with a name of "sensor_color".
 *
 * You can use the X button on gamepad1 to toggle the LED on and off.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
@Autonomous(name = "Line follower", group = "Sensor")
public class SensorColor extends LinearOpMode {

//    SamsOmniDriver driver;
    ColorSensor colorSensor;    // Hardware Device Object
    int darkestAlpha = Integer.MAX_VALUE; //dark colors have lower values
    int lightestAlpha = 0; //light colors have higher values
    int idealAlpha; //for a line follower, the idea color is a gray color (in between black and white)

    @Override
    public void runOpMode() {

//        driver = new SamsOmniDriver(hardwareMap);
//        driver.setSpeed(0.25);

        // get a reference to our ColorSensor object.
        colorSensor = hardwareMap.colorSensor.get("sensor_color");

        // Set the LED in the beginning
        colorSensor.enableLed(true);

        // wait for the start button to be pressed.
        while (!isStarted())
        {
            int alpha = colorSensor.alpha();
            if (alpha < darkestAlpha)
                darkestAlpha = alpha;
            if (alpha > lightestAlpha)
                lightestAlpha = alpha;

            telemetry.addData("Alpha", colorSensor.alpha());
            telemetry.addData("Darkest Alpha", darkestAlpha);
            telemetry.addData("Lightest Alpha", lightestAlpha);
            telemetry.update();
        }

        //ideal alpha is the value right between the lightest and darkest alpha
        idealAlpha = (lightestAlpha - darkestAlpha) / 2;

        // while the op mode is active, loop and read the RGB data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            double correction = (idealAlpha - colorSensor.alpha()) / (double)(lightestAlpha - darkestAlpha);
//            driver.setDirection(1.0,0, -2 * correction);

            telemetry.addData("Alpha", colorSensor.alpha());
            telemetry.addData("Min", darkestAlpha);
            telemetry.addData("Max", lightestAlpha);
            telemetry.addData("Correction", correction);

            telemetry.update();
        }
    }
}
