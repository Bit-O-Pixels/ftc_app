package com.qualcomm.ftcrobotcontroller.Krakens;

public class Drive extends KrakenTelementry
{
        public Drive() {}
        @Override public void loop () {
                float l_gp1_left_trigger = gamepad1.left_trigger;
                float l_gp1_right_trigger = gamepad1.right_trigger;
                float l_gp1_left_joystick = gamepad1.left_stick_x;
                float l_gp1_right_joystick = gamepad1.right_stick_y;
                float speed = l_gp1_right_trigger - l_gp1_left_trigger;
                //Change to use the little joystick

                double left_speed = Math.min(1.0, Math.max(-1.0, (speed - l_gp1_left_joystick)));
                double right_speed = -Math.min(1.0, Math.max(-1.0, (speed + l_gp1_left_joystick)));
                set_arm_servo_1((l_gp1_right_joystick+1)/2);
                set_drive_power(left_speed,right_speed);
                update_telemetry();
                telemetry.addData("10", "GP1 Left: " + l_gp1_left_trigger);
                telemetry.addData("11", "GP1 Right: " + l_gp1_right_trigger);

        }
}