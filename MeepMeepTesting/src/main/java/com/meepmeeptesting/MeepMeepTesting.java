package com.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(90)))
                                .forward(50)
                                .turn(Math.toRadians(-90))
                                .splineTo(new Vector2d(20, 40), Math.toRadians(-90))
                                .splineTo(new Vector2d(00, 30), Math.toRadians(-180))
                                .turn(Math.toRadians(180))
                                .splineTo(new Vector2d(25, 15), Math.toRadians(-90))
                                .splineTo(new Vector2d(00, 0), Math.toRadians(-180))
                                .turn(Math.toRadians(-90))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.GRID_BLUE)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}