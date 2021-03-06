package com.qualcomm.ftcrobotcontroller.Krakens;

import com.qualcomm.robotcore.hardware.GyroSensor;

public class KrakenAutoRedGyroTest extends KrakenTelementry

{

    public KrakenAutoRedGyroTest()

    {

    } // PushBotAuto

    //--------------------------------------------------------------------------
    //
    // start
    //
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    private boolean LEFT = false;
    private boolean RIGHT = true;

    private GyroSensor sensorGyro;
    private int heading = 0;
    @Override public void start ()

    {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();

        //
        // Reset the motor encoders on the drive wheels.
        //
        reset_drive_encoders ();
        v_state = 0;
        heading = 0;
        // write some device information (connection info, name and type)
        // to the log file.
        hardwareMap.logDevices();

        // get a reference to our GyroSensor object.
        sensorGyro = hardwareMap.gyroSensor.get("gyro");
        /*
            if(sensorGyro.getHeading() > [90/45]){
                stop motors
                move on
            }
        */


        // calibrate the gyro.
        sensorGyro.calibrate();//

    } // start

    @Override public void loop ()

    {
        if(sensorGyro.isCalibrating()){
            return;
        }
        switch (v_state)
        {
        case 0:

            reset_drive_encoders ();

            v_state++;


        case 1:

            run_using_encoders ();

            //
            // Start the drive wheel motors at full power.
            //
            set_drive_power (0.25f, 0.25f);

            //
            // Have the motor shafts turned the required amount?
            //
            // If they haven't, then the op-mode remains in this state (i.e this
            // block will be executed the next time this method is called).
            //
            if (have_drive_encoders_reached (677.4, 677.4))//1329
            {
                //
                // Reset the encoders to ensure they are at a known good value.
                //
                reset_drive_encoders ();

                //
                // Stop the motors.
                //
                set_drive_power (0.0f, 0.0f);

                //
                // Transition to the next state when this method is called
                // again.
                //

                v_state++;
            }
            break;
        //
        // Wait...
        //
        case 2:
            if (have_drive_encoders_reset ())
            {
                v_state++;
            }
            break;
        //
        // Turn left until the encoders exceed the specified values.
        //
        case 3:
            run_using_encoders();
            set_drive_power(-0.25f, 0.25f);

            if (have_drive_encoders_reached (1050, 1050))
            {
                reset_drive_encoders ();
                set_drive_power (0.0f, 0.0f);
                v_state++;
            }
            break;
        //
        // Wait...
        //
        case 4:
            if (have_drive_encoders_reset ())
            {
                v_state++;
            }
            break;
        //
        // Turn right until the encoders exceed the specified values.
        //
        case 5:
            run_using_encoders ();
            set_drive_power (0.75f, 0.75f);
            if (have_drive_encoders_reached (8500, 8500))
            {
                reset_drive_encoders ();
                set_drive_power (0.0f, 0.0f);
                v_state++;
            }
            break;
        //
        // Wait...
        //
        case 6:
            if (have_drive_encoders_reset ())
            {
                v_state++;
            }
            break;

            case 7:
                run_using_encoders ();
                set_drive_power (-0.25f, 0.25f);
                if (have_drive_encoders_reached (1100, 1100))
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            //
            // Wait...
            //
            case 8:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                break;

            case 9:
                run_using_encoders ();
                set_drive_power (0.25f, 0.25f);
                if (have_drive_encoders_reached (1608.2, 1608.2))
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            //
            // Wait...
            //
            case 10:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                break;
        //
        // Perform no action - stay in this case until the OpMode is stopped.
        // This method will still be called regardless of the state machine.
        //
        default:
            //
            // The autonomous actions have been accomplished (i.e. the state has
            // transitioned into its final state.
            //
            break;
        }

        //
        // Send telemetry data to the driver station.
        //
        update_telemetry (); // Update common telemetry
        telemetry.addData ("18", "State: " + v_state);

    } // loop
    private void updateHeading() {

    }
    private void resetHeading() {
        //sensorGyro.reset
    }
   // private boolean hasHeadingReached(boolean direction, int angle){
    //    if(direction == LEFT){
     //       return heading <= 360-angle;
     //   }else if(direction == RIGHT){
     //       return heading >= angle;
     //   }
  //  }
    //--------------------------------------------------------------------------
    //
    // v_state
    //
    /**
     * This class member remembers which state is currently active.  When the
     * start method is called, the state will be initialized (0).  When the loop
     * starts, the state will change from initialize to state_1.  When state_1
     * actions are complete, the state will change to state_2.  This implements
     * a state machine for the loop method.
     */
    private int v_state = 0;

} // PushBotAuto
