package com.qualcomm.ftcrobotcontroller.Krakens;

public class Simple_drive extends KrakenTelementry
{
        public Simple_drive() {}
        @Override public void loop () {

                float l_gp1_left_trigger = gamepad1.left_trigger;
                float l_gp1_right_trigger = gamepad1.right_trigger;
                float l_gp1_left_joystick = gamepad1.left_stick_x;
                float l_gp1_right_joystick = gamepad1.right_stick_y;


                }

                float speed = l_gp1_right_trigger - l_gp1_left_trigger;


                //0.0 1.0
                // -1.0, 0.0 and 1.0
                //float upperBaseSpeed = 0.25f;
                float upperSpeed = upperBin*0.3f;
                //float bucketBaseSpeed = 0.2f;
                float bucketSpeed = lowerBin*0.35f;

                double left_speed = Math.min(1.0, Math.max(-1.0, (speed + l_gp1_left_joystick)));
                double right_speed = Math.min(1.0, Math.max(-1.0, (speed - l_gp1_left_joystick)));

                set_drive_power(left_speed*0.75, right_speed*0.75);
                //bottom-x, bottom-y, upper-y, bucket-y
                set_arm_motors(upperSpeed, bucketSpeed);
                //set_arm_servo(bucketPos);
                //set_arm_servos
                update_telemetry();
                telemetry.addData("10", "GP1 Left: " + l_gp1_left_trigger);
                telemetry.addData("11", "GP1 Right: " + l_gp1_right_trigger);
                telemetry.addData("12",v_left_front_drive.getCurrentPosition());

        }
}