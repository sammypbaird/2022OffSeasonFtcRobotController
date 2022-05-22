package org.firstinspires.ftc.teamcode.roadrunner.objects;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamePadSpeedRetriever implements SpeedRetriever
{
    private Gamepad gamepad;

    public GamePadSpeedRetriever(Gamepad gamepad)
    {
        this.gamepad = gamepad;
    }

    @Override
    public double getSpeed() {
        return ((gamepad.right_stick_y*-1)/2)+0.5;
    }
}
