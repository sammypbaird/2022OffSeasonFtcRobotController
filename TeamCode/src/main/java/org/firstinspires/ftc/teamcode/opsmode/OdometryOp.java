package org.firstinspires.ftc.teamcode.opsmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Odometry Op", group="Linear Opmode")
public class OdometryOp extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(90)))
                .forward(50)
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(20, 40), Math.toRadians(-90))
                .splineTo(new Vector2d(00, 30), Math.toRadians(-180))
                .turn(Math.toRadians(180))
                .splineTo(new Vector2d(25, 15), Math.toRadians(-90))
                .splineTo(new Vector2d(00, 0), Math.toRadians(-180))
                .turn(Math.toRadians(-90))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(trajSeq);

        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("finalX", poseEstimate.getX());
        telemetry.addData("finalY", poseEstimate.getY());
        telemetry.addData("finalHeading", poseEstimate.getHeading());
        telemetry.update();

        while (!isStopRequested() && opModeIsActive()) ;
    }

    private Pose2d getEndPose(List<Trajectory> trajectoryList)
    {
        return trajectoryList.get(trajectoryList.size() - 1).end();
    }
}