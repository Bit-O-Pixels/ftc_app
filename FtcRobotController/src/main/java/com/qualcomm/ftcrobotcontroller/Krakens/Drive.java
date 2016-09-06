package com.qualcomm.ftcrobotcontroller.Krakens;

public class Drive extends KrakenTelementry
{
        public Drive() {}
        @Override public void loop () {

                float l_gp1_left_trigger = gamepad1.left_trigger;
                float l_gp1_right_trigger = gamepad1.right_trigger;
                float l_gp1_left_joystick = gamepad1.left_stick_x;
                float l_gp1_right_joystick = gamepad1.right_stick_y;

                float l_gp2_left_trigger = gamepad2.left_trigger;
                float l_gp2_right_joystick_y = gamepad2.right_stick_y;
                float l_gp2_left_joystick_y = gamepad2.left_stick_y;
                float upperBin = 0;
                float lowerBin = 0;
                if(gamepad2.dpad_up){
                        upperBin = -1;
                }else if(gamepad2.dpad_down){
                        upperBin = 1;
                }
                if(gamepad2.a){
                        lowerBin = -1;
                }else if(gamepad2.b){
                        lowerBin = 1;
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