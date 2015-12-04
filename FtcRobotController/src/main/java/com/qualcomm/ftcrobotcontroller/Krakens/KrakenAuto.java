package com.qualcomm.ftcrobotcontroller.Krakens;

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
                {"forward",2 + (1/3),"ft"},
                {"left",45},
                {"forward", 1 + (1/3), "ft"},
                {"left", 90},
                {"forward", 2, "ft"}
        };

    }
    @Override public void loop() {
        if(step == -1){
            //init >:(

        }else{
            //go go go >:(((((
            if(!have_drive_encoders_reset()) { // TODO: Change to wait for things to finish up doing stuff
                return; // :)
            }
            Object[] CurrentStep = operations[step];
            if(CurrentStep[0].equals("forward")){
                set_drive_power(0.20,-0.20);
                if(CurrentStep[2].equals("ft")){
                    if(have_drive_encoders_reached(
                            (Integer.valueOf((String)CurrentStep[1])
                                    *12*encoderInch,
                            Integer.valueOf((String)CurrentStep[1])
                                    *12*encoderInch)){

                    }
                }
            }else if(CurrentStep[0].equals("back")){
                set_drive_power(-0.20,0.20);
                if(have_drive_encoders_reached()){

                }
            }else if(CurrentStep[0].equals("left")){
                set_drive_power(0.05,0.05);
            }else if(CurrentStep[0].equals("right")){
                set_drive_power(-0.05,-0.05);
            }
        }
    }
    /*
    @Override public void loop ()

    {

        /*
        [["forward",[6,"ft"]], ["turn_left",90],  ... ]




        update_telemetry(); // Update common telemetry
        if(v_state == -1){
            sensorGyro.calibrate();
            v_state++;
        }
        telemetry.addData("18", "State: " + v_state);
        if(sensorGyro.isCalibrating()){
            telemetry.addData("17", "Calibrating");
            v_state = 0;
            return; // Kill if calibrating
        }
        rot = sensorGyro.getHeading();
        telemetry.addData("17", "rotation: " + rot);

        switch (v_state)
        {
        case 0:
            reset_drive_encoders ();

            v_state++;

            break;

        case 1:
            run_using_encoders ();

            set_drive_power(0.25, -0.25);

            if (have_drive_encoders_reached (encoderInch*12, encoderInch*12))
            {
                reset_drive_encoders ();

                set_drive_power (0.0f, 0.0f);
                //sensorGyro.calibrate();

                v_state++;
            }
            break;
        case 2:
            if (have_drive_encoders_reset ())
            {
                v_state++;
            }
            break;
            case 3:
                run_using_encoders ();
                set_drive_power (0.25f, 0.25f);
                if (rot >= 90)
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            case 4:
                if (have_drive_encoders_reset ())//other part of above
                {
                    v_state++;
                }
                break;
            case 5:
                run_using_encoders ();
                set_drive_power (0.25f, 0.25f);
                if (have_drive_encoders_reached (encoderInch*8, encoderInch*8))
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            case 6:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                break;
            case 7:
                run_using_encoders ();
                set_drive_power (0.25f, 0.25f);
                if (rot >= 45)
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            case 8:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                break;
            case 9:
                run_using_encoders ();
                set_drive_power (0.25f, -0.25f);
                if (have_drive_encoders_reached (encoderInch*14.42, encoderInch*14.42))
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            case 10:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                break;
        default:
            break;
        }
    }*/
    private int v_state = 0;
}
