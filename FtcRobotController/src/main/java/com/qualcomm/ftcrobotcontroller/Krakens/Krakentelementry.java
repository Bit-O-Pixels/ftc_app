package com.qualcomm.ftcrobotcontroller.Krakens;
import com.qualcomm.ftcrobotcontroller.opmodes.PushBotHardware;
/*
 * @author SSI Robotics
 * @version 2015-08-02-13-57
 */
public class Krakentelementry extends KrakenHardware
{
    public Krakentelementry()
    {
    }
    public void update_telemetry ()
    {
        if (a_warning_generated ())
        {
            set_first_message (a_warning_message ());
        }

        telemetry.addData
            ( "01"
            , "Left Front Drive: "
                + a_left_drive_power ()
                + ", "
                + a_left_encoder_count ()
            );
        telemetry.addData
            ( "02"
            , "Right Front Drive: "
                + a_right_drive_power ()
                + ", "
                + a_right_encoder_count ()
            );
        telemetry.addData
                ( "03"
                , "Left Rear Drive: "
                   + a_left_drive_power ()
                   + ", "
                   + a_left_encoder_count ()
                );
        telemetry.addData
                ( "04"
                 , "Right Rear Drive: "
                  + a_right_drive_power ()
                  + ", "
                  + a_right_encoder_count ()
                );

    }
    public void update_gamepad_telemetry ()

    {

        telemetry.addData ("05", "GP1 Left: " + -gamepad1.left_stick_y);
        telemetry.addData ("06", "GP1 Right: " + -gamepad1.right_stick_y);
        telemetry.addData ("07", "GP2 Left: " + -gamepad2.left_stick_y);
        telemetry.addData ("08", "GP2 X: " + gamepad2.x);
        telemetry.addData ("09", "GP2 Y: " + gamepad2.y);
        telemetry.addData ("10", "GP1 LT: " + gamepad1.left_trigger);
        telemetry.addData ("11", "GP1 RT: " + gamepad1.right_trigger);

    }

    public void set_first_message (String p_message)

    {
        telemetry.addData ( "00", p_message);

    }

    public void set_error_message (String p_message)

    {
        set_first_message ("ERROR: " + p_message);

    }
}
