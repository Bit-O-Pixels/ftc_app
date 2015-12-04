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
                float l_gp2_right_joystick_x = gamepad2.right_stick_x;
                float l_gp2_right_joystick_y = gamepad2.right_stick_y;
                boolean l_gp2_button_b = gamepad2.b; //upper up
                boolean l_gp2_button_a = gamepad2.a; //upper down
                boolean l_gp2_dpad_up = gamepad2.dpad_up; //upper up
                boolean l_gp2_dpad_down = gamepad2.dpad_down; //upper down

                float speed = l_gp1_right_trigger - l_gp1_left_trigger;


                //0.0 1.0
                // -1.0, 0.0 and 1.0
                float upperBaseSpeed = 0.45f;
                float upperSpeed = 0.0f;
                float bucketBaseSpeed = 0.2f;
                float bucketSpeed = 0.0f;

                //Arm Math
                if(l_gp2_button_a){
                    //down
                    upperSpeed = -upperBaseSpeed;
                }else if(l_gp2_button_b){
                    //up
                    upperSpeed = upperBaseSpeed;
                }

                //Bucket Math
                if(l_gp2_dpad_up){
                    bucketSpeed = bucketBaseSpeed;
                }else if(l_gp2_dpad_down){
                    bucketSpeed = -bucketBaseSpeed;
                }
                double bucketPos = 0.5 - (l_gp2_left_trigger/2);


                //Drive Math
                double left_speed = Math.min(1.0, Math.max(-1.0, (speed - l_gp1_left_joystick)));
                double right_speed = -Math.min(1.0, Math.max(-1.0, (speed + l_gp1_left_joystick)));

                set_drive_power(left_speed,right_speed);
                //bottom-x, bottom-y, upper-y, bucket-y
                set_arm_motors(l_gp2_right_joystick_x, l_gp2_right_joystick_y, upperSpeed, bucketSpeed);
                set_arm_servos(bucketPos,bucketPos);
                //set_arm_servos
                update_telemetry();
                telemetry.addData("10", "GP1 Left: " + l_gp1_left_trigger);
                telemetry.addData("11", "GP1 Right: " + l_gp1_right_trigger);

        }
}