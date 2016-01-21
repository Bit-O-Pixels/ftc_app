package com.qualcomm.ftcrobotcontroller.Krakens;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.lang.reflect.Array;

public class KrakenAuto extends KrakenTelementry

{

    final int wheelRotation = 1120*2;
    final int circum = (int) Math.round(8*Math.PI);
    final int encoderInch = wheelRotation/circum;
    GyroSensor sensorGyro;
    int rot = 0;

    public KrakenAuto()

    {

        hardwareMap.logDevices();
    }

    boolean go = false;
    private Object[][] operations;
    private int step = -1;
    @Override public void start ()

    {

        super.start();

        reset_drive_encoders();
        sensorGyro = hardwareMap.gyroSensor.get("gyro");
        v_state = -1;
        operations = new Object[][]{
                /*{"forward",2 + (1/3),"ft"},
                {"left",45},
                {"forward", 1 + (1/3), "ft"},
                {"left", 90},
                {"forward", 2, "ft"},*/
                {"forward",4,"ft"},
                {"left",45},
                {"forward",4,"ft"},
                {"left",45},
                {"forward",2,"ft"},
                {"back",2,"ft"},
                {"right",90},
                {"back",3,"ft"},
                {"right",45},
                {"back",3.5,"ft"}
                //{"arm","fullextended"}
        };

    }
    private int tet = 0;
    @Override public void loop() {
        if(tet == 0){
            reset_drive_encoders();
            tet++;
        }
        if(!have_drive_encoders_reset()){
            return;
        }
        if(Math.abs(v_left_rear_drive.getCurrentPosition()) >  5000){
            set_drive_power(0.0,0.0);
            return;
        }
        set_drive_power(1.0, 1.0);
        update_telemetry();
        telemetry.addData("22", v_left_rear_drive.getCurrentPosition());

        float upperBaseSpeed = 0.45f;
        float upperSpeed = 0.0f;
        float bucketBaseSpeed = 0.2f;
        float bucketSpeed = 0.0f;

        if(step == -1){
            reset_drive_encoders();
            sensorGyro.calibrate();
            step++;
        }else{
            if(!have_drive_encoders_reset()||sensorGyro.isCalibrating()) { // TODO: Change to wait for things to finish up doing stuff
                return;
            }
            rot = sensorGyro.getHeading();
            Object[] CurrentStep = operations[step];
            if(CurrentStep[0].toString().equals("forward")){
                run_using_encoders();
                //DbgLog.error("BABYKRAKEN: Got To Forward Command. " + CurrentStep[1].toString());
                set_drive_power(0.20,0.20);//Let's make sure we're getting here.
                if(CurrentStep[2].toString().equals("ft")){
                    if(have_drive_encoders_reached(
                            Float.parseFloat(CurrentStep[1].toString())
                                    *12*encoderInch,
                            Float.parseFloat(CurrentStep[1].toString())
                                    *12*encoderInch)){
                        reset_drive_encoders();
                        if(operations.length-1 >= step+1){
                            if(operations[step+1][0].equals("left")||operations[step+1][0].equals("right")){
                                sensorGyro.calibrate();
                            }
                        }
                        set_drive_power(0.0, 0.0);
                        step++;
                    }
                }
            }else if(CurrentStep[0].toString().equals("back")){
                run_using_encoders();
                set_drive_power(-0.20,-0.20);
                if(have_drive_encoders_reached(
                        Float.parseFloat(CurrentStep[1].toString())
                                *12*encoderInch,
                        Float.parseFloat(CurrentStep[1].toString())
                                *12*encoderInch)){
                    reset_drive_encoders();
                    if(operations.length-1 >= step+1){
                        if(operations[step+1][0].equals("left")||operations[step+1][0].equals("right")){
                            sensorGyro.calibrate();
                        }
                    }
                    set_drive_power(0.0, 0.0);
                    step++;
                }
            }else if(CurrentStep[0].toString().equals("left")){
                run_using_encoders();
                set_drive_power(0.05,-0.05);
                if(rot >= Float.parseFloat(CurrentStep[1].toString())){
                    reset_drive_encoders();
                    set_drive_power(0.0, 0.0);
                    step++;
                }
            }else if(CurrentStep[0].toString().equals("right")){
                run_using_encoders();// MAMAAAAAAAAAAAAAAAAAAAA oOOOOOOOOOo0000oooo00
                set_drive_power(-0.05,0.05);
                if(rot >= 360-Float.parseFloat(CurrentStep[1].toString())){
                    reset_drive_encoders();
                    set_drive_power(0.0,0.0);
                    step++;
                }
            }else if(CurrentStep[0].toString().equals("arm")){
                if(CurrentStep[1].equals("fullextended")){
                    set_arm_motors(0.2,0.0);
                    if(have_arm_encoders_reached(0,1440,1440,0)){
                        reset_drive_encoders();
                        set_arm_motors(0.0,0.0);
                        step++;
                    }
                }
                step++;
            }
        }
        telemetry.addData("3","step: " + step );
        update_telemetry();
    }
    private int v_state = 0;
}
