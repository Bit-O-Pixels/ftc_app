package com.qualcomm.ftcrobotcontroller.Krakens;

import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by manager on 1/29/2016.
 */
public class KrakenOBO {
    KrakenTelementry kt;
    private int lowerGoal = -1;
    private int upperGoal = -1;
    //private boolean
    public KrakenOBO(KrakenTelementry kt){
        this.kt = kt;
    }
    public void setArmToPosition(int lower, int upper){

        lowerGoal = lower;
        upperGoal = upper;

    }
    public void loop(){
        int lowerEnc = kt.v_arm_motor_3.getCurrentPosition();
        int upperEnc = kt.v_arm_motor_4.getCurrentPosition();
        if(upperGoal == -1&& lowerGoal == -1){
            return;
        }
        if(Math.abs(lowerEnc - lowerGoal) >= 3){
            kt.v_arm_motor_3.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            kt.v_arm_motor_3.setTargetPosition(lowerGoal);
            kt.v_arm_motor_3.setPower(0.30);
        }else if(Math.abs(upperEnc - upperGoal) >= 3){
            //lower is set!
            kt.v_arm_motor_3.setPower(0);
            kt.v_arm_motor_3.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            kt.v_arm_motor_4.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            kt.v_arm_motor_4.setTargetPosition(lowerGoal);
            kt.v_arm_motor_4.setPower(0.30);
        }
    }
}
