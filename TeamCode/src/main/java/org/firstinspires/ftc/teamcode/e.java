package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "autoOp", group = "@@@")

public class e extends LinearOpMode
{
    private ElapsedTime timer = new ElapsedTime();
    private DcMotorEx lefty;
    private DcMotorEx righty;
    private int tpr;
    @Override
    public void runOpMode() {
        lefty= hardwareMap.get(DcMotorEx.class, "left_drive");
        righty = hardwareMap.get(DcMotorEx.class, "right_drive");
        lefty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        righty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        tpr = 538;
        waitForStart();
        lefty.setTargetPosition(tpr*2); //runs for 2 rotations
        righty.setTargetPosition(tpr*2);

        lefty.setVelocity(200); //sets the speed of the motor
        righty.setVelocity(200);
        lefty.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        righty.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void moveBot(int velL, int velR, int rotL, int rotR){
        tpr = 538;
        lefty.setTargetPosition(tpr*rotL);
        righty.setTargetPosition(tpr*rotR);
        lefty.setVelocity(velL);
        righty.setVelocity(velR);
        lefty.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        righty.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
