package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "autoOp", group = "@@@")

public class autoOp extends LinearOpMode
{
    BNO055IMU imu;
    private ElapsedTime timer = new ElapsedTime();
    private DcMotorEx lefty;
    private DcMotorEx righty;
    private int tpr;
    @Override
    public void runOpMode() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "DroopyIMU.json"; parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);

        lefty= hardwareMap.get(DcMotorEx.class, "left_drive");
        righty = hardwareMap.get(DcMotorEx.class, "right_drive");
        lefty.setDirection(DcMotor.Direction.FORWARD);
        righty.setDirection(DcMotor.Direction.FORWARD);
        lefty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        righty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        tpr = 538;
        waitForStart();
        moveBot(200,200,3,3);
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

    public void turnBotTo(int angle, int speed){
        lefty.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        righty.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        float starthead= imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        if (360-starthead<=starthead){
            lefty.setPower(speed);
            righty.setPower(-speed);
            while(imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle!=angle){
                telemetry.addData("Turn Progress", "Still Turning");
                telemetry.update();
            }
            lefty.setPower(0);
            righty.setPower(0);
            telemetry.addData("Turn Progress", "Complete");
            telemetry.update();
        }
        if (360-starthead>starthead){
            lefty.setPower(-speed);
            righty.setPower(speed);
            while(imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle!=angle){
                telemetry.addData("Turn Progress", "Still Turning");
                telemetry.update();
            }
            lefty.setPower(0);
            righty.setPower(0);
            telemetry.addData("Turn Progress", "Complete");
            telemetry.update();
        }
    }
}
